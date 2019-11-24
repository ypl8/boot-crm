package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.common.utils.StringUtils;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.*;
import cn.kfqjtdqb.core.utils.CSVUtils;
import cn.kfqjtdqb.core.utils.DgbSecurityUserHelper;
import cn.kfqjtdqb.core.utils.ErrorUtils;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AssertPowerController {

    // 依赖注入
    @Autowired
    private AssertPowerService assertPowerService;
    @Autowired
    private AssertPowerRentService assertPowerRentService;

    @Autowired
    private PropertyLeasingService propertyLeasingService;

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RuntimeService runtimeService;

    private String processDefinitionKey = "assertPower";

    @Autowired
    private SystemService systemService;

    @Value("${check.state.type}")
    private String CHECK_STATE_TYPE;
    @RequestMapping(value = "/assertPower")
    public String showAssertPower() {
        return "redirect:/assertPower/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertPower/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String assert_num, String status, Model model) {
        List<BaseDict> checkType = systemService.findBaseDictListByType(CHECK_STATE_TYPE);
        model.addAttribute("checkType", checkType);
        Page<AssertPower> assertPowerPage = assertPowerService.selectAssertPowerList(page, rows, property_leasing_num, assert_num, status);
        model.addAttribute("page", assertPowerPage);
        //参数回显
        model.addAttribute("assert_num", assert_num);
        model.addAttribute("status", status);
        model.addAttribute("property_leasing_num", property_leasing_num);
        return "assertPower";
    }

    @RequestMapping("/assertPower/edit")
    @ResponseBody
    public AssertPower getAssertPowerById(Long id) {
        AssertPower assertPower = assertPowerService.getAssertPowerById(id);
        return assertPower;
    }


    @RequestMapping("/assertPower/update")
    @ResponseBody
    public ResultCode updateAssertPower(@Valid AssertPower assertPower, Errors errors) {

        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            assertPower.setStatus(ConstUtils.CheckUNCOMINT);
            assertPowerService.updateAssertPower(assertPower);
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(assertPower.getProperty_leasing_num());
            if (isAssertPower(assertPower.getProperty_leasing_num(), assertPower.getAssert_num())) {
                propertyLeasing.setPowerState(ConstUtils.POWERSTATESUCCESS);
            } else {
                propertyLeasing.setPowerState(ConstUtils.POWERSTATEFAIL);
            }
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("电信息更新失败");
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("电信息更新成功");
        return resultCode;
    }

    @RequestMapping("/assertPower/delete")
    @ResponseBody
    public String deleteAssertPower(Long id) {
        assertPowerService.deleteAssertPower(id);
        return "OK";
    }


    /**
     * 创建电信息
     */
    @RequestMapping("/assertPower/create")
    @ResponseBody
    public ResultCode createAssertPower(@Valid AssertPower assertPower, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }

        BigDecimal maxPower = assertPowerService.getMaxPowerNum();

        if(maxPower==null){
            AssertLeasing assertLeasing=new AssertLeasing();
            assertLeasing.setAssert_num(assertPower.getAssert_num());
            assertLeasing.setProperty_leasing_num(assertPower.getProperty_leasing_num());
            assertLeasing=propertyLeasingService.selectAssertLeasingByAssertNum(assertLeasing);
            maxPower=assertLeasing.getElectric_num();

        }

        if(assertPower.getPower_num().compareTo(maxPower)<=0){
            resultCode.setCode(-1);
            resultCode.setMsg("电表度数小于上次电表的度数");
            return resultCode;
        }
        try {
            int rows = assertPowerService.createAssertPower(assertPower);
            if (rows > 0) {
                PropertyLeasing propertyLeasing = new PropertyLeasing();
                propertyLeasing.setProperty_leasing_num(assertPower.getProperty_leasing_num());
                if (isAssertPower(assertPower.getProperty_leasing_num(), assertPower.getAssert_num())) {
                    propertyLeasing.setPowerState(ConstUtils.POWERSTATESUCCESS);
                } else {
                    propertyLeasing.setPowerState(ConstUtils.POWERSTATEFAIL);
                }
                assertPower.setStatus(ConstUtils.CheckUNCOMINT);
                propertyLeasingService.updatePropertyLeasing(propertyLeasing);
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


    @RequestMapping("/assertPower/changeState")
    @ResponseBody
    public ResultCode changeState(Long id, String comment_state, @RequestParam(defaultValue = "") String comment) {
        String status = comment_state;
        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();

            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());

            // String processDefinitionKey = "assert";  //第一个版本


            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);

       /*     AssertPower assertPower = assertPowerService.getAssertPowerById(id);
            assertPower.setStatus(status);
            assertPowerService.updateAssertPower(assertPower);*/

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


    @RequestMapping("/assertPower/submit")
    @ResponseBody
    public ResultCode submit(Long id) {

        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();
            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());
            activitiService.startProcesser(id, processDefinitionKey, userInfo.getId());  //开启流程
            AssertPower assertPower = assertPowerService.getAssertPowerById(id);
            assertPower.setStatus(ConstUtils.CheckING);  //表示的是已经提交状态
            assertPowerService.updateAssertPower(assertPower);
            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);
            for (Task task : tasks) {
                //2  通过任务对象获取流程实例
                ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                //3 通过流程实例获取“业务键”
                String businessKey = pi.getBusinessKey();
                if (businessKey != null && id.toString().equals(businessKey)) {
                    activitiService.completeTask(userInfo.getId().toString(), task.getId(), pi.getId(), "电费录入申请");
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


    @RequestMapping("/assertPower/getComments")
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


    /*@RequestMapping("/assertDeposit/find")
    @ResponseBody
    public AssertDeposit getAssertDepositByLeasingNum(String property_leasing_num) {
        AssertDeposit assertDeposit = assertDepositService.getAssertDepositByLeasingNum(property_leasing_num);
        return assertDeposit;
    }*/

    @RequestMapping("/assertPower/find")
    @ResponseBody
    public ResultCode getAssertWaterByLeasingNum(String property_leasing_num, String assert_num) {
        List<AssertPower> assertPowers = assertPowerService.selectAssertPowerListByAssertNum(property_leasing_num, assert_num, ConstUtils.CheckPASS);  //查询所有的资产是否审核通过。
        AssertPowerRent assertPowerRent = new AssertPowerRent();
        assertPowerRent.setProperty_leasing_num(property_leasing_num);
        assertPowerRent.setAssert_num(assert_num);
        BigDecimal power_rent_recivied = assertPowerRentService.getAssertPowerRentCountByLeasingNum(assertPowerRent);//表示的是实际已收的水费
        if (power_rent_recivied == null) {
            power_rent_recivied = BigDecimal.ZERO;
        }
        ResultCode resultCode = new ResultCode();
        assertPowerRent.setPower_rent_recivied(power_rent_recivied);  //实际已支付
        if (assertPowers == null || assertPowers.size() == 0) {  //表示的是没有审核成功的录入电费
            resultCode.setCode(-1);
            resultCode.setMsg("没有审核成功录入的用电");
            return resultCode;
        } else {
            AssertLeasing assertLeasing = new AssertLeasing();
            assertLeasing.setProperty_leasing_num(property_leasing_num);
            assertLeasing.setAssert_num(assert_num);
            assertLeasing = propertyLeasingService.selectAssertLeasingByAssertNum(assertLeasing);
            if (!ConstUtils.CheckPASS.equals(assertLeasing.getStatus())) {  //表示的是初始化水电未进行审核。
                resultCode.setCode(-1);
                resultCode.setMsg("初始化水电未进行审核");
                return resultCode;
            }
            BigDecimal initPowerNum = assertLeasing.getElectric_num();
            BigDecimal power_num = assertPowers.get(0).getPower_num();  // 表示的是当前的电表度数
            PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingByNum(property_leasing_num);
            BigDecimal power_rate = propertyLeasing.getPower_rate();   //一顿水多少钱。
            assertPowerRent.setPower_rent((power_num.subtract(initPowerNum)).multiply(power_rate));  //表示的是应该收的水费
            assertPowerRent.setPowermeter_num(assertPowers.get(0).getPowermeter_num());  //表示的是水表号
            assertPowerRent.setPower_num(assertPowers.get(0).getPower_num());   //表示的是当前的水号
            assertPowerRent.setDeadline(assertPowers.get(0).getDeadline());  //截止时间
        }

        resultCode.setCode(0);
        resultCode.setData(assertPowerRent);
        return resultCode;

    }


    boolean isAssertPower(String property_leasing_num, String assert_num) {
        List<AssertPower> assertPowers = assertPowerService.selectAssertPowerListByAssertNum(property_leasing_num, assert_num, "2");
        AssertPowerRent assertPowerRent = new AssertPowerRent();
        assertPowerRent.setProperty_leasing_num(property_leasing_num);
        assertPowerRent.setAssert_num(assert_num);
        BigDecimal power_rent_recivied = assertPowerRentService.getAssertPowerRentCountByLeasingNum(assertPowerRent);//表示的是实际已收的水费
        if (power_rent_recivied == null) {
            power_rent_recivied = BigDecimal.ZERO;
        }
        if (assertPowers == null || assertPowers.size() == 0) {
            return true;
        } else {
            AssertLeasing assertLeasing = new AssertLeasing();
            assertLeasing.setProperty_leasing_num(property_leasing_num);
            assertLeasing.setAssert_num(assert_num);
            assertLeasing = propertyLeasingService.selectAssertLeasingByAssertNum(assertLeasing);
            BigDecimal initPowerNum = assertLeasing.getElectric_num();
            BigDecimal power_num = assertPowers.get(0).getPower_num();  // 表示的是当前的电表度数
            PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingByNum(property_leasing_num);
            BigDecimal power_rate = propertyLeasing.getPower_rate();   //一顿水多少钱。
            BigDecimal power_rent = power_num.subtract(initPowerNum).multiply(power_rate);  //表示的是应该收的水费
            if (power_rent.compareTo(power_rent_recivied) <= 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    @RequestMapping("/assertPower/downloadPower")
    public void downloadPower(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows, @RequestParam String assert_num, @RequestParam String property_leasing_num, @RequestParam String status) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Page<AssertPower> assertPowerPage = assertPowerService.selectAssertPowerList(page, rows, property_leasing_num, assert_num, status);
        //定义csv文件名称
        String csvFileName = "用电信息表";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv表头
        String colNames = "序号,租凭合同编号,资产编号,电表编号,电表度数,截止抄表时间";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,assert_num,powermeter_num,power_num,deadline";
        //遍历保存查询数据集到dataList中

        for (AssertPower assertPower : assertPowerPage.getRows()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertPower.getId());
            map.put("property_leasing_num", assertPower.getProperty_leasing_num());
            map.put("assert_num", assertPower.getAssert_num());
            map.put("powermeter_num", assertPower.getPowermeter_num());
            map.put("power_num", assertPower.getPower_num());
            map.put("deadline", assertPower.getDeadline());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/assertPower/downloadPowerAll")
    public void downloadPowerAll(HttpServletResponse response, @RequestParam String assert_num, @RequestParam String property_leasing_num, @RequestParam String status) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        List<AssertPower> assertPowers = assertPowerService.selectAssertPowerListByAssertNum(property_leasing_num, assert_num, status);
        //定义csv文件名称
        String csvFileName = "用电信息表";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv表头
        String colNames = "序号,租凭合同编号,资产编号,电表编号,电表度数,截止抄表时间";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,assert_num,powermeter_num,power_num,deadline";
        //遍历保存查询数据集到dataList中

        for (AssertPower assertPower : assertPowers) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertPower.getId());
            map.put("property_leasing_num", assertPower.getProperty_leasing_num());
            map.put("assert_num", assertPower.getAssert_num());
            map.put("powermeter_num", assertPower.getPowermeter_num());
            map.put("power_num", assertPower.getPower_num());
            map.put("deadline", assertPower.getDeadline());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


}
