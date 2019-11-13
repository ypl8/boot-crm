package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.AssertWaterRentService;
import cn.kfqjtdqb.core.service.AssertWaterService;
import cn.kfqjtdqb.core.service.PropertyLeasingService;
import cn.kfqjtdqb.core.service.TotalRentalService;
import cn.kfqjtdqb.core.utils.CSVUtils;
import cn.kfqjtdqb.core.utils.ErrorUtils;
import cn.kfqjtdqb.core.utils.RentUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TotalRentalController {

    public final Log logger = LogFactory.getLog(TotalRentalController.class);
    @Autowired
    private TotalRentalService totalRentalService;

    @RequestMapping(value = "/totalRental")
    public String showTotalRental() {
        return "redirect:/toalRental/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/totalRental/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String community_name, String year_months, Model model) {
    /*    if (community_name != null) {
            try {
                community_name = new String(community_name.getBytes("ISO8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }*/
        Page<TotalRental> totalRentalPage = totalRentalService.selectTotalRentalList(page, rows, property_leasing_num, year_months, community_name);
        model.addAttribute("page", totalRentalPage);
        //参数回显
        model.addAttribute("community_name", community_name);
        model.addAttribute("year_months", year_months);
        model.addAttribute("property_leasing_num", property_leasing_num);
        return "totalRental";
    }


    // 客户列表
    @RequestMapping(value = "/totalRental/listCommunity")
    public String listCommunity(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                                String community_name, String year_months, String years, Model model) {
       /* if(community_name!=null){
            try {
                community_name = new String(community_name.getBytes("ISO8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }*/
        Page<TotalRental> totalRentalPage = null;
        if (!StringUtils.isNotBlank(year_months) && StringUtils.isNotBlank(years)) {
            totalRentalPage = totalRentalService.selectTotalRentalCommunityByYearList(page, rows, years, community_name);
        } else {
            totalRentalPage = totalRentalService.selectTotalRentalCommunityList(page, rows, year_months, community_name);
        }

        model.addAttribute("page", totalRentalPage);
        //参数回显
        model.addAttribute("community_name", community_name);
        model.addAttribute("year_months", year_months);
        model.addAttribute("years", years);
        return "totalCommunityRental";
    }

    @RequestMapping("/totalRental/edit")
    @ResponseBody
    public TotalRental getTotalRentalById(Long id) {
        TotalRental totalRental = totalRentalService.getTotalRentalById(id);
        return totalRental;
    }


    @RequestMapping("/totalRental/update")
    @ResponseBody
    public ResultCode updateAssertWater(@Valid TotalRental totalRental, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            totalRentalService.updateTotalRental(totalRental);
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("每个月租金信息更新失败");
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("每个月租金信息更新成功");
        return resultCode;
    }

    @RequestMapping("/totalRental/delete")
    @ResponseBody
    public String deleteToalRental(Long id) {
        totalRentalService.deleteTotalRental(id);
        return "OK";
    }


    /**
     * 创建水信息
     */
    @RequestMapping("/totalRental/create")
    @ResponseBody

    public ResultCode createDeposit(@Valid TotalRental totalRental, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }

        try {
            int rows = totalRentalService.createTotalRental(totalRental);
            if (rows > 0) {
                resultCode.setCode(0);
                resultCode.setMsg("每个月租金信息创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("每个月租金信息创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("每个月租金信息创建失败");
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


    @RequestMapping("/totalRental/downloadTotalRental")
    public void downloadTotalRental(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows,
                                    @RequestParam String property_leasing_num, @RequestParam String community_name, @RequestParam String year_months) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Page<TotalRental> totalRentalPage = totalRentalService.selectTotalRentalList(page, rows, property_leasing_num, year_months, community_name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv文件名称
        String csvFileName = "租金收入明细表";
        //定义csv表头
        String colNames = "序号,合同编号,小区名称,栋号,承租人,承租位置,应收租金,实收租金,对应月份,到帐时间";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,community_name,building_num,tenant,rentalLocation,rental,reality_rental,year_months,deadline";
        //遍历保存查询数据集到dataList中

        for (TotalRental totalRental : totalRentalPage.getRows()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", totalRental.getId());
            map.put("property_leasing_num", totalRental.getProperty_leasing_num());
            map.put("community_name", totalRental.getCommunity_name());
            map.put("building_num", totalRental.getBuilding_num());
            map.put("tenant", totalRental.getTenant());
            map.put("rentalLocation", totalRental.getRentalLocation());
            map.put("rental", totalRental.getRental());
            map.put("reality_rental", totalRental.getReality_rental());
            map.put("year_months", totalRental.getYear_months());
            if (totalRental.getDeadline() != null) {
                map.put("deadline", sdf.format(totalRental.getDeadline()));
            } else {
                map.put("deadline", null);
            }
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/totalRental/downloadTotalRentalAll")
    public void downloadTotalRentalAll(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows,
                                       @RequestParam String property_leasing_num, @RequestParam String community_name, @RequestParam String year_months) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        List<TotalRental> totalRentals = totalRentalService.selectTotalRentalList(property_leasing_num, community_name, year_months);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义csv文件名称
        String csvFileName = "租金收入明细表";
        //定义csv表头
        String colNames = "序号,合同编号,小区名称,栋号,承租人,承租位置,应收租金,实收租金,对应月份,到帐时间";
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,community_name,building_num,tenant,rentalLocation,rental,reality_rental,year_months,deadline";
        //遍历保存查询数据集到dataList中

        for (TotalRental totalRental : totalRentals) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", totalRental.getId());
            map.put("property_leasing_num", totalRental.getProperty_leasing_num());
            map.put("community_name", totalRental.getCommunity_name());
            map.put("building_num", totalRental.getBuilding_num());
            map.put("tenant", totalRental.getTenant());
            map.put("rentalLocation", totalRental.getRentalLocation());
            map.put("rental", totalRental.getRental());
            map.put("reality_rental", totalRental.getReality_rental());
            map.put("year_months", totalRental.getYear_months());
            if (totalRental.getDeadline() != null) {
                map.put("deadline", sdf.format(totalRental.getDeadline()));
            } else {
                map.put("deadline", null);
            }
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/totalRental/downloadCommunityRental")
    public void downloadCommunityRental(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows,
                                        @RequestParam String community_name, @RequestParam String year_months, @RequestParam String years) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        //遍历保存查询数据集到dataList中
        Page<TotalRental> totalRentalPage = null;
        if (!StringUtils.isNotBlank(year_months) && StringUtils.isNotBlank(years)) {
            totalRentalPage = totalRentalService.selectTotalRentalCommunityByYearList(page, rows, years, community_name);
        } else {
            totalRentalPage = totalRentalService.selectTotalRentalCommunityList(page, rows, year_months, community_name);
        }
        //定义csv文件名称
        String csvFileName = "租金统计";
        //定义csv表头
        String colNames = "小区编号,应收租金费,实收租金费,欠缴金额,收取率,对应月份";

        //定义表头对应字段的key
        String mapKey = "community_name,rental,reality_rental,difference,collectionRate,year_months";

        for (TotalRental totalRental : totalRentalPage.getRows()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("community_name", totalRental.getCommunity_name());
            map.put("rental", totalRental.getRental());
            map.put("reality_rental", totalRental.getReality_rental());
            map.put("difference", totalRental.getDifference());
            map.put("collectionRate", totalRental.getCollectionRate());
            map.put("year_months", totalRental.getYear_months());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


    @RequestMapping("/totalRental/downloadCommunityRentalAll")
    public void downloadCommunityRentalAll(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer
            page, @RequestParam(defaultValue = "10") Integer rows,
                                           @RequestParam String community_name, @RequestParam String year_months, @RequestParam String years) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

        List<TotalRental>  totalRentals=null;
        if (!StringUtils.isNotBlank(year_months) && StringUtils.isNotBlank(years)) {
            totalRentals = totalRentalService.selectTotalRentalCommunityByYearList(years, community_name);
        } else {
            totalRentals = totalRentalService.selectTotalRentalCommunityList(year_months, community_name);
        }

        //定义csv文件名称
        String csvFileName = "租金统计";
        //定义csv表头
        String colNames = "小区编号,应收租金费,实收租金费,欠缴金额,收取率,对应月份";

        //定义表头对应字段的key
        String mapKey = "community_name,rental,reality_rental,difference,collectionRate,year_months";

        for (TotalRental totalRental : totalRentals) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("community_name", totalRental.getCommunity_name());
            map.put("rental", totalRental.getRental());
            map.put("reality_rental", totalRental.getReality_rental());
            map.put("difference", totalRental.getDifference());
            map.put("collectionRate", totalRental.getCollectionRate());
            map.put("year_months", totalRental.getYear_months());
            dataList.add(map);
        }
        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


}
