package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.common.utils.StringUtils;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.*;
import cn.kfqjtdqb.core.service.AssertWaterRentService;
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
public class AssertWaterRentController {

    // 依赖注入
    @Autowired
    private AssertWaterRentService AssertWaterRentService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private PropertyLeasingService propertyLeasingService;

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RuntimeService runtimeService;

    private String processDefinitionKey = "assertWaterRental";

    @Value("${water.state.type}")
    private String STATE_TYPE;
    @Value("${check.state.type}")
    private String CHECK_STATE_TYPE;

    @RequestMapping(value = "/assertWaterRent")
    public String showAssertWaterRent() {
        return "redirect:/assertWaterRent/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertWaterRent/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String state, String assert_num, Model model,String status) {

        Page<AssertWaterRent> AssertWaterRentList = AssertWaterRentService.selectAssertWaterRentList(page, rows, property_leasing_num, assert_num, state,status);
        model.addAttribute("page", AssertWaterRentList);
        List<BaseDict> stateType = systemService.findBaseDictListByType(STATE_TYPE);
        model.addAttribute("stateType", stateType);
        List<BaseDict> checkType = systemService.findBaseDictListByType(CHECK_STATE_TYPE);
        model.addAttribute("checkType", checkType);
        model.addAttribute("status", status);
        //参数回显
        model.addAttribute("state", state);
        model.addAttribute("assert_num", assert_num);
        model.addAttribute("property_leasing_num", property_leasing_num);
        return "assertWaterRent";
    }

    @RequestMapping("/assertWaterRent/edit")
    @ResponseBody
    public AssertWaterRent getAssertWaterRentById(Long id) {
        AssertWaterRent AssertWaterRent = AssertWaterRentService.getAssertWaterRentById(id);
        return AssertWaterRent;
    }

