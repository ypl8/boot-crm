package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.*;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class AssertLeasingController {

    // 依赖注入
    @Autowired
    private AssertLeasingService assertLeasingService;




    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private SystemService systemService;

    @Value("${check.state.type}")
    private String CHECK_STATE_TYPE;

    private String processDefinitionKey = "assertLeasing";


    @RequestMapping(value = "/assertLeasing")
    public String showAssertLeasing() {
        return "redirect:/assertLeasing/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertLeasing/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String assert_num, Model model,String status) {

        Page<AssertLeasing> assertLeasingPage = assertLeasingService.selectAssertLeasingList(page, rows, property_leasing_num, assert_num,status);
        model.addAttribute("page", assertLeasingPage);
        List<BaseDict> checkType = systemService.findBaseDictListByType(CHECK_STATE_TYPE);
        model.addAttribute("checkType", checkType);
        //参数回显
        model.addAttribute("status", status);
        model.addAttribute("assert_num", assert_num);
        model.addAttribute("property_leasing_num", property_leasing_num);
        return "assertLeasing";
    }

    @RequestMapping("/assertLeasing/edit")
    @ResponseBody
    public AssertLeasing getAssertLeasingById(Long id) {
        AssertLeasing assertLeasing = assertLeasingService.getAssertLeasingById(id);
        return assertLeasing;
    }


    @RequestMapping("/assertLeasing/update")
    @ResponseBody
    public ResultCode updateAssertLeasing(@Valid AssertLeasing assertLeasing, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            assertLeasing.setStatus(ConstUtils.CheckUNCOMINT);
            assertLeasingService.updateAssertLeasing(assertLeasing);
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("资产合同信息更新成功");
        return resultCode;
    }

    @RequestMapping("/assertLeasing/delete")
    @ResponseBody
    public String deleteAssertLeasing(Long id) {
        assertLeasingService.deleteAssertLeasing(id);
        return "OK";
    }


    /**
     * 创建水信息
     */
    @RequestMapping("/assertLeasing/create")
    @ResponseBody
    /*public String createDeposit(String property_leasing_num, String assert_num, String water_num, String watermeter_num, String deadline) {*/
    public ResultCode createDeposit(@Valid AssertLeasing assertLeasing, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            assertLeasing.setStatus(ConstUtils.CheckUNCOMINT);
            int rows = assertLeasingService.createAssertLeasing(assertLeasing);
            if (rows==-2147482646||rows > 0) {
                resultCode.setCode(0);
                resultCode.setMsg("资产合同信息创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("资产合同信息创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
    }



    @RequestMapping("/assertLeasing/changeState")
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


    @RequestMapping("/assertLeasing/submit")
    @ResponseBody
    public ResultCode submit(Long id) {

        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();
            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());
            activitiService.startProcesser(id, processDefinitionKey, userInfo.getId());  //开启流程
            AssertLeasing assertLeasing = assertLeasingService.getAssertLeasingById(id);
            assertLeasing.setStatus(ConstUtils.CheckING);  //表示的是已经提交状态
            assertLeasingService.updateAssertLeasing(assertLeasing);
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


    @RequestMapping("/assertLeasing/getComments")
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
