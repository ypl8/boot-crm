package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.common.utils.StringUtils;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.*;
import cn.kfqjtdqb.core.utils.*;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AssertEstateController {

    // 依赖注入
    @Autowired
    private AssertEstateService assertEstateService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private PropertyLeasingService propertyLeasingService;
    @Autowired
    private TotalEstateService totalEstateService;

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RuntimeService runtimeService;
    @Value("${estate.state.type}")
    private String STATE_TYPE;
    @Value("${check.state.type}")
    private String CHECK_STATE_TYPE;
    private String processDefinitionKey = "estate";

    @RequestMapping(value = "/assertEstate")
    public String showAssertEstate() {
        return "redirect:/assertEstate/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertEstate/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String state, Model model,String status) {

        Page<AssertEstate> AssertEstateList = assertEstateService.selectAssertEstateList(page, rows, property_leasing_num, state,status);
        model.addAttribute("page", AssertEstateList);
        List<BaseDict> checkType = systemService.findBaseDictListByType(CHECK_STATE_TYPE);
        model.addAttribute("checkType", checkType);
        List<BaseDict> stateType = systemService.findBaseDictListByType(STATE_TYPE);
        model.addAttribute("stateType", stateType);
        model.addAttribute("state", state);
        model.addAttribute("status", status);

        //参数回显
        model.addAttribute("property_leasing_num", property_leasing_num);
        return "assertEstate";
    }

    @RequestMapping("/assertEstate/edit")
    @ResponseBody
    public AssertEstate getAssertEstateById(Long id) {
        AssertEstate AssertEstate = assertEstateService.getAssertEstateById(id);
        return AssertEstate;
    }

    /*    String id, String property_leasing_num, String state, String estate, String reality_estate, String rent_start_time,
        String rent_end_time, String estate_received*/
    @RequestMapping("/assertEstate/update")
    @ResponseBody
    public ResultCode updateAssertEstate(@Valid AssertEstate assertEstate, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        if (assertEstate.getEstate_recivied() == null) {
            assertEstate.setEstate_recivied(new BigDecimal("0"));
        }

        try {
            String property_leasing_num = assertEstate.getProperty_leasing_num();
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(property_leasing_num);

            if (assertEstate.getEstate_recivied().compareTo(assertEstate.getEstate_recivied().add(assertEstate.getReality_estate())) <= 0) {  //表示的已经缴清
                assertEstate.setState(ConstUtils.ESTATESTATESUCCESS);
                propertyLeasing.setEstateState(ConstUtils.ESTATESTATESUCCESS);
            } else {
                assertEstate.setState(ConstUtils.ESTATESTATEFAIL);
                propertyLeasing.setEstateState(ConstUtils.ESTATESTATEFAIL);
            }
            assertEstate.setStatus(ConstUtils.CheckUNCOMINT);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
            assertEstateService.updateAssertEstate(assertEstate);
            resultCode.setMsg("物业费修改成功");
            resultCode.setCode(0);
            return resultCode;
        } catch (DataAccessException e) {
            resultCode.setMsg(e.getMessage());
            resultCode.setCode(-1);
            return resultCode;
        }


    }

    @RequestMapping("/assertEstate/delete")
    @ResponseBody
    public String deleteAssertEstate(Long id) {
        assertEstateService.deleteAssertEstate(id);
        return "OK";
    }


    @RequestMapping("/assertEstate/changeState")
    @ResponseBody
    public ResultCode changeState(Long id, String comment_state, @RequestParam(defaultValue = "") String comment) {
        String status = comment_state;
        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();

            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());

            // String processDefinitionKey = "assert";  //第一个版本


            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);

          /*  AssertEstate assertEstate = assertEstateService.getAssertEstateById(id);
            assertEstate.setStatus(status);
            assertEstateService.updateAssertEstate(assertEstate);*/

            AssertEstate assertEstate = assertEstateService.getAssertEstateById(id);

            for (Task task : tasks) {
                //2  通过任务对象获取流程实例
                ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                //3 通过流程实例获取“业务键”
                String businessKey = pi.getBusinessKey();
                if (businessKey != null && id.toString().equals(businessKey)) {
                    activitiService.completeTask(userInfo.getId().toString(), task.getId(), status, pi.getId(), comment);
                    if (ConstUtils.CheckPASS.equals(status)) {
                        BigDecimal realityEstate = assertEstate.getReality_estate();
                        List<TotalEstate> totalEstates = totalEstateService.getTotalEstateByLeasingNum(assertEstate.getProperty_leasing_num());
                        for (int i = 0; i < totalEstates.size(); i++) {
                            if (totalEstates.get(i).getEstate().compareTo(totalEstates.get(i).getReality_estate()) > 0) {  //表示的是应该收的租金 大于租金。
                                BigDecimal different = totalEstates.get(i).getEstate().subtract(totalEstates.get(i).getReality_estate());  //表示的应该收的金额减去本次收的金额  就是当前月份应该收的金额
                                if (realityEstate.compareTo(different) <= 0) {
                                    totalEstates.get(i).setDeadline(assertEstate.getDeadline());
                                    totalEstates.get(i).setReality_estate(totalEstates.get(i).getReality_estate().add(realityEstate));
                                    totalEstateService.updateTotalEstate(totalEstates.get(i));
                                    break;
                                } else {
                                    totalEstates.get(i).setDeadline(assertEstate.getDeadline());
                                    totalEstates.get(i).setReality_estate(totalEstates.get(i).getEstate());
                                    totalEstateService.updateTotalEstate(totalEstates.get(i));
                                    realityEstate = realityEstate.subtract(different);
                                }
                            }

                        }
                    }
                }
            }
        } catch (Exception e) {
            resultCode.setMsg(e.getMessage());
            resultCode.setCode(-1);
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("操作成功");

        return resultCode;
    }


    @RequestMapping("/assertEstate/submit")
    @ResponseBody
    public ResultCode submit(Long id) {

        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();
            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());
            activitiService.startProcesser(id, processDefinitionKey, userInfo.getId());  //开启流程
            AssertEstate assertEstate = assertEstateService.getAssertEstateById(id);
            assertEstate.setStatus(ConstUtils.CheckING);  //表示的是已经提交状态
            assertEstateService.updateAssertEstate(assertEstate);
            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);
            for (Task task : tasks) {
                //2  通过任务对象获取流程实例
                ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                //3 通过流程实例获取“业务键”
                String businessKey = pi.getBusinessKey();
                if (businessKey != null && id.toString().equals(businessKey)) {
                    activitiService.completeTask(userInfo.getId().toString(), task.getId(), pi.getId(), "物业申请");
                }
            }
        } catch (Exception e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("提交成功");
        return resultCode;
    }


    @RequestMapping("/assertEstate/getComments")
    @ResponseBody
    public ResultCode getComment(Long id) {

        ResultCode resultCode = new ResultCode();

        List<Comment> data = null;
        try {
            data = activitiService.getProcessComments(id, processDefinitionKey);
        } catch (Exception e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setData(data);
        resultCode.setMsg("查询成功");

        return resultCode;
    }


    /**
     * 创建物流
     */
    @RequestMapping("/assertEstate/create")
    @ResponseBody
    public ResultCode createEstate(@Valid AssertEstate assertEstate, Errors errors) {

        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            if (assertEstate.getEstate_recivied() == null) {
                assertEstate.setEstate_recivied(BigDecimal.ZERO);
            }
            String property_leasing_num = assertEstate.getProperty_leasing_num();
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(property_leasing_num);
            assertEstate.setStatus(ConstUtils.CheckUNCOMINT);
            //修改合同的状态
            if (assertEstate.getEstate().compareTo(assertEstate.getEstate_recivied().add(assertEstate.getReality_estate())) <= 0) {  //表示的已经缴清
                assertEstate.setState(ConstUtils.ESTATESTATESUCCESS);
                propertyLeasing.setEstateState(ConstUtils.ESTATESTATESUCCESS);
            } else {
                assertEstate.setState(ConstUtils.ESTATESTATEFAIL);
                propertyLeasing.setEstateState(ConstUtils.ESTATESTATEFAIL);
            }
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
            int rows = assertEstateService.createAssertEstate(assertEstate);
            if (rows > 0) {
               /* TotalEstate totalEstate = new TotalEstate();
                totalEstate.setProperty_leasing_num(assertEstate.getProperty_leasing_num());
                totalEstate.setId(Long.parseLong(assertEstate.getYear_months()));
                TotalEstate totalEstate1=  totalEstateService.getTotalEstateById(Long.parseLong(assertEstate.getYear_months()));
                totalEstate.setReality_estate(assertEstate.getReality_estate().add(totalEstate1.getReality_estate()));
                totalEstate.setDeadline(new Date());
                totalEstateService.updateTotalEstate(totalEstate);*/
                resultCode.setCode(0);
                resultCode.setMsg("物业费创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("物业费创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("物业费创建失败");
            return resultCode;
        }

    }

    /**
     * 创建物流
     */
    @RequestMapping("/assertEstate/create2")
    @ResponseBody
    public ResultCode createEstate2(@Valid AssertEstate assertEstate, Errors errors) {

        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            if (assertEstate.getEstate_recivied() == null) {
                assertEstate.setEstate_recivied(BigDecimal.ZERO);
            }
            String property_leasing_num = assertEstate.getProperty_leasing_num();
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(property_leasing_num);

            if (assertEstate.getEstate().compareTo(assertEstate.getEstate_recivied().add(assertEstate.getReality_estate())) <= 0) {  //表示的已经缴清
                assertEstate.setState(ConstUtils.ESTATESTATESUCCESS);
                propertyLeasing.setEstateState(ConstUtils.ESTATESTATESUCCESS);
            } else {
                assertEstate.setState(ConstUtils.ESTATESTATEFAIL);
                propertyLeasing.setEstateState(ConstUtils.ESTATESTATEFAIL);
            }
            assertEstate.setStatus(ConstUtils.CheckUNCOMINT);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
            int rows = assertEstateService.createAssertEstate(assertEstate);
            if (rows > 0) {
               /* BigDecimal realityEstate = assertEstate.getReality_estate();
                List<TotalEstate> totalEstates = totalEstateService.getTotalEstateByLeasingNum(property_leasing_num);
                for (int i = 0; i < totalEstates.size(); i++) {
                    if (totalEstates.get(i).getEstate().compareTo(totalEstates.get(i).getReality_estate()) > 0) {  //表示的是应该收的租金 大于租金。
                        BigDecimal different = totalEstates.get(i).getEstate().subtract(totalEstates.get(i).getReality_estate());  //表示的应该收的金额减去本次收的金额  就是当前月份应该收的金额
                        if (realityEstate.compareTo(different) <= 0) {
                            totalEstates.get(i).setDeadline(assertEstate.getDeadline());
                            totalEstates.get(i).setReality_estate(totalEstates.get(i).getReality_estate().add(realityEstate));
                            totalEstateService.updateTotalEstate(totalEstates.get(i));
                            break;
                        } else {
                            totalEstates.get(i).setDeadline(assertEstate.getDeadline());
                            totalEstates.get(i).setReality_estate(totalEstates.get(i).getEstate());
                            totalEstateService.updateTotalEstate(totalEstates.get(i));
                            realityEstate = realityEstate.subtract(different);
                        }
                    }

                }*/
               /* TotalEstate totalEstate = new TotalEstate();
                totalEstate.setProperty_leasing_num(assertEstate.getProperty_leasing_num());
                totalEstate.setId(Long.parseLong(assertEstate.getYear_months()));
                totalEstate.setReality_estate(assertEstate.getReality_estate());
                totalEstate.setDeadline(new Date());
                totalEstateService.updateTotalEstate(totalEstate);*/
                resultCode.setCode(0);
                resultCode.setMsg("物业费创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("物业费创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("物业费创建失败");
            return resultCode;
        }

    }


    @RequestMapping("/assertEstate/find")
    @ResponseBody
    public AssertEstate getAssertEstateByLeasingNum(String id) {
        PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(Long.parseLong(id));
        //实际已经交的物业费
        BigDecimal rent_recivied = assertEstateService.getAssertEstateCountByLeasingNum(propertyLeasing.getProperty_leasing_num());
        if (rent_recivied == null) rent_recivied = BigDecimal.ZERO;
        //截止本月应收物业费
        BigDecimal rent_should = BigDecimal.ZERO;
        Date startRentDate = propertyLeasing.getRent_start_time();  //租金开始时间
        Date currentDate = new Date();  //表示的是当前时间
        int month = DateUtils.getDifMonth(startRentDate, currentDate) + 1;   //需要交物业费的月份
        rent_should = new BigDecimal(month).multiply(propertyLeasing.getEstate_charge_month());
        AssertEstate AssertEstate = new AssertEstate();
        AssertEstate.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
        AssertEstate.setRent_start_time(propertyLeasing.getRent_start_time());
        AssertEstate.setRent_end_time(propertyLeasing.getRent_end_time());
        AssertEstate.setEstate(rent_should);  //截止本月本次应该收多少钱
        AssertEstate.setEstate_recivied(rent_recivied);  //实际已经收了多少钱
        AssertEstate.setReality_estate(rent_should.subtract(rent_recivied));
        if (rent_should.compareTo(rent_recivied) <= 0) {
            AssertEstate.setState("9");  //表示的已经缴清
        } else {
            AssertEstate.setState("8");
        }
        return AssertEstate;
    }


    @RequestMapping("/assertEstate/downloadEstateInfol")
    public void downloadEstateInfol(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows, @RequestParam String state, @RequestParam String property_leasing_num,@RequestParam String status) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Page<AssertEstate> assertEstateList = assertEstateService.selectAssertEstateList(page, rows, property_leasing_num, state,status);
        //定义csv文件名称
        String csvFileName = "物业信息表";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv表头
        String colNames = "序号,租凭合同编号,本次收取费用开始时间,本次收取费用结束时间,截止本月应收物业费,实际已收物业费,本次实收物业费,收费时间,状态";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,rent_start_time,rent_end_time,estate,estate_recivied,reality_estate,deadline,state";
        //遍历保存查询数据集到dataList中
        for (AssertEstate assertEstate : assertEstateList.getRows()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertEstate.getId());
            map.put("property_leasing_num", assertEstate.getProperty_leasing_num());
            map.put("rent_start_time", sdf.format(assertEstate.getRent_start_time()));
            map.put("rent_end_time", sdf.format(assertEstate.getRent_end_time()));
            map.put("estate", assertEstate.getEstate());
            map.put("estate_recivied", assertEstate.getEstate_recivied());
            map.put("reality_estate", assertEstate.getReality_estate());
            map.put("deadline", sdf.format(assertEstate.getDeadline()));
            map.put("state", assertEstate.getState());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/assertEstate/downloadEstateInfolAll")
    public void downloadEstateInfolAll(HttpServletResponse response, @RequestParam String state, @RequestParam String property_leasing_num,@RequestParam String status) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        List<AssertEstate> assertEstateList = assertEstateService.selectAssertEstateList(property_leasing_num, state,status);
        //定义csv文件名称
        String csvFileName = "物业信息表";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv表头
        String colNames = "序号,租凭合同编号,本次收取费用开始时间,本次收取费用结束时间,截止本月应收物业费,实际已收物业费,本次实收物业费,收费时间,状态";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,rent_start_time,rent_end_time,estate,estate_recivied,reality_estate,deadline,state";
        //遍历保存查询数据集到dataList中
        for (AssertEstate assertEstate : assertEstateList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertEstate.getId());
            map.put("property_leasing_num", assertEstate.getProperty_leasing_num());
            map.put("rent_start_time", sdf.format(assertEstate.getRent_start_time()));
            map.put("rent_end_time", sdf.format(assertEstate.getRent_end_time()));
            map.put("estate", assertEstate.getEstate());
            map.put("estate_recivied", assertEstate.getEstate_recivied());
            map.put("reality_estate", assertEstate.getReality_estate());
            map.put("deadline", sdf.format(assertEstate.getDeadline()));
            map.put("state", assertEstate.getState());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


}
