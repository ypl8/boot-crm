package cn.kfqjtdqb.core.web.controller;


import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.*;
import cn.kfqjtdqb.core.utils.Assert;
import cn.kfqjtdqb.core.utils.CSVUtils;


import cn.kfqjtdqb.core.utils.DgbSecurityUserHelper;
import cn.kfqjtdqb.core.utils.ErrorUtils;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class AssertInfolController {

    // 依赖注入
    @Autowired
    private AssertInfolService assertInfolService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AssertLeasingService assertLeasingService;

    public final Log logger = LogFactory.getLog(LogAop.class);


   // public static final String csv_assertInfol_upload_path = "D://java";
    public static final String csv_assertInfol_upload_path = "/home/csv/assertInfol";
    private static final String POINT_SUCCESS_URL = "/assertInfol/list.action";
    @Value("${floor.state.type}")
    private String STATE_TYPE;

    @Value("${check.state.type}")
    private String CHECK_STATE_TYPE;

    @Value("${assert.type}")
    private String  ASSERT_TYPE;

    @RequestMapping(value = "/assertInfol")
    public String showAssertInfol() {
        return "redirect:/assertInfol/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertInfol/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String assert_num, String property_leasing_num, String floor_state, String community_name, Model model, String status,String assertType) {
        /*if (community_name != null) {
            try {
                community_name = new String(community_name.getBytes("ISO8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }*/

        Page<AssertInfol> assertInfolList = assertInfolService.findAssertInfolList(page, rows, assert_num, floor_state, community_name, property_leasing_num, status,assertType);
        model.addAttribute("page", assertInfolList);
        List<BaseDict> stateType = systemService.findBaseDictListByType(STATE_TYPE);
        model.addAttribute("stateType", stateType);
        List<BaseDict> checkType = systemService.findBaseDictListByType(CHECK_STATE_TYPE);
        model.addAttribute("checkType", checkType);
        List<BaseDict>  assertStateType=systemService.findBaseDictListByType(ASSERT_TYPE);
        model.addAttribute("assertStateType", assertStateType);

        //参数回显
        model.addAttribute("community_name", community_name);
        model.addAttribute("assert_num", assert_num);
        model.addAttribute("status", status);
        model.addAttribute("assertType", assertType);
        model.addAttribute("floor_state", floor_state);
        model.addAttribute("property_leasing_num", property_leasing_num);
        return "assertInfol";
    }


    // 客户列表
    @RequestMapping(value = "/assertInfol/listCommunity")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String community_name, Model model) {

        Page<AssertInfolTotal> assertInfolList = assertInfolService.findAssertInfolListByCommunity(page, rows, community_name);
        model.addAttribute("page", assertInfolList);
        //参数回显
        model.addAttribute("community_name", community_name);

        return "totalAssertInfol";
    }

    @RequestMapping("/assertInfol/edit")
    @ResponseBody
    public AssertInfol getAssertInfolById(Long id) {
        AssertInfol assertInfol = assertInfolService.getAssertInfolById(id);
        return assertInfol;
    }


    @RequestMapping("/assertInfol/findByAssertNum")
    @ResponseBody
    public ResultCode getAssertInfolByAssertNum(String assert_num, String property_leasing_num) {
        AssertInfol assertInfol = assertInfolService.getAssertInfolByAssertNum(assert_num);
        List<AssertLeasing> assertLeasings = assertLeasingService.selectAssertLeasingListByAssertNum(property_leasing_num, assert_num);
        ResultCode resultCode = new ResultCode();
        if (assertLeasings != null && assertLeasings.size() == 1) {   //查找初始化审核记录有没有通过
            AssertLeasing assertLeasing = assertLeasings.get(0);
            if (ConstUtils.CheckPASS.equals(assertLeasing.getStatus())) {
                resultCode.setData(assertInfol);
                resultCode.setCode(0);
                return resultCode;
            }
        } else {
            resultCode.setMsg("请先初始化水电，并进行审核成功");
            resultCode.setCode(-1);
            return resultCode;
        }
        resultCode.setMsg("请先初始化水电，并进行审核成功");
        resultCode.setCode(-1);
        return resultCode;

    }

    @RequestMapping("/assertInfol/show")
    @ResponseBody
    public AssertInfol getAssertInfol(Long id) {
        AssertInfol assertInfol = assertInfolService.findAssertWithPropertyLeasing(id);
        return assertInfol;
    }

    @RequestMapping("/assertInfol/getAllBuildNum")
    @ResponseBody
    public ResultCode getAllBuildNum(String community_name, @RequestParam(defaultValue = "") String floor_state) {
        List<String> buildNums = assertInfolService.findAllBuildNum(community_name, floor_state);
        ResultCode resultCode = new ResultCode();

        if (buildNums == null || buildNums.size() == 0) {
            resultCode.setCode(-1);
            resultCode.setMsg("该小区没有对应未出租的栋号");
        } else {
            resultCode.setCode(0);
            resultCode.setData(buildNums);
        }
        return resultCode;
    }

    @RequestMapping("/assertInfol/getAllRentalLocation")
    @ResponseBody
    public ResultCode getAllRentalLocation(String community_name, String building_num, @RequestParam(defaultValue = "") String floor_state) {
        List<String> rentalLocations = assertInfolService.findAllRoomNum(community_name, building_num, floor_state);
        ResultCode resultCode = new ResultCode();

        if (rentalLocations == null || rentalLocations.size() == 0) {
            resultCode.setCode(-1);
            resultCode.setMsg("该栋没有对应未出租的资产");
        } else {
            resultCode.setCode(0);
            resultCode.setData(rentalLocations);
        }
        return resultCode;
    }


    @RequestMapping("/assertInfol/update")
    @ResponseBody
    public ResultCode updateAssertInfol(@Valid AssertInfol assertInfol, Errors errors) {

        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            // 获取错误信息
            List<FieldError> errorList = errors.getFieldErrors();
            for (FieldError error : errorList) {
                // 打印字段错误信息
                stringBuilder.append("字段 :" + error.getField() + "错误信息" + error.getDefaultMessage());
            }
            resultCode.setCode(-1);
            resultCode.setMsg(stringBuilder.toString());
            return resultCode;
        }
        assertInfol.setStatus(ConstUtils.CheckUNCOMINT);

        try {
            assertInfolService.updateAssertInfol(assertInfol);
        } catch (Exception e) {
            resultCode.setMsg(e.getMessage());
            resultCode.setCode(0);
            return resultCode;
        }

     /*   UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();
        UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());
        String processDefinitionKey = "assert";
        activitiService.startProcesser(assertInfol.getId(), processDefinitionKey,userInfo.getId());  //开启流程*/
        // activitiService.startProcesser(assertInfol.getId());
        resultCode.setMsg("资产信息修改成功");
        resultCode.setCode(0);
        return resultCode;
    }

    @RequestMapping("/assertInfol/delete")
    @ResponseBody
    public String deleteAssertInfol(Long id) {
        AssertInfol assertInfol = assertInfolService.findAssertWithPropertyLeasing(id);
        if (assertInfol != null && assertInfol.getPropertyLeasings() != null && assertInfol.getPropertyLeasings().size() > 0) {  //表示的是存在关联关系
            return "FALSE";
        }
        assertInfolService.deleteAssertInfol(id);
        return "OK";
    }


    /**
     * 创建资产
     */
    @RequestMapping("/assertInfol/create")
    @ResponseBody
    /* public ResultCode caseCreate( String assert_num,  String card_num, String community_name, String building_num,  String room_number, String floorage, String floor_state, String watermeter_num, String electricmeter_num) {*/
    public ResultCode caseCreate(@Valid AssertInfol assertInfol, Errors errors) {

        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        assertInfol.setStatus(ConstUtils.CheckUNCOMINT);

        try {
            int rows = assertInfolService.createAssertInfol(assertInfol);
            if (rows > 0||rows==-2147482646) {
                /* taskService.complete(list.get(0).getId());*/
                //  activitiService.startProcesser(assertInfol.getId());

                resultCode.setCode(0);
                resultCode.setMsg("资产信息创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("资产信息创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }


    }


    @RequestMapping("/assertInfol/changeState")
    @ResponseBody
    public ResultCode changeState(Long id, String comment_state, @RequestParam(defaultValue = "") String comment) {
        String status = comment_state;
        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();

            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());

            // String processDefinitionKey = "assert";  //第一个版本

            String processDefinitionKey = "assert";

            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);

          /*  AssertInfol assertInfol = assertInfolService.getAssertInfolById(id);
            assertInfol.setStatus(status);
            assertInfolService.updateAssertInfol(assertInfol);*/

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


    @RequestMapping("/assertInfol/assertSubmit")
    @ResponseBody
    public ResultCode assertSubmit(Long id) {
        String processDefinitionKey = "assert";
        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();
            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());
            activitiService.startProcesser(id, processDefinitionKey, userInfo.getId());  //开启流程
            AssertInfol assertInfol = assertInfolService.getAssertInfolById(id);
            assertInfol.setStatus(ConstUtils.CheckING);
            assertInfolService.updateAssertInfol(assertInfol);
            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);
            for (Task task : tasks) {
                //2  通过任务对象获取流程实例
                ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                //3 通过流程实例获取“业务键”
                String businessKey = pi.getBusinessKey();
                if (businessKey != null && id.toString().equals(businessKey)) {
                    activitiService.completeTask(userInfo.getId().toString(), task.getId(), pi.getId(), "资产申请");
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


    @RequestMapping("/assertInfol/getComments")
    @ResponseBody
    public ResultCode getComment(Long id) {

        ResultCode resultCode = new ResultCode();
        String processDefinitionKey = "assert";
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


    @RequestMapping("/assertInfol/showUpload")
    public ModelAndView showUpload() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("assertInfolUpload");
        return mv;
    }

    @RequestMapping("/assertInfol/uploadAssertInfol")
    public ModelAndView uploadPoint(HttpServletRequest request, @RequestParam MultipartFile uploadFile) {
        ModelAndView mv = new ModelAndView();
        try {
            String url = csv_assertInfol_upload_path;
            String fileName = uploadFile.getOriginalFilename();
            Assert.isEmpty(fileName, "请先上传csv文件");
            File file = new File(url, fileName);
            uploadFile.transferTo(file);
            ArrayList<String[]> dateList = CSVUtils.csvReader(file);
            List<AssertInfol> assertInfols=new ArrayList<AssertInfol>();
            //根据制定贷款线索csv文件表头含义，将字段逐个保存到实体中并保存到数据库
            for (int i = 0; i < dateList.size(); i++) {
                String[] content = dateList.get(i);
                AssertInfol assertInfol = new AssertInfol();
                assertInfol.setAssert_num(content[1]);
                assertInfol.setCard_num(content[2]);
                assertInfol.setCommunity_name(content[3]);
                assertInfol.setBuilding_num(content[4]);
                assertInfol.setRoom_number(content[5]);
                assertInfol.setFloorage(Double.parseDouble(content[6]));
                assertInfol.setFloor_state(content[7]);
                assertInfol.setWatermeter_num(content[8]);
                assertInfol.setElectricmeter_num(content[9]);
                assertInfol.setAssertType(content[10]);
                assertInfol.setRemark(content[11]);
                assertInfol.setStatus(ConstUtils.CheckUNCOMINT);
                assertInfols.add(assertInfol);
            }
            assertInfolService.createAssertInfolAll(assertInfols);
            String successMsg = "资产信息csv文件导入成功";
            mv.addObject("msg", successMsg);
            mv.addObject("url", POINT_SUCCESS_URL);
            mv.setViewName("success");
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = "资产信息csv导入失败,原因:" + e.getMessage();
            mv.addObject("msg", errorMsg);
            mv.setViewName("error");
        }

        return mv;

    }


    @RequestMapping("/assertInfol/downloadAssertInfol")
    public void downloadAssertInfol(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows,
                                    @RequestParam String assert_num, @RequestParam String floor_state, @RequestParam String community_name, @RequestParam String property_leasing_num, String status,String assertType) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Page<AssertInfol> assertInfolList = assertInfolService.findAssertInfolList(page, rows, assert_num, floor_state, community_name, property_leasing_num, status,assertType);
        //定义csv文件名称
        String csvFileName = "资产信息表";
        //定义csv表头
        String colNames = "序号,资产编号,产证编号,小区名称,栋号,房号/店面号,建筑面积,房屋状态,水表号,电表号,资产类型,备注";
        //定义表头对应字段的key
        String mapKey = "id,assert_num,card_num,community_name,building_num,room_number,floorage,floor_state,watermeter_num,electricmeter_num,assertType,remark";
        //遍历保存查询数据集到dataList中
        for (AssertInfol assertInfol : assertInfolList.getRows()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertInfol.getId());
            map.put("assert_num", assertInfol.getAssert_num());
            map.put("card_num", assertInfol.getCard_num());
            map.put("community_name", assertInfol.getCommunity_name());
            map.put("building_num", assertInfol.getBuilding_num());
            map.put("room_number", assertInfol.getRoom_number());
            map.put("floorage", assertInfol.getFloorage());
            map.put("floor_state", assertInfol.getFloor_state());
            map.put("watermeter_num", assertInfol.getWatermeter_num());
            map.put("electricmeter_num", assertInfol.getElectricmeter_num());
            map.put("assertType", assertInfol.getAssertType());
            map.put("remark", assertInfol.getRemark());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/assertInfol/downloadAssertInfolAll")
    public void downloadAssertInfolAll(HttpServletResponse response, @RequestParam String
            assert_num, @RequestParam String floor_state, String community_name) {

       /* if (community_name != null) {
            try {
                community_name = new String(community_name.getBytes("ISO8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }*/
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        List<AssertInfol> assertInfolList = assertInfolService.findAssertInfolListAll(assert_num, floor_state, community_name);
        //定义csv文件名称
        String csvFileName = "资产信息表";
        //定义csv表头
        String colNames = "序号,资产编号,产证编号,小区名称,栋号,房号/店面号,建筑面积,房屋状态,水表号,电表号,资产类型,备注";
        //定义表头对应字段的key
        String mapKey = "id,assert_num,card_num,community_name,building_num,room_number,floorage,floor_state,watermeter_num,electricmeter_num,assertType,remark";
        //遍历保存查询数据集到dataList中
        for (AssertInfol assertInfol : assertInfolList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", assertInfol.getId());
            map.put("assert_num", assertInfol.getAssert_num());
            map.put("card_num", assertInfol.getCard_num());
            map.put("community_name", assertInfol.getCommunity_name());
            map.put("building_num", assertInfol.getBuilding_num());
            map.put("room_number", assertInfol.getRoom_number());
            map.put("floorage", assertInfol.getFloorage());
            map.put("floor_state", assertInfol.getFloor_state());
            map.put("watermeter_num", assertInfol.getWatermeter_num());
            map.put("electricmeter_num", assertInfol.getElectricmeter_num());
            map.put("assertType", assertInfol.getAssertType());
            map.put("remark", assertInfol.getRemark());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }

    @RequestMapping("/assertInfol/downloadCommunityAssertInfol")
    public void downloadCommunityAssertInfol(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows,
                                             @RequestParam String community_name) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Page<AssertInfolTotal> assertInfolList = assertInfolService.findAssertInfolListByCommunity(page, rows, community_name);
        //定义csv文件名称
        String csvFileName = "资产信息统计表";
        //定义csv表头
        String colNames = "小区编号,资产总数,资产出租个数,资产空闲个数,资产非法占用,出租率";
        //定义表头对应字段的key
        String mapKey = "community_name,countSum,countRented,countUnrent,countOccupy,assertRate";
        //遍历保存查询数据集到dataList中
        for (AssertInfolTotal assertInfolTotal : assertInfolList.getRows()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("community_name", assertInfolTotal.getCommunity_name());
            map.put("countSum", assertInfolTotal.getCountSum());
            map.put("countRented", assertInfolTotal.getCountRented());
            map.put("countUnrent", assertInfolTotal.getCountUnrent());
            map.put("countOccupy", assertInfolTotal.getCountOccupy());
            map.put("assertRate", assertInfolTotal.getAssertRate());

            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/assertInfol/downloadCommunityAssertInfolAll")
    public void downloadCommunityAssertInfolAll(HttpServletResponse response, @RequestParam String community_name) {

        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        List<AssertInfolTotal> assertInfolList = assertInfolService.findAssertInfolListByCommunity(community_name);
        //定义csv文件名称
        String csvFileName = "资产信息统计表";
        //定义csv表头
        String colNames = "小区编号,资产总数,资产出租个数,资产空闲个数,资产非法占用,出租率";
        //定义表头对应字段的key
        String mapKey = "community_name,countSum,countRented,countUnrent,countOccupy,assertRate";
        //遍历保存查询数据集到dataList中
        for (AssertInfolTotal assertInfolTotal : assertInfolList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("community_name", assertInfolTotal.getCommunity_name());
            map.put("countSum", assertInfolTotal.getCountSum());
            map.put("countRented", assertInfolTotal.getCountRented());
            map.put("countUnrent", assertInfolTotal.getCountUnrent());
            map.put("countOccupy", assertInfolTotal.getCountOccupy());
            map.put("assertRate", assertInfolTotal.getAssertRate());

            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }

}
