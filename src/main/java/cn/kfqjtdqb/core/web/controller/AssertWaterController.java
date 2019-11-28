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
public class AssertWaterController {

    // 依赖注入
    @Autowired
    private AssertWaterService assertWaterService;

    @Autowired
    private AssertWaterRentService assertWaterRentService;
    @Autowired
    private PropertyLeasingService propertyLeasingService;

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private SystemService systemService;

    private String processDefinitionKey = "assertWater";
    @Value("${check.state.type}")
    private String CHECK_STATE_TYPE;

    @RequestMapping(value = "/assertWater")
    public String showAssertWater() {
        return "redirect:/assertWater/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertWater/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String assert_num, Model model,String status) {

        Page<AssertWater> assertWaterPage = assertWaterService.selectAssertWaterList(page, rows, property_leasing_num, assert_num,status);
        model.addAttribute("page", assertWaterPage);
        List<BaseDict> checkType = systemService.findBaseDictListByType(CHECK_STATE_TYPE);
        model.addAttribute("checkType", checkType);

        //参数回显
        model.addAttribute("assert_num", assert_num);
        model.addAttribute("property_leasing_num", property_leasing_num);
        model.addAttribute("status", status);
        return "assertWater";
    }

    @RequestMapping("/assertWater/edit")
    @ResponseBody
    public AssertWater getAssertWaterById(Long id) {
        AssertWater assertWater = assertWaterService.getAssertWaterById(id);
        return assertWater;
    }


    @RequestMapping("/assertWater/update")
    @ResponseBody
    public ResultCode updateAssertWater(@Valid AssertWater assertWater, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            assertWater.setStatus(ConstUtils.CheckUNCOMINT);
            assertWaterService.updateAssertWater(assertWater);
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(assertWater.getProperty_leasing_num());
            if (isAssertWater(assertWater.getProperty_leasing_num(), assertWater.getAssert_num())) {
                propertyLeasing.setWaterState(ConstUtils.WATERSTATESUCCESSL);
            } else {
                propertyLeasing.setWaterState(ConstUtils.WATERSTATEFAIL);
            }
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("水信息更新失败");
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("水信息更新成功");
        return resultCode;
    }

    @RequestMapping("/assertWater/delete")
    @ResponseBody
    public String deleteAssertWater(Long id) {
        assertWaterService.deleteAssertWater(id);
        return "OK";
    }


