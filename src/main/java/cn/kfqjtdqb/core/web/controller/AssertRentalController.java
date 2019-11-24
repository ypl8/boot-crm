package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.common.utils.Page;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AssertRentalController {

    // 依赖注入
    @Autowired
    private AssertRentalService assertRentalService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private PropertyLeasingService propertyLeasingService;


    @Autowired
    private TotalRentalService totalRentalService;


    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RuntimeService runtimeService;


    private String processDefinitionKey = "rental";
    @Value("${rental.state.type}")
    private String STATE_TYPE;

    @Value("${check.state.type}")
    private String CHECK_STATE_TYPE;

    @RequestMapping(value = "/assertRental")
    public String showAssertRental() {
        return "redirect:/assertRental/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertRental/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String state, Model model,String status) {

        Page<AssertRental> assertRentalList = assertRentalService.selectAssertRentalList(page, rows, property_leasing_num, state,status);
        model.addAttribute("page", assertRentalList);
        List<BaseDict> stateType = systemService.findBaseDictListByType(STATE_TYPE);
        model.addAttribute("stateType", stateType);
        List<BaseDict> checkType = systemService.findBaseDictListByType(CHECK_STATE_TYPE);
        model.addAttribute("checkType", checkType);

        //参数回显
        model.addAttribute("property_leasing_num", property_leasing_num);
        model.addAttribute("state", state);
        model.addAttribute("status", status);
        return "assertRental";
    }

    @RequestMapping("/assertRental/edit")
    @ResponseBody
    public RentalVo getAssertRentalById(Long id) {
        AssertRental assertRental = assertRentalService.getAssertRentalById(id);
        RentalVo rentalVo = new RentalVo();
        rentalVo.setId(assertRental.getId());
        rentalVo.setRental(assertRental.getRental());
        rentalVo.setRent_recivied(assertRental.getRent_recivied());
        rentalVo.setReality_rental(assertRental.getReality_rental());
        rentalVo.setProperty_leasing_num(assertRental.getProperty_leasing_num());
        rentalVo.setRent_end_time(assertRental.getRent_end_time());
        List<TotalRental> totalRentals = totalRentalService.getTotalRentalByLeasingNum(assertRental.getProperty_leasing_num());
    /*    List<TotalRental> results = new ArrayList<TotalRental>();
        for (int i = 0; i < totalRentals.size(); i++) {   //添加数据
            if (totalRentals.get(i).getReality_rental() < totalRentals.get(i).getRental()) {
                results.add(totalRentals.get(i));
            }
        }*/

        rentalVo.setDeadline(assertRental.getDeadline());
        rentalVo.setState(assertRental.getState());
        rentalVo.setTotalRentals(totalRentals);
        rentalVo.setRent_start_time(assertRental.getRent_start_time());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String day = simpleDateFormat.format(assertRental.getRent_start_time()).substring(6, 8);
        rentalVo.setDay(day);
        return rentalVo;
    }

    /*   String id, String property_leasing_num, String state, String rental, String reality_rental, String rent_start_time,
       String rent_end_time, String rent_received*/
    @RequestMapping("/assertRental/update")
    @ResponseBody
    public ResultCode updateAssertRental(@Valid AssertRental assertRental, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            if (assertRental.getRent_recivied() == null) {
                assertRental.setRent_recivied(BigDecimal.ZERO);
            }
            String property_leasing_num = assertRental.getProperty_leasing_num();
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(property_leasing_num);
            if (assertRental.getRental().compareTo(assertRental.getRent_recivied().add(assertRental.getReality_rental())) <= 0) {  //表示的已经缴清
                assertRental.setState(ConstUtils.RENTALSTATESUCCESS);
                propertyLeasing.setRentalState(ConstUtils.RENTALSTATESUCCESS);
            } else {
                assertRental.setState(ConstUtils.RENTALSTATEFAIL);
                propertyLeasing.setRentalState(ConstUtils.RENTALSTATEFAIL);
            }
            assertRental.setStatus(ConstUtils.CheckUNCOMINT);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
            assertRentalService.updateAssertRental(assertRental);
            TotalRental totalRental = new TotalRental();
            totalRental.setProperty_leasing_num(property_leasing_num);
            totalRental.setId(Long.parseLong(assertRental.getYear_months()));
            totalRental.setReality_rental(assertRental.getReality_rental());
            totalRental.setDeadline(new Date());
            totalRentalService.updateTotalRental(totalRental);
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("租金信息更新成功");
        return resultCode;

    }

    @RequestMapping("/assertRental/delete")
    @ResponseBody
    public String deleteAssertRental(Long id) {
        assertRentalService.deleteAssertRental(id);
        return "OK";
    }


    /**
     * 创建租金
     */
    @RequestMapping("/assertRental/create")
    @ResponseBody
    public ResultCode createRental(@Valid AssertRental assertRental, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            String property_leasing_num = assertRental.getProperty_leasing_num();
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(property_leasing_num);
            if (assertRental.getRental().compareTo(assertRental.getRent_recivied().add(assertRental.getReality_rental())) <= 0) {  //表示的已经缴清
                assertRental.setState(ConstUtils.RENTALSTATESUCCESS);
                propertyLeasing.setRentalState(ConstUtils.RENTALSTATESUCCESS);
            } else {
                assertRental.setState(ConstUtils.RENTALSTATEFAIL);
                propertyLeasing.setRentalState(ConstUtils.RENTALSTATEFAIL);
            }
            assertRental.setStatus(ConstUtils.CheckUNCOMINT);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
            int rows = assertRentalService.createAssertRental(assertRental);
            if (rows > 0) {
               /* TotalRental totalRental = new TotalRental();
                totalRental.setProperty_leasing_num(property_leasing_num);
                totalRental.setId(Long.parseLong(assertRental.getYear_months()));
                *//*      totalRental.setReality_rental(assertRental.getReality_rental());*//*
                totalRental.setDeadline(assertRental.getDeadline());
                TotalRental totalRental1 = totalRentalService.getTotalRentalById(Long.parseLong(assertRental.getYear_months()));
                totalRental.setReality_rental(totalRental1.getReality_rental().add(assertRental.getReality_rental()));
                totalRentalService.updateTotalRental(totalRental);*/
                resultCode.setCode(0);
                resultCode.setMsg("租金创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("租金创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
    }


    /**
     * 创建租金
     */
    @RequestMapping("/assertRental/create2")
    @ResponseBody
    public ResultCode createRental2(@Valid AssertRental assertRental, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            String property_leasing_num = assertRental.getProperty_leasing_num();
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(property_leasing_num);
            if (assertRental.getRental().compareTo(assertRental.getRent_recivied().add(assertRental.getReality_rental())) <= 0) {  //表示的已经缴清
                assertRental.setState(ConstUtils.RENTALSTATESUCCESS);
                propertyLeasing.setRentalState(ConstUtils.RENTALSTATESUCCESS);
            } else {
                assertRental.setState(ConstUtils.RENTALSTATEFAIL);
                propertyLeasing.setRentalState(ConstUtils.RENTALSTATEFAIL);
            }
            assertRental.setStatus(ConstUtils.CheckUNCOMINT);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
            int rows = assertRentalService.createAssertRental(assertRental);

            if (rows > 0) {
               /* BigDecimal realityRental = assertRental.getReality_rental();
                List<TotalRental> totalRentals = totalRentalService.getTotalRentalByLeasingNum(property_leasing_num);

                for (int i = 0; i < totalRentals.size(); i++) {
                    if (totalRentals.get(i).getRental().compareTo(totalRentals.get(i).getReality_rental()) > 0) {  //表示的是应该收的租金 大于租金。
                        BigDecimal different = totalRentals.get(i).getRental().subtract(totalRentals.get(i).getReality_rental());  //表示的应该收的金额减去本次收的金额  就是当前月份应该收的金额
                        if (realityRental.compareTo(different) <= 0) {
                            totalRentals.get(i).setDeadline(assertRental.getDeadline());
                            totalRentals.get(i).setReality_rental(totalRentals.get(i).getReality_rental().add(realityRental));
                            totalRentalService.updateTotalRental(totalRentals.get(i));
                            break;
                        } else {
                            totalRentals.get(i).setDeadline(assertRental.getDeadline());
                            totalRentals.get(i).setReality_rental(totalRentals.get(i).getRental());
                            totalRentalService.updateTotalRental(totalRentals.get(i));
                            realityRental = realityRental.subtract(different);
                        }
                    }

                }*/
               /* TotalRental totalRental = new TotalRental();
                totalRental.setProperty_leasing_num(property_leasing_num);
                totalRental.setId(Long.parseLong(assertRental.getYear_months()));
                totalRental.setReality_rental(assertRental.getReality_rental());*/

                resultCode.setCode(0);
                resultCode.setMsg("租金创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("租金创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
    }


    @RequestMapping("/assertRental/changeState")
    @ResponseBody
    public ResultCode changeState(Long id, String comment_state, @RequestParam(defaultValue = "") String comment) {
        String status = comment_state;
        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();

            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());

            // String processDefinitionKey = "assert";  //第一个版本

            AssertRental assertRental = assertRentalService.getAssertRentalById(id);

            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);

         /*   AssertRental assertRental = assertRentalService.getAssertRentalById(id);
            assertRental.setStatus(status);
            assertRentalService.updateAssertRental(assertRental);*/

            for (Task task : tasks) {
                //2  通过任务对象获取流程实例
                ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                //3 通过流程实例获取“业务键”
                String businessKey = pi.getBusinessKey();
                if (businessKey != null && id.toString().equals(businessKey)) {
                    activitiService.completeTask(userInfo.getId().toString(), task.getId(), status, pi.getId(), comment);
                    if(ConstUtils.CheckPASS.equals(status)){
                       /* TotalRental totalRental = new TotalRental();
                        totalRental.setProperty_leasing_num(assertRental.getProperty_leasing_num());
                        totalRental.setId(Long.parseLong(assertRental.getYear_months()));
                        *//*      totalRental.setReality_rental(assertRental.getReality_rental());*//*
                        totalRental.setDeadline(assertRental.getDeadline());
                        TotalRental totalRental1 = totalRentalService.getTotalRentalById(Long.parseLong(assertRental.getYear_months()));
                        totalRental.setReality_rental(totalRental1.getReality_rental().add(assertRental.getReality_rental()));
                        totalRentalService.updateTotalRental(totalRental);*/
                        BigDecimal realityRental = assertRental.getReality_rental();
                        List<TotalRental> totalRentals = totalRentalService.getTotalRentalByLeasingNum(assertRental.getProperty_leasing_num());

                        for (int i = 0; i < totalRentals.size(); i++) {
                            if (totalRentals.get(i).getRental().compareTo(totalRentals.get(i).getReality_rental()) > 0) {  //表示的是应该收的租金 大于租金。
                                BigDecimal different = totalRentals.get(i).getRental().subtract(totalRentals.get(i).getReality_rental());  //表示的应该收的金额减去本次收的金额  就是当前月份应该收的金额
                                if (realityRental.compareTo(different) <= 0) {
                                    totalRentals.get(i).setDeadline(assertRental.getDeadline());
                                    totalRentals.get(i).setReality_rental(totalRentals.get(i).getReality_rental().add(realityRental));
                                    totalRentalService.updateTotalRental(totalRentals.get(i));
                                    break;
                                } else {
                                    totalRentals.get(i).setDeadline(assertRental.getDeadline());
                                    totalRentals.get(i).setReality_rental(totalRentals.get(i).getRental());
                                    totalRentalService.updateTotalRental(totalRentals.get(i));
                                    realityRental = realityRental.subtract(different);
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


    @RequestMapping("/assertRental/submit")
    @ResponseBody
    public ResultCode submit(Long id) {

        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();
            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());
            activitiService.startProcesser(id, processDefinitionKey, userInfo.getId());  //开启流程
            AssertRental assertRental = assertRentalService.getAssertRentalById(id);
            assertRental.setStatus(ConstUtils.CheckING);
            assertRentalService.updateAssertRental(assertRental);
            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);
            for (Task task : tasks) {
                //2  通过任务对象获取流程实例
                ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                //3 通过流程实例获取“业务键”
                String businessKey = pi.getBusinessKey();
                if (businessKey != null && id.toString().equals(businessKey)) {
                    activitiService.completeTask(userInfo.getId().toString(), task.getId(), pi.getId(), "租金申请");
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


    @RequestMapping("/assertRental/getComments")
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


    @RequestMapping("/assertRental/find")
    @ResponseBody
    public AssertRental getAssertRentalByLeasingNum(String id) {
        PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(Long.parseLong(id));
        //实际已经交的租金
        BigDecimal rent_recivied = assertRentalService.getAssertRentalCountByLeasingNum(propertyLeasing.getProperty_leasing_num());
        if (rent_recivied == null) rent_recivied = BigDecimal.ZERO;
        //截止本月应收租金
        BigDecimal rent_should = BigDecimal.ZERO;
        Date currentDate = new Date();  //表示的是当前时间
        rent_should = new BigDecimal(RentUtils.getToalRentByCurrentDate(propertyLeasing, currentDate) + "");
        AssertRental assertRental = new AssertRental();
        assertRental.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
        assertRental.setRent_start_time(propertyLeasing.getRent_start_time());
        assertRental.setRent_end_time(propertyLeasing.getRent_end_time());
        assertRental.setRental(rent_should);  //截止本月本次应该收多少钱
        assertRental.setRent_recivied(rent_recivied);  //实际已经收了多少钱
        assertRental.setReality_rental(rent_should.subtract(rent_recivied));

        if (rent_should.compareTo(rent_recivied) <= 0) {
            assertRental.setState("7");  //表示的已经缴清
        } else {
            assertRental.setState("6");
        }
        return assertRental;
    }

    @RequestMapping("/assertRental/downloadAssertRental")
    public void downloadAssertRental(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows,
                                     String property_leasing_num, String state,String status) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Page<AssertRental> assertRentalList = assertRentalService.selectAssertRentalList(page, rows, property_leasing_num, state,status);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv文件名称
        String csvFileName = "租金收入信息表";
        //定义csv表头
        String colNames = "序号,资产编号,收取租金开始时间,收取租金结束时间,截止当前已收租金,截止本月应收租金,当前收取租金,状态";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,rent_start_time,rent_end_time,rent_recivied,rental,reality_rental,state";
        //遍历保存查询数据集到dataList中
        for (AssertRental assertRental : assertRentalList.getRows()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertRental.getId());

            map.put("property_leasing_num", assertRental.getProperty_leasing_num());
            map.put("rent_start_time", sdf.format(assertRental.getRent_start_time()));
            map.put("rent_end_time", sdf.format(assertRental.getRent_end_time()));
            map.put("rent_recivied", assertRental.getRent_recivied());
            map.put("rental", assertRental.getRental());
            map.put("reality_rental", assertRental.getReality_rental());
            map.put("state", assertRental.getState());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/assertRental/downloadAssertRentalAll")
    public void downloadAssertRentalAll(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows,
                                        String property_leasing_num, String state,String status) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        List<AssertRental> assertRentalList = assertRentalService.selectAssertRentalAllList(property_leasing_num, state,status);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv文件名称
        String csvFileName = "租金收入信息表";
        //定义csv表头
        String colNames = "序号,资产编号,收取租金开始时间,收取租金结束时间,截止当前已收租金,截止本月应收租金,当前收取租金,状态";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,rent_start_time,rent_end_time,rent_recivied,rental,reality_rental,state";
        //遍历保存查询数据集到dataList中
        for (AssertRental assertRental : assertRentalList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertRental.getId());
            map.put("property_leasing_num", assertRental.getProperty_leasing_num());
            map.put("rent_start_time", sdf.format(assertRental.getRent_start_time()));
            map.put("rent_end_time", sdf.format(assertRental.getRent_end_time()));
            map.put("rent_recivied", assertRental.getRent_recivied());
            map.put("rental", assertRental.getRental());
            map.put("reality_rental", assertRental.getReality_rental());
            map.put("state", assertRental.getState());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


}
