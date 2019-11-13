package cn.kfqjtdqb.core.web.controller;


import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.bean.AssertInfolTotal;
import cn.kfqjtdqb.core.bean.BaseDict;
import cn.kfqjtdqb.core.bean.ResultCode;
import cn.kfqjtdqb.core.service.AssertInfolService;
import cn.kfqjtdqb.core.service.SystemService;
import cn.kfqjtdqb.core.utils.Assert;
import cn.kfqjtdqb.core.utils.CSVUtils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
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

@Controller
public class AssertInfolController {

    // 依赖注入
    @Autowired
    private AssertInfolService assertInfolService;
    @Autowired
    private SystemService systemService;

    public static final String csv_assertInfol_upload_path = "D://csv//assertInfol";
    private static final String POINT_SUCCESS_URL = "/assertInfol/list.action";
    @Value("${floor.state.type}")
    private String STATE_TYPE;

    @RequestMapping(value = "/assertInfol")
    public String showAssertInfol() {
        return "redirect:/assertInfol/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertInfol/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                     String assert_num, String property_leasing_num, String floor_state, String community_name, Model model) {
        /*if (community_name != null) {
            try {
                community_name = new String(community_name.getBytes("ISO8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }*/
        Page<AssertInfol> assertInfolList = assertInfolService.findAssertInfolList(page, rows, assert_num, floor_state, community_name,property_leasing_num);
        model.addAttribute("page", assertInfolList);
        List<BaseDict> stateType = systemService.findBaseDictListByType(STATE_TYPE);
        model.addAttribute("stateType", stateType);
        //参数回显
        model.addAttribute("community_name", community_name);
        model.addAttribute("assert_num", assert_num);
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
    public AssertInfol getAssertInfolByAssertNum(String assert_num) {
        AssertInfol assertInfol = assertInfolService.getAssertInfolByAssertNum(assert_num);
        return assertInfol;
    }

    @RequestMapping("/assertInfol/show")
    @ResponseBody
    public AssertInfol getAssertInfol(Long id) {
        AssertInfol assertInfol = assertInfolService.findAssertWithPropertyLeasing(id);
        return assertInfol;
    }
    @RequestMapping("/assertInfol/getAllBuildNum")
    @ResponseBody
    public ResultCode getAllBuildNum(String community_name,@RequestParam(defaultValue = "") String  floor_state) {
        List<String> buildNums = assertInfolService.findAllBuildNum(community_name,floor_state);
        ResultCode  resultCode=new ResultCode();

        if(buildNums==null||buildNums.size()==0){
            resultCode.setCode(-1);
            resultCode.setMsg("该小区没有对应未出租的栋号");
        }else{
            resultCode.setCode(0);
            resultCode.setData(buildNums);
        }
        return resultCode ;
    }

    @RequestMapping("/assertInfol/getAllRentalLocation")
    @ResponseBody
    public ResultCode getAllRentalLocation(String community_name,String building_num,@RequestParam(defaultValue = "") String  floor_state) {
        List<String> rentalLocations = assertInfolService.findAllRoomNum(community_name,building_num,floor_state);
        ResultCode  resultCode=new ResultCode();

        if(rentalLocations==null||rentalLocations.size()==0){
            resultCode.setCode(-1);
            resultCode.setMsg("该栋没有对应未出租的资产");
        }else{
            resultCode.setCode(0);
            resultCode.setData(rentalLocations);
        }
        return resultCode ;
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
        assertInfolService.updateAssertInfol(assertInfol);
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
            StringBuilder stringBuilder = new StringBuilder();
            // 获取错误信息
            List<FieldError> errorList = errors.getFieldErrors();
            for (FieldError error : errorList) {

                // 打印字段错误信息
                stringBuilder.append("fied :" + error.getField() + "\t" + "msg:" + error.getDefaultMessage());
                //  System.err.println("fied :" + error.getField() + "\t" + "msg:" + error.getDefaultMessage());
            }
            resultCode.setCode(-1);
            resultCode.setMsg(stringBuilder.toString());
            return resultCode;
        }
      /*  AssertInfol assertInfol = new AssertInfol();
        assertInfol.setAssert_num(assert_num);
        assertInfol.setCard_num(card_num);
        assertInfol.setCommunity_name(community_name);
        assertInfol.setBuilding_num(building_num);
        assertInfol.setRoom_number(room_number);
        assertInfol.setFloorage(Double.parseDouble(floorage));
        assertInfol.setFloor_state(floor_state);
        assertInfol.setWatermeter_num(watermeter_num);
        assertInfol.setElectricmeter_num(electricmeter_num);*/
        try {
            int rows = assertInfolService.createAssertInfol(assertInfol);
            if (rows > 0) {
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
            resultCode.setMsg("资产信息创建失败");
            return resultCode;
        }


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
                assertInfol.setRemark(content[10]);
                assertInfolService.createAssertInfol(assertInfol);
            }
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
                                    @RequestParam String assert_num, @RequestParam String floor_state,@RequestParam  String community_name, @RequestParam  String property_leasing_num) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Page<AssertInfol> assertInfolList = assertInfolService.findAssertInfolList(page, rows, assert_num, floor_state, community_name,property_leasing_num);
        //定义csv文件名称
        String csvFileName = "资产信息表";
        //定义csv表头
        String colNames = "序号,资产编号,产证编号,小区名称,栋号,房号/店面号,建筑面积,房屋状态,水表号,电表号,备注";
        //定义表头对应字段的key
        String mapKey = "id,assert_num,card_num,community_name,building_num,room_number,floorage,floor_state,watermeter_num,electricmeter_num,remark";
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
        String colNames = "序号,资产编号,产证编号,小区名称,栋号,房号/店面号,建筑面积,房屋状态,水表号,电表号,备注";
        //定义表头对应字段的key
        String mapKey = "id,assert_num,card_num,community_name,building_num,room_number,floorage,floor_state,watermeter_num,electricmeter_num,remark";
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
            map.put("remark", assertInfol.getRemark());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }
    @RequestMapping("/assertInfol/downloadCommunityAssertInfol")
    public void downloadCommunityAssertInfol(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows,
                                 @RequestParam  String community_name) {
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
    public void downloadCommunityAssertInfolAll(HttpServletResponse response, @RequestParam  String community_name) {

        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        List<AssertInfolTotal> assertInfolList = assertInfolService.findAssertInfolListByCommunity( community_name);
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