    /*   String id, String property_leasing_num,String assert_num ,String state, String water_rent, String water_num,String reality_water_rent, String rent_start_time,
       String rent_end_time, String water_rent_received,String watermeter_num,String deadline*/
    @RequestMapping("/assertWaterRent/update")
    @ResponseBody
    public ResultCode updateAssertWaterRent(@Valid AssertWaterRent assertWaterRent, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            if (assertWaterRent.getWater_rent_recivied() == null) {
                assertWaterRent.setWater_rent_recivied(BigDecimal.ZERO);
            }
            BigDecimal water_rent_recivied = assertWaterRent.getWater_rent_recivied();  // 表示的是已经收的租金
            BigDecimal reality_water_rent = assertWaterRent.getReality_water_rent();  //表示的本次收的租金
            BigDecimal waterRent = assertWaterRent.getWater_rent();   //表示的是本次应收租金
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(assertWaterRent.getProperty_leasing_num());
            if (waterRent.compareTo( water_rent_recivied.add( reality_water_rent))<=0) {  //表示的是已经缴清
                assertWaterRent.setState(ConstUtils.WATERSTATESUCCESSL);
                propertyLeasing.setWaterState(ConstUtils.WATERSTATESUCCESSL);
            } else {
                assertWaterRent.setState(ConstUtils.WATERSTATEFAIL);
                propertyLeasing.setWaterState(ConstUtils.WATERSTATEFAIL);
            }
            assertWaterRent.setStatus(ConstUtils.CheckUNCOMINT);
            AssertWaterRentService.updateAssertWaterRent(assertWaterRent);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);   //存在问题
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("水费信息创建失败");
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("水费信息创建成功");
        return resultCode;

    }

    @RequestMapping("/assertWaterRent/delete")
    @ResponseBody
    public String deleteAssertWaterRent(Long id) {
        AssertWaterRentService.deleteAssertWaterRent(id);
        return "OK";
    }


    @RequestMapping("/assertWaterRent/changeState")
    @ResponseBody
    public ResultCode changeState(Long id, String comment_state , @RequestParam(defaultValue = "") String comment) {
        String status = comment_state;
        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();

            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());

            // String processDefinitionKey = "assert";  //第一个版本


            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);

           /* AssertWaterRent assertWaterRent = AssertWaterRentService.getAssertWaterRentById(id);
            assertWaterRent.setStatus(status);
            AssertWaterRentService.updateAssertWaterRent(assertWaterRent);*/

            for (Task task : tasks) {
                //2  通过任务对象获取流程实例
                ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                //3 通过流程实例获取“业务键”
                String businessKey = pi.getBusinessKey();
                if (businessKey != null && id.toString().equals(businessKey)) {
                    activitiService.completeTask(userInfo.getId().toString(), task.getId(), status, pi.getId(), comment);
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


    @RequestMapping("/assertWaterRent/submit")
    @ResponseBody
    public ResultCode submit(Long id) {

        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();
            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());
            activitiService.startProcesser(id, processDefinitionKey, userInfo.getId());  //开启流程
            AssertWaterRent assertWaterRent = AssertWaterRentService.getAssertWaterRentById(id);
            assertWaterRent.setStatus(ConstUtils.CheckING);  //表示的是已经提交状态
            AssertWaterRentService.updateAssertWaterRent(assertWaterRent);
            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);
            for (Task task : tasks) {
                //2  通过任务对象获取流程实例
                ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                //3 通过流程实例获取“业务键”
                String businessKey = pi.getBusinessKey();
                if (businessKey != null && id.toString().equals(businessKey)) {
                    activitiService.completeTask(userInfo.getId().toString(), task.getId(), pi.getId(), "水费申请");
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


    @RequestMapping("/assertWaterRent/getComments")
    @ResponseBody
    public ResultCode getComment(Long id) {

        ResultCode resultCode = new ResultCode();

        List<Comment> data = null;
        try {
            data = activitiService.getProcessComments(id,processDefinitionKey);
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
     * 创建资产
     */
    @RequestMapping("/assertWaterRent/create")
    @ResponseBody
    public ResultCode createRental(@Valid AssertWaterRent assertWaterRent, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            if (assertWaterRent.getWater_rent_recivied() == null) {
                assertWaterRent.setWater_rent_recivied(BigDecimal.ZERO);
            }
            BigDecimal water_rent_recivied = assertWaterRent.getWater_rent_recivied();  // 表示的是已经收的租金
            BigDecimal reality_water_rent = assertWaterRent.getReality_water_rent();  //表示的本次收的租金
            BigDecimal waterRent = assertWaterRent.getWater_rent();   //表示的是本次应收租金
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(assertWaterRent.getProperty_leasing_num());
            if (waterRent .compareTo( water_rent_recivied .add( reality_water_rent))<=0) {  //表示的是已经缴清
                assertWaterRent.setState(ConstUtils.WATERSTATESUCCESSL);
                propertyLeasing.setWaterState(ConstUtils.WATERSTATESUCCESSL);
            } else {
                assertWaterRent.setState(ConstUtils.WATERSTATEFAIL);
                propertyLeasing.setWaterState(ConstUtils.WATERSTATEFAIL);
            }
            assertWaterRent.setStatus(ConstUtils.CheckUNCOMINT);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);   //存在问题
            int rows = AssertWaterRentService.createAssertWaterRent(assertWaterRent);
            if (rows > 0) {
                resultCode.setCode(0);
                resultCode.setMsg("电信息创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("电信息创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("电信息创建失败");
            return resultCode;
        }

    }


/*    @RequestMapping("/assertWaterRent/find")
    @ResponseBody
    public AssertWaterRent getAssertWaterRentByLeasingNum(String id) {
        PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(Long.parseLong(id));
        //实际已经交的租金
        Double rent_recivied = AssertWaterRentService.getAssertWaterRentCountByLeasingNum(propertyLeasing.getProperty_leasing_num());
        if(rent_recivied==null)  rent_recivied=0d;
        //截止本月应收租金
        double rent_should = 0;
        Date startRentDate = propertyLeasing.getRent_start_time();  //租金开始时间
        Date currentDate = new Date();  //表示的是当前时间
        int month = DateUtils.getDifMonth(startRentDate, currentDate) + 1;   //需要交押金的月份
        int freePriod = propertyLeasing.getRent_free_period();  //获取免租期;
        if (month > propertyLeasing.getRent_period() * 12) {   //表示的是租期已经到了
            rent_should = propertyLeasing.getTotal_rent();
        } else {   //表示的是租期没有到
            if (freePriod - month >= 0) {   //表示的是没有过免租期
                rent_should = 0;
            } else {  //表示的是过了免租期
                String monthRental = propertyLeasing.getMonthly_rental();
                String[] months = monthRental.split(",");
                if (months.length == 1) {  //表示的是每月的租金一样的。
                    rent_should = Double.parseDouble(months[0]) * (month - freePriod);
                } else {   //表示的是每个月的租金不一样。
                    double allRent = 0;
                    for (int i = freePriod + 1; i <= month; i++) {
                        allRent += RentUtils.getMonthRent(i, months);
                    }
                    rent_should = allRent;
                }
            }
        }
        AssertWaterRent AssertWaterRent = new AssertWaterRent();
        AssertWaterRent.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
        AssertWaterRent.setRent_start_time(propertyLeasing.getRent_start_time());
        AssertWaterRent.setRent_end_time(propertyLeasing.getRent_end_time());
        AssertWaterRent.setRental(rent_should);  //截止本月本次应该收多少钱
        AssertWaterRent.setRent_recivied(rent_recivied);  //实际已经收了多少钱
        AssertWaterRent.setReality_rental(rent_should - rent_recivied);
        if (rent_should >= rent_recivied) {
            AssertWaterRent.setState("7");  //表示的已经缴清
        } else {
            AssertWaterRent.setState("6");
        }
        return assertWaterRent;
    }*/


    @RequestMapping("/assertWaterRent/downloadWaterRent")
    public void downloadWaterRent(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows, @RequestParam   String state , @RequestParam  String property_leasing_num,@RequestParam String assert_num,String status) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Page<AssertWaterRent> assertWaterRentPage = AssertWaterRentService.selectAssertWaterRentList(page, rows,property_leasing_num,assert_num,state,status);
        //定义csv文件名称
        String csvFileName = "水费信息表";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv表头
        String colNames = "序号,租凭合同编号,资产编号,本次收取费用开始时间,本次收取费用结束时间,截止本月应收水费,实际已收水费,本次实收水费,收费时间,状态";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,assert_num,rent_start_time,rent_end_time,water_rent,water_rent_recivied,reality_water_rent,deadline,state";
        //遍历保存查询数据集到dataList中
        for (AssertWaterRent assertWaterRent : assertWaterRentPage.getRows()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertWaterRent.getId());
            map.put("property_leasing_num", assertWaterRent.getProperty_leasing_num());
            map.put("assert_num", assertWaterRent.getAssert_num());
            map.put("rent_start_time", sdf.format(assertWaterRent.getRent_start_time()));
            map.put("rent_end_time", sdf.format(assertWaterRent.getRent_end_time()));
            map.put("water_rent", assertWaterRent.getWater_rent());
            map.put("water_rent_recivied", assertWaterRent.getWater_rent_recivied());
            map.put("reality_water_rent", assertWaterRent.getReality_water_rent());
            map.put("deadline", sdf.format(assertWaterRent.getDeadline()));
            map.put("state", assertWaterRent.getState());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/assertWaterRent/downloadWaterRentAll")
    public void downloadWaterRentAll(HttpServletResponse response, @RequestParam   String state , @RequestParam  String property_leasing_num, @RequestParam String assert_num,String status) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        List<AssertWaterRent> assertWaterRents = AssertWaterRentService.selectAssertWaterRentList(property_leasing_num,assert_num,state,status);
        //定义csv文件名称
        String csvFileName = "水费信息表";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv表头
        String colNames = "序号,租凭合同编号,资产编号,本次收取费用开始时间,本次收取费用结束时间,截止本月应收水费,实际已收水费,本次实收水费,收费时间,状态";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,assert_num,rent_start_time,rent_end_time,water_rent,water_rent_recivied,reality_water_rent,deadline,state";
        //遍历保存查询数据集到dataList中
        for (AssertWaterRent assertWaterRent : assertWaterRents) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertWaterRent.getId());
            map.put("property_leasing_num", assertWaterRent.getProperty_leasing_num());
            map.put("assert_num", assertWaterRent.getAssert_num());
            map.put("rent_start_time", sdf.format(assertWaterRent.getRent_start_time()));
            map.put("rent_end_time", sdf.format(assertWaterRent.getRent_end_time()));
            map.put("water_rent", assertWaterRent.getWater_rent());
            map.put("water_rent_recivied", assertWaterRent.getWater_rent_recivied());
            map.put("reality_water_rent", assertWaterRent.getReality_water_rent());
            map.put("deadline", sdf.format(assertWaterRent.getDeadline()));
            map.put("state", assertWaterRent.getState());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }

}
