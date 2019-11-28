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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AssertDepositController {
    // 依赖注入
    @Autowired
    private AssertDepositService assertDepositService;

    @Autowired
    private SystemService systemService;

    @Value("${deposit.state.type}")
    private String STATE_TYPE;

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RuntimeService runtimeService;


    private String processDefinitionKey = "deposit";

    @Autowired
    private PropertyLeasingService propertyLeasingService;

    @Value("${check.state.type}")
    private String CHECK_STATE_TYPE;
    @RequestMapping(value = "/assertDeposit")
    public String showAssertDeposit() {
        return "redirect:/assertDeposit/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertDeposit/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String state, Model model,String status) {

        Page<AssertDeposit> assertInfolList = assertDepositService.findAssertDepositList(page, rows, property_leasing_num, state,status);
        model.addAttribute("page", assertInfolList);
        List<BaseDict> stateType = systemService.findBaseDictListByType(STATE_TYPE);
        List<BaseDict> checkType = systemService.findBaseDictListByType(CHECK_STATE_TYPE);
        model.addAttribute("checkType", checkType);
        model.addAttribute("stateType", stateType);
        model.addAttribute("state", state);
        model.addAttribute("status", status);
        //参数回显
        model.addAttribute("property_leasing_num", property_leasing_num);
        return "assertDeposit";
    }

    @RequestMapping("/assertDeposit/edit")
    @ResponseBody
    public AssertDeposit getAssertDepositById(Long id) {
        AssertDeposit assertDeposit = assertDepositService.getAssertDepositById(id);
        return assertDeposit;
    }


    @RequestMapping("/assertDeposit/update")
    @ResponseBody
    public ResultCode updateAssertDeposit(@Valid AssertDeposit assertDeposit, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return  ErrorUtils.getRsult(errors);
        }
        PropertyLeasing propertyLeasing = new PropertyLeasing();
        propertyLeasing.setProperty_leasing_num(assertDeposit.getProperty_leasing_num());
        if (assertDeposit.getDeposit() .compareTo(assertDeposit.getReality_deposit())<=0) {  //表示的是缴清
            assertDeposit.setState(ConstUtils.DEPOSITSTATESUCCESS);
            propertyLeasing.setDepositState(ConstUtils.DEPOSITSTATESUCCESS);
        } else {  //表示的是未缴清
            assertDeposit.setState(ConstUtils.DEPOSITSTATEFAIL);
            propertyLeasing.setDepositState(ConstUtils.DEPOSITSTATEFAIL);
        }
        try {
            assertDeposit.setStatus(ConstUtils.CheckUNCOMINT);
            assertDepositService.updateAssertDeposit(assertDeposit);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setMsg("押金修改成功");
        resultCode.setCode(0);
        return resultCode;

    }

    @RequestMapping("/assertDeposit/delete")
    @ResponseBody
    public String deleteAssertDeposit(Long id) {
        assertDepositService.deleteAssertDeposit(id);
        return "OK";
    }


    /**
     * 创建资产
     */
    @RequestMapping("/assertDeposit/create")
    @ResponseBody
    public ResultCode createDeposit(@Valid AssertDeposit assertDeposit, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return  ErrorUtils.getRsult(errors);
        }
        try {
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(assertDeposit.getProperty_leasing_num());
            if (assertDeposit.getDeposit() .compareTo( assertDeposit.getReality_deposit())<=0) {  //表示的是缴清
                assertDeposit.setState(ConstUtils.DEPOSITSTATESUCCESS);
                propertyLeasing.setDepositState(ConstUtils.DEPOSITSTATESUCCESS);
            } else {  //表示的是未缴清
                assertDeposit.setState(ConstUtils.DEPOSITSTATEFAIL);
                propertyLeasing.setDepositState(ConstUtils.DEPOSITSTATEFAIL);
            }
            assertDeposit.setState(ConstUtils.CheckUNCOMINT);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
            int rows = assertDepositService.createAssertDeposit(assertDeposit);
            if (rows==-2147482646||rows > 0) {
                resultCode.setCode(0);
                resultCode.setMsg("押金创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("押金创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("押金创建失败");
            return resultCode;
        }
    }


    @RequestMapping("/assertDeposit/find")
    @ResponseBody
    public AssertDeposit getAssertDepositByLeasingNum(String property_leasing_num) {
        AssertDeposit assertDeposit = assertDepositService.getAssertDepositByLeasingNum(property_leasing_num);
        return assertDeposit;
    }


    @RequestMapping("/assertDeposit/downloadDepositInfol")
    public void downloadDepositInfol(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows, @RequestParam   String state , @RequestParam  String property_leasing_num,String status) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Page<AssertDeposit> assertDepositList = assertDepositService.findAssertDepositList(page, rows,property_leasing_num,state,status);
        //定义csv文件名称
        String csvFileName = "押金信息表";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv表头
        String colNames = "序号,租凭合同编号,本次收取费用开始时间,本次收取费用结束时间,应收保证金,实收押金,收费时间,状态";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,rent_start_time,rent_end_time,deposit,deposit_recivied,reality_deposit,deadline,state";
        //遍历保存查询数据集到dataList中
        for (AssertDeposit assertDeposit : assertDepositList.getRows()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertDeposit.getId());
            map.put("property_leasing_num", assertDeposit.getProperty_leasing_num());
            map.put("rent_start_time", sdf.format(assertDeposit.getRent_start_time()));
            map.put("rent_end_time", sdf.format(assertDeposit.getRent_end_time()));
            map.put("deposit", assertDeposit.getDeposit());
            map.put("reality_deposit", assertDeposit.getReality_deposit());
            map.put("deadline", sdf.format(assertDeposit.getDeadline()));
            map.put("state", assertDeposit.getState());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/assertDeposit/downloadDepositInfolAll")
    public void downloadDepositInfolAll(HttpServletResponse response, @RequestParam   String state , @RequestParam  String property_leasing_num) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        List<AssertDeposit> assertDepositList = assertDepositService.selectAssertDepositList(property_leasing_num,state);
        //定义csv文件名称
        String csvFileName = "押金信息表";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv表头
        String colNames = "序号,租凭合同编号,本次收取费用开始时间,本次收取费用结束时间,应收保证金,实收押金,收费时间,状态";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,rent_start_time,rent_end_time,deposit,reality_deposit,deadline,state";
        //遍历保存查询数据集到dataList中
        for (AssertDeposit assertDeposit : assertDepositList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertDeposit.getId());
            map.put("property_leasing_num", assertDeposit.getProperty_leasing_num());
            map.put("rent_start_time", sdf.format(assertDeposit.getRent_start_time()));
            map.put("rent_end_time",  sdf.format(assertDeposit.getRent_end_time()));
            map.put("deposit", assertDeposit.getDeposit());
            map.put("reality_deposit", assertDeposit.getReality_deposit());
            map.put("deadline",  sdf.format(assertDeposit.getDeadline()));
            map.put("state", assertDeposit.getState());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }



    @RequestMapping("/assertDeposit/changeState")
    @ResponseBody
    public ResultCode changeState(Long id, String comment_state , @RequestParam(defaultValue = "") String comment) {
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


    @RequestMapping("/assertDeposit/submit")
    @ResponseBody
    public ResultCode submit(Long id) {

        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();
            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());
            activitiService.startProcesser(id, processDefinitionKey, userInfo.getId());  //开启流程
            AssertDeposit assertDeposit = assertDepositService.getAssertDepositById(id);
            assertDeposit.setStatus(ConstUtils.CheckING);  //表示的是已经提交状态
            assertDepositService.updateAssertDeposit(assertDeposit);
            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);
            for (Task task : tasks) {
                //2  通过任务对象获取流程实例
                ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                //3 通过流程实例获取“业务键”
                String businessKey = pi.getBusinessKey();
                if (businessKey != null && id.toString().equals(businessKey)) {
                    activitiService.completeTask(userInfo.getId().toString(), task.getId(), pi.getId(), "押金审核处理");
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


    @RequestMapping("/assertDeposit/getComments")
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


}
