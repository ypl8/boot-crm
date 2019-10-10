package cn.kfqjtdqb.core.web.controller;


import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.common.utils.StringUtils;
import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.bean.PropertyLeasing;
import cn.kfqjtdqb.core.service.AssertInfolService;
import cn.kfqjtdqb.core.service.PropertyLeasingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class PropertyLeasingController {

    // 依赖注入
    @Autowired
    private PropertyLeasingService propertyLeasingService;

    @RequestMapping(value = "/propertyLeasing")
    public String showPropertyLeasing() {
        return "redirect:/propertyLeasing/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/propertyLeasing/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, Model model) {

        Page<PropertyLeasing> assertInfolList = propertyLeasingService.findPropertyLeasingList(page, rows, property_leasing_num);
        model.addAttribute("page", assertInfolList);
        //参数回显
        model.addAttribute("property_leasing_num", property_leasing_num);
        return "propertyLeasing";
    }

    @RequestMapping("/propertyLeasing/edit")
    @ResponseBody
    public PropertyLeasing getPropertyLeasingById(Long id) {
        PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(id);

        return propertyLeasing;
    }

    @RequestMapping("/propertyLeasing/update")
    @ResponseBody
    public String updatePropertyLeasing(String  id,String property_leasing_num, String tenant, String rental_area, String sign_in_time,
                                        String rent_charge_standard, String monthly_rental,
                                        String rent_free_period, String rent_period, String collect_rent_way,String  collect_rent_time,
                                        String estate_charge_standard, String estate_charge_month, String deposit,
                                        String deposit_time, String water_rate, String power_rate,
                                        String collect_rate_way, String rent_start_time, String rent_end_time,
                                        String property_leasing_state, String remark) {
        PropertyLeasing propertyLeasing = new PropertyLeasing();
        propertyLeasing.setId(Long.parseLong(id));
        propertyLeasing.setProperty_leasing_num(property_leasing_num);
        propertyLeasing.setTenant(tenant);
        propertyLeasing.setRental_area(Double.parseDouble(rental_area));
        propertyLeasing.setSign_in_time(StringUtils.convert(sign_in_time));
        propertyLeasing.setRent_charge_standard(Double.parseDouble(rent_charge_standard));
        propertyLeasing.setMonthly_rental(Double.parseDouble(monthly_rental));
        propertyLeasing.setRent_free_period(Double.parseDouble(rent_free_period));
        propertyLeasing.setRent_period(Double.parseDouble(rent_period));
        propertyLeasing.setCollect_rent_way(collect_rent_way.charAt(0));
        propertyLeasing.setCollect_rent_time(collect_rent_time);
        propertyLeasing.setEstate_charge_standard(Double.parseDouble(estate_charge_standard));
        propertyLeasing.setEstate_charge_month(Double.parseDouble(estate_charge_month));
        propertyLeasing.setDeposit(Double.parseDouble(deposit));
        propertyLeasing.setDeposit_time(StringUtils.convert(deposit_time));
        propertyLeasing.setWater_rate(Double.parseDouble(water_rate));
        propertyLeasing.setPower_rate(Double.parseDouble(power_rate));
        propertyLeasing.setCollect_rate_way(collect_rate_way.charAt(0));
        propertyLeasing.setRent_start_time(StringUtils.convert(rent_start_time));
        propertyLeasing.setRent_end_time(StringUtils.convert(rent_end_time));
        propertyLeasing.setProperty_leasing_state(property_leasing_state.charAt(0));
        propertyLeasing.setRemark(remark);

        propertyLeasingService.updatePropertyLeasing(propertyLeasing);
        return "OK";
    }


    @RequestMapping("/propertyLeasing/delete")
    @ResponseBody
    public String deletePropertyLeasing(Long id) {
        propertyLeasingService.deletePropertyLeasing(id);
        return "OK";
    }



    /**
     * 创建客户
     */
    @RequestMapping("/propertyLeasing/create")
    @ResponseBody
    public String propertyLeasingCreate(String property_leasing_num, String tenant, String rental_area, String sign_in_time,
                                        String rent_charge_standard, String monthly_rental,
                                        String rent_free_period, String rent_period, String collect_rent_way,String  collect_rent_time,
                                        String estate_charge_standard, String estate_charge_month, String deposit,
                                        String deposit_time, String water_rate, String power_rate,
                                        String collect_rate_way, String rent_start_time, String rent_end_time,
                                        String property_leasing_state, String remark) {

        PropertyLeasing propertyLeasing = new PropertyLeasing();
        propertyLeasing.setProperty_leasing_num(property_leasing_num);
        propertyLeasing.setTenant(tenant);
        propertyLeasing.setRental_area(Double.parseDouble(rental_area));
        propertyLeasing.setSign_in_time(StringUtils.convert(sign_in_time));
        propertyLeasing.setRent_charge_standard(Double.parseDouble(rent_charge_standard));
        propertyLeasing.setMonthly_rental(Double.parseDouble(monthly_rental));
        propertyLeasing.setRent_free_period(Double.parseDouble(rent_free_period));
        propertyLeasing.setRent_period(Double.parseDouble(rent_period));
        propertyLeasing.setCollect_rent_way(collect_rent_way.charAt(0));
        propertyLeasing.setCollect_rent_time(collect_rent_time);
        propertyLeasing.setEstate_charge_standard(Double.parseDouble(estate_charge_standard));
        propertyLeasing.setEstate_charge_month(Double.parseDouble(estate_charge_month));
        propertyLeasing.setDeposit(Double.parseDouble(deposit));
        propertyLeasing.setDeposit_time(StringUtils.convert(deposit_time));
        propertyLeasing.setWater_rate(Double.parseDouble(water_rate));
        propertyLeasing.setPower_rate(Double.parseDouble(power_rate));
        propertyLeasing.setCollect_rate_way(collect_rate_way.charAt(0));
        propertyLeasing.setRent_start_time(StringUtils.convert(rent_start_time));
        propertyLeasing.setRent_end_time(StringUtils.convert(rent_end_time));
        propertyLeasing.setProperty_leasing_state(property_leasing_state.charAt(0));
        propertyLeasing.setRemark(remark);
        int rows = propertyLeasingService.addPropertyLeasing(propertyLeasing);
        if (rows > 0) {
            return "OK";
        } else {
            return "FALSE";
        }
    }


}
