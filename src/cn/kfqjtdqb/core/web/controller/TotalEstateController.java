package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.ResultCode;
import cn.kfqjtdqb.core.bean.TotalEstate;
import cn.kfqjtdqb.core.service.PropertyLeasingService;
import cn.kfqjtdqb.core.service.TotalEstateService;
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

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

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
            if (rows > 0) {
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

}