    /**
     * 创建水信息
     */
    @RequestMapping("/assertWater/create")
    @ResponseBody
    /*public String createDeposit(String property_leasing_num, String assert_num, String water_num, String watermeter_num, String deadline) {*/
    public ResultCode createDeposit(@Valid AssertWater assertWater, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        BigDecimal maxWater = assertWaterService.getMaxWaterNum();

        if(maxWater==null){
            AssertLeasing assertLeasing=new AssertLeasing();
            assertLeasing.setAssert_num(assertWater.getAssert_num());
            assertLeasing.setProperty_leasing_num(assertWater.getProperty_leasing_num());
            assertLeasing=propertyLeasingService.selectAssertLeasingByAssertNum(assertLeasing);
            maxWater=assertLeasing.getWater_num();
        }

        if(assertWater.getWater_num().compareTo(maxWater)<=0){
            resultCode.setCode(-1);
            resultCode.setMsg("水表度数小于上次电表的度数");
            return resultCode;
        }
        try {
            assertWater.setStatus(ConstUtils.CheckUNCOMINT);
            int rows = assertWaterService.createAssertWater(assertWater);
            if (rows==-2147482646||rows > 0) {
                PropertyLeasing propertyLeasing = new PropertyLeasing();
                propertyLeasing.setProperty_leasing_num(assertWater.getProperty_leasing_num());
                if (isAssertWater(assertWater.getProperty_leasing_num(), assertWater.getAssert_num())) {
                    propertyLeasing.setWaterState(ConstUtils.WATERSTATESUCCESSL);
                } else {
                    propertyLeasing.setWaterState(ConstUtils.WATERSTATEFAIL);
                }
                propertyLeasingService.updatePropertyLeasing(propertyLeasing);
                resultCode.setCode(0);
                resultCode.setMsg("水信息创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("水信息创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("水信息创建失败");
            return resultCode;
        }
    }



    @RequestMapping("/assertWater/changeState")
    @ResponseBody
    public ResultCode changeState(Long id, String comment_state , @RequestParam(defaultValue = "") String comment) {
        String status = comment_state;
        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();

            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());

            // String processDefinitionKey = "assert";  //第一个版本


            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);

        /*    AssertWater assertWater = assertWaterService.getAssertWaterById(id);
            assertWater.setStatus(status);
            assertWaterService.updateAssertWater(assertWater);*/

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


    @RequestMapping("/assertWater/submit")
    @ResponseBody
    public ResultCode submit(Long id) {

        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();
            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());
            activitiService.startProcesser(id, processDefinitionKey, userInfo.getId());  //开启流程
            AssertWater assertWater = assertWaterService.getAssertWaterById(id);
            assertWater.setStatus(ConstUtils.CheckING);  //表示的是已经提交状态
            assertWaterService.updateAssertWater(assertWater);
            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);
            for (Task task : tasks) {
                //2  通过任务对象获取流程实例
                ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                //3 通过流程实例获取“业务键”
                String businessKey = pi.getBusinessKey();
                if (businessKey != null && id.toString().equals(businessKey)) {
                    activitiService.completeTask(userInfo.getId().toString(), task.getId(), pi.getId(), "水录入申请");
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


    @RequestMapping("/assertWater/getComments")
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

    @RequestMapping("/assertWater/find")
    @ResponseBody
    public ResultCode getAssertWaterByLeasingNum(String property_leasing_num, String assert_num) {
        List<AssertWater> assertWater = assertWaterService.selectAssertWaterListByAssertNum(property_leasing_num, assert_num,ConstUtils.CheckPASS);
        AssertWaterRent assertWaterRent = new AssertWaterRent();
        assertWaterRent.setProperty_leasing_num(property_leasing_num);
        assertWaterRent.setAssert_num(assert_num);
        BigDecimal water_rent_recivied = assertWaterRentService.getAssertWaterRentCountByLeasingNum(assertWaterRent);//表示的是实际已收的水费
        if (water_rent_recivied == null) {
            water_rent_recivied = BigDecimal.ZERO;
        }

        ResultCode resultCode=new ResultCode();
        assertWaterRent.setWater_rent_recivied(water_rent_recivied);  //实际已支付
        if (assertWater == null || assertWater.size() == 0) {
            resultCode.setCode(-1);
            resultCode.setMsg("没有审核成功录入的用水");
            return resultCode;
        } else {
            AssertLeasing assertLeasing = new AssertLeasing();
            assertLeasing.setProperty_leasing_num(property_leasing_num);
            assertLeasing.setAssert_num(assert_num);
            assertLeasing = propertyLeasingService.selectAssertLeasingByAssertNum(assertLeasing);
            if(!ConstUtils.CheckPASS.equals(assertLeasing.getStatus())){  //表示的是初始化水电未进行审核。
                resultCode.setCode(-1);
                resultCode.setMsg("初始化水电未进行审核");
                return resultCode;
            }
            BigDecimal initWaterNum = assertLeasing.getWater_num();
            BigDecimal water_num = assertWater.get(0).getWater_num();  // 表示的是当前的水表度数
            PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingByNum(property_leasing_num);
            BigDecimal water_rate = propertyLeasing.getWater_rate();   //一顿水多少钱。
            assertWaterRent.setWater_rent((water_num.subtract(initWaterNum)).multiply( water_rate));  //表示的是应该收的水费
            assertWaterRent.setWatermeter_num(assertWater.get(0).getWatermeter_num());  //表示的是水表号
            assertWaterRent.setWater_num(assertWater.get(0).getWater_num());   //表示的是当前的水号
            assertWaterRent.setDeadline(assertWater.get(0).getDeadline());  //截止时间
        }

        resultCode.setCode(0);
        resultCode.setData(assertWaterRent);
        return resultCode;
    }


    boolean isAssertWater(String property_leasing_num, String assert_num) {
        List<AssertWater> assertWater = assertWaterService.selectAssertWaterListByAssertNum(property_leasing_num, assert_num,null);
        AssertWaterRent assertWaterRent = new AssertWaterRent();
        assertWaterRent.setProperty_leasing_num(property_leasing_num);
        assertWaterRent.setAssert_num(assert_num);
        BigDecimal water_rent_recivied = assertWaterRentService.getAssertWaterRentCountByLeasingNum(assertWaterRent);//表示的是实际已收的水费
        if (water_rent_recivied == null) {
            water_rent_recivied = BigDecimal.ZERO;
        }
        if (assertWater == null || assertWater.size() == 0) {
            return true;
        } else {
            AssertLeasing assertLeasing = new AssertLeasing();
            assertLeasing.setProperty_leasing_num(property_leasing_num);
            assertLeasing.setAssert_num(assert_num);
            assertLeasing = propertyLeasingService.selectAssertLeasingByAssertNum(assertLeasing);
            BigDecimal initWaterNum = assertLeasing.getWater_num();
            BigDecimal water_num = assertWater.get(0).getWater_num();  // 表示的是当前的水表度数
            PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingByNum(property_leasing_num);
            BigDecimal water_rate = propertyLeasing.getWater_rate();   //一顿水多少钱。
            BigDecimal water_rent = (water_num .subtract(initWaterNum)).multiply( water_rate);  //表示的是应该收的水费
            if (water_rent.compareTo( water_rent_recivied)<=0) {
                return true;
            } else {
                return false;
            }
        }

    }

    @RequestMapping("/assertWater/downloadWater")
    public void downloadWater(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows, @RequestParam   String assert_num , @RequestParam  String property_leasing_num,String status) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Page<AssertWater> assertWaterPage = assertWaterService.selectAssertWaterList(page, rows,property_leasing_num,assert_num,status);
        //定义csv文件名称
        String csvFileName = "用水信息表";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv表头
        String colNames = "序号,租凭合同编号,资产编号,水表编号,水表度数,截止抄表时间";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,assert_num,watermeter_num,water_num,deadline";
        //遍历保存查询数据集到dataList中

        for (AssertWater assertWater : assertWaterPage.getRows()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertWater.getId());
            map.put("property_leasing_num", assertWater.getProperty_leasing_num());
            map.put("assert_num", assertWater.getAssert_num());
            map.put("watermeter_num",assertWater.getWatermeter_num());
            map.put("water_num", assertWater.getWater_num());
            map.put("deadline",sdf.format(assertWater.getDeadline()));
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/assertWater/downloadWaterAll")
    public void downloadWaterAll(HttpServletResponse response, @RequestParam   String assert_num , @RequestParam  String property_leasing_num,String status) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        List<AssertWater> assertWaters = assertWaterService.selectAssertWaterListByAssertNum(property_leasing_num,assert_num,status);
        //定义csv文件名称
        String csvFileName = "用水信息表";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv表头
        String colNames = "序号,租凭合同编号,资产编号,水表编号,水表度数,截止抄表时间";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,assert_num,watermeter_num,water_num,deadline";
        //遍历保存查询数据集到dataList中

        for (AssertWater assertWater : assertWaters) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertWater.getId());
            map.put("property_leasing_num", assertWater.getProperty_leasing_num());
            map.put("assert_num", assertWater.getAssert_num());
            map.put("watermeter_num",assertWater.getWatermeter_num());
            map.put("water_num", assertWater.getWater_num());
            map.put("deadline",sdf.format(assertWater.getDeadline()));
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }
}
