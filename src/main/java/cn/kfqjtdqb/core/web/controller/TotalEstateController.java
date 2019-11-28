package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.ResultCode;
import cn.kfqjtdqb.core.bean.TotalEstate;
import cn.kfqjtdqb.core.bean.TotalEstate;
import cn.kfqjtdqb.core.bean.TotalEstate;
import cn.kfqjtdqb.core.service.PropertyLeasingService;
import cn.kfqjtdqb.core.service.TotalEstateService;
import cn.kfqjtdqb.core.utils.CSVUtils;
import cn.kfqjtdqb.core.utils.ErrorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TotalEstateController {


    @Autowired
    private TotalEstateService totalEstateService;

    @RequestMapping(value = "/totalEstate")
    public String showTotalEstate() {
        return "redirect:/toalEstate/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/totalEstate/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String community_name, String year_months, Model model) {
       /* if (community_name != null) {
            try {
                community_name = new String(community_name.getBytes("ISO8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }*/
        Page<TotalEstate> totalEstatePage = totalEstateService.selectTotalEstateList(page, rows, property_leasing_num, year_months, community_name);

        model.addAttribute("page", totalEstatePage);
        //参数回显
        model.addAttribute("community_name", community_name);
        model.addAttribute("year_months", year_months);
        model.addAttribute("property_leasing_num", property_leasing_num);
        return "totalEstate";
    }


    // 客户列表
    @RequestMapping(value = "/totalEstate/listCommunity")
    public String listCommunity(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                                String community_name, String year_months, String years, Model model) {
       /* if (community_name != null) {
            try {
                community_name = new String(community_name.getBytes("ISO8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }*/
        Page<TotalEstate> totalEstatePage = null;
        if (!StringUtils.isNotBlank(year_months) && StringUtils.isNotBlank(years)) {
            totalEstatePage=totalEstateService.selectTotalEstateCommunityByYearList(page, rows, years, community_name);
        } else {
            totalEstatePage = totalEstateService.selectTotalEstateCommunityList(page, rows, year_months, community_name);
        }
        model.addAttribute("page", totalEstatePage);
        //参数回显
        model.addAttribute("years", years);
        model.addAttribute("community_name", community_name);
        model.addAttribute("year_months", year_months);

        return "totalCommunityEstate";
    }

    @RequestMapping("/totalEstate/edit")
    @ResponseBody
    public TotalEstate getTotalEstateById(Long id) {
        TotalEstate totalEstate = totalEstateService.getTotalEstateById(id);
        return totalEstate;
    }


    @RequestMapping("/totalEstate/update")
    @ResponseBody
    public ResultCode updateAssertWater(@Valid TotalEstate totalEstate, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            totalEstateService.updateTotalEstate(totalEstate);
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("每个月物业费信息更新失败");
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("每个月物业费信息更新成功");
        return resultCode;
    }

    @RequestMapping("/totalEstate/delete")
    @ResponseBody
    public String deleteToalEstate(Long id) {
        totalEstateService.deleteTotalEstate(id);
        return "OK";
    }


    /**
     * 创建水信息
     */
    @RequestMapping("/totalEstate/create")
    @ResponseBody

    public ResultCode createDeposit(@Valid TotalEstate totalEstate, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }

        try {
            int rows = totalEstateService.createTotalEstate(totalEstate);
            if (rows==-2147482646||rows > 0) {
                resultCode.setCode(0);
                resultCode.setMsg("每个月物业费信息创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("每个月物业费信息创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("每个月物业费信息创建失败");
            return resultCode;
        }
    }

/*
    @RequestMapping("/assertWater/find")
    @ResponseBody
    public AssertWaterRent getAssertWaterByLeasingNum(String property_leasing_num, String assert_num) {
        List<AssertWater> assertWater = assertWaterService.selectAssertWaterListByAssertNum(property_leasing_num, assert_num);
        AssertWaterRent assertWaterRent = new AssertWaterRent();
        assertWaterRent.setProperty_leasing_num(property_leasing_num);
        assertWaterRent.setAssert_num(assert_num);
        Double water_rent_recivied = assertWaterRentService.getAssertWaterRentCountByLeasingNum(assertWaterRent);//表示的是实际已收的水费
        if (water_rent_recivied == null) {
            water_rent_recivied = 0d;
        }
        assertWaterRent.setWater_rent_recivied(water_rent_recivied);  //实际已支付
        if (assertWater == null || assertWater.size() == 0) {
            return null;
        } else {
            AssertLeasing assertLeasing = new AssertLeasing();
            assertLeasing.setProperty_leasing_num(property_leasing_num);
            assertLeasing.setAssert_num(assert_num);
            assertLeasing = propertyLeasingService.selectAssertLeasingByAssertNum(assertLeasing);
            Double initWaterNum = assertLeasing.getWater_num();
            Double water_num = assertWater.get(0).getWater_num();  // 表示的是当前的水表度数
            PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingByNum(property_leasing_num);
            Double water_rate = propertyLeasing.getWater_rate();   //一顿水多少钱。
            assertWaterRent.setWater_rent((water_num-initWaterNum)*water_rate);  //表示的是应该收的水费
            assertWaterRent.setWatermeter_num(assertWater.get(0).getWatermeter_num());  //表示的是水表号
            assertWaterRent.setWater_num(assertWater.get(0).getWater_num());   //表示的是当前的水号
            assertWaterRent.setDeadline(assertWater.get(0).getDeadline());  //截止时间
        }

        return assertWaterRent;
    }
*/


    @RequestMapping("/totalEstate/downloadTotalEstate")
    public void downloadTotalEstate(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows,
                                    @RequestParam   String property_leasing_num, @RequestParam String community_name, @RequestParam String year_months) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Page<TotalEstate> totalEstatePage = totalEstateService.selectTotalEstateList(page, rows, property_leasing_num, year_months, community_name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv文件名称
        String csvFileName = "物业收入明细表";
        //定义csv表头
        String colNames = "序号,合同编号,小区名称,栋号,承租人,承租位置,应收物业,实收物业,对应月份,到帐时间";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,community_name,building_num,tenant,estateLocation,estate,reality_estate,year_months,deadline";
        //遍历保存查询数据集到dataList中

        for (TotalEstate totalEstate : totalEstatePage.getRows()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", totalEstate.getId());
            map.put("property_leasing_num", totalEstate.getProperty_leasing_num());
            map.put("community_name", totalEstate.getCommunity_name());
            map.put("building_num",totalEstate.getBuilding_num());
            map.put("tenant", totalEstate.getTenant());
            map.put("estateLocation", totalEstate.getRentalLocation());
            map.put("estate", totalEstate.getEstate());
            map.put("reality_estate", totalEstate.getReality_estate());
            map.put("year_months", totalEstate.getYear_months());
            if(totalEstate.getDeadline()!=null){
                map.put("deadline", sdf.format(totalEstate.getDeadline()));
            }else{
                map.put("deadline", null);
            }
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/totalEstate/downloadTotalEstateAll")
    public void downloadTotalEstateAll(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows,
                                       @RequestParam   String property_leasing_num, @RequestParam String community_name, @RequestParam String year_months) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        List<TotalEstate> totalEstates = totalEstateService.selectTotalEstateList(property_leasing_num, community_name,year_months);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv文件名称
        String csvFileName = "物业收入明细表";
        //定义csv表头
        String colNames = "序号,合同编号,小区名称,栋号,承租人,承租位置,应收物业,实收物业,对应月份,到帐时间";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,community_name,building_num,tenant,estateLocation,estate,reality_estate,year_months,deadline";
        //遍历保存查询数据集到dataList中

        for (TotalEstate totalEstate : totalEstates) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", totalEstate.getId());
            map.put("property_leasing_num", totalEstate.getProperty_leasing_num());
            map.put("community_name", totalEstate.getCommunity_name());
            map.put("building_num",totalEstate.getBuilding_num());
            map.put("tenant", totalEstate.getTenant());
            map.put("estateLocation", totalEstate.getRentalLocation());
            map.put("estate", totalEstate.getEstate());
            map.put("reality_estate", totalEstate.getReality_estate());
            map.put("year_months", totalEstate.getYear_months());
            if(totalEstate.getDeadline()!=null){
                map.put("deadline", sdf.format(totalEstate.getDeadline()));
            }else{
                map.put("deadline", null);
            }
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/totalEstate/downloadCommunityEstate")
    public void downloadCommunityEstate(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows,
                                        @RequestParam String community_name, @RequestParam String year_months, @RequestParam String years) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        //遍历保存查询数据集到dataList中
        Page<TotalEstate> totalEstatePage = null;
        if (!StringUtils.isNotBlank(year_months) && StringUtils.isNotBlank(years)) {
            totalEstatePage = totalEstateService.selectTotalEstateCommunityByYearList(page, rows, years, community_name);
        } else {
            totalEstatePage = totalEstateService.selectTotalEstateCommunityList(page, rows, year_months, community_name);
        }
        //定义csv文件名称
        String csvFileName = "物业统计";
        //定义csv表头
        String colNames = "小区编号,应收物业费,实收物业费,欠缴金额,收取率,对应月份";

        //定义表头对应字段的key
        String mapKey = "community_name,estate,reality_estate,difference,collectionRate,year_months";

        for (TotalEstate totalEstate : totalEstatePage.getRows()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("community_name", totalEstate.getCommunity_name());
            map.put("estate", totalEstate.getEstate());
            map.put("reality_estate", totalEstate.getReality_estate());
            map.put("difference", totalEstate.getDifference());
            map.put("collectionRate", totalEstate.getCollectionRate());
            map.put("year_months", totalEstate.getYear_months());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/totalEstate/downloadCommunityEstateAll")
    public void downloadCommunityEstateAll(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows,
                                           @RequestParam String community_name, @RequestParam String year_months, @RequestParam String years) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

        List<TotalEstate>  totalEstates=null;
        if (!StringUtils.isNotBlank(year_months) && StringUtils.isNotBlank(years)) {
            totalEstates = totalEstateService.selectTotalEstateCommunityByYearList(years, community_name);
        } else {
            totalEstates = totalEstateService.selectTotalEstateCommunityList(year_months, community_name);
        }

        //定义csv文件名称
        String csvFileName = "物业统计";
        //定义csv表头
        String colNames = "小区编号,应收物业费,实收物业费,欠缴金额,收取率,对应月份";

        //定义表头对应字段的key
        String mapKey = "community_name,estate,reality_estate,difference,collectionRate,year_months";

        for (TotalEstate totalEstate : totalEstates) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("community_name", totalEstate.getCommunity_name());
            map.put("estate", totalEstate.getEstate());
            map.put("reality_estate", totalEstate.getReality_estate());
            map.put("difference", totalEstate.getDifference());
            map.put("collectionRate", totalEstate.getCollectionRate());
            map.put("year_months", totalEstate.getYear_months());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }



}
