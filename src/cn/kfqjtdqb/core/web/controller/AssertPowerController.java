package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.common.utils.StringUtils;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.AssertPowerRentService;
import cn.kfqjtdqb.core.service.AssertPowerService;
import cn.kfqjtdqb.core.service.PropertyLeasingService;
import cn.kfqjtdqb.core.utils.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class AssertPowerController {

    // 依赖注入
    @Autowired
    private AssertPowerService assertPowerService;
    @Autowired
    private AssertPowerRentService assertPowerRentService;

    @Autowired
    private PropertyLeasingService propertyLeasingService;

    @RequestMapping(value = "/assertPower")
    public String showAssertPower() {
        return "redirect:/assertPower/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertPower/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String assert_num, Model model) {

        Page<AssertPower> assertPowerPage = assertPowerService.selectAssertPowerList(page, rows, property_leasing_num, assert_num);
        model.addAttribute("page", assertPowerPage);
        //参数回显
        model.addAttribute("assert_num", assert_num);
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


    /*@RequestMapping("/assertDeposit/find")
    @ResponseBody
    public AssertDeposit getAssertDepositByLeasingNum(String property_leasing_num) {
        AssertDeposit assertDeposit = assertDepositService.getAssertDepositByLeasingNum(property_leasing_num);
        return assertDeposit;
    }*/

    @RequestMapping("/assertPower/find")
    @ResponseBody
    public AssertPowerRent getAssertWaterByLeasingNum(String property_leasing_num, String assert_num) {
        List<AssertPower> assertPowers = assertPowerService.selectAssertPowerListByAssertNum(property_leasing_num, assert_num);
        AssertPowerRent assertPowerRent = new AssertPowerRent();
        assertPowerRent.setProperty_leasing_num(property_leasing_num);
        assertPowerRent.setAssert_num(assert_num);
        BigDecimal power_rent_recivied = assertPowerRentService.getAssertPowerRentCountByLeasingNum(assertPowerRent);//表示的是实际已收的水费
        if (power_rent_recivied == null) {
            power_rent_recivied = BigDecimal.ZERO;
        }
        assertPowerRent.setPower_rent_recivied(power_rent_recivied);  //实际已支付
        if (assertPowers == null || assertPowers.size() == 0) {
            return null;
        } else {
            AssertLeasing assertLeasing = new AssertLeasing();
            assertLeasing.setProperty_leasing_num(property_leasing_num);
            assertLeasing.setAssert_num(assert_num);
            assertLeasing = propertyLeasingService.selectAssertLeasingByAssertNum(assertLeasing);
            BigDecimal initPowerNum = assertLeasing.getElectric_num();
            BigDecimal power_num = assertPowers.get(0).getPower_num();  // 表示的是当前的电表度数
            PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingByNum(property_leasing_num);
            BigDecimal power_rate = propertyLeasing.getPower_rate();   //一顿水多少钱。
            assertPowerRent.setPower_rent((power_num.subtract(initPowerNum)).multiply(power_rate));  //表示的是应该收的水费
            assertPowerRent.setPowermeter_num(assertPowers.get(0).getPowermeter_num());  //表示的是水表号
            assertPowerRent.setPower_num(assertPowers.get(0).getPower_num());   //表示的是当前的水号
            assertPowerRent.setDeadline(assertPowers.get(0).getDeadline());  //截止时间
        }
        return assertPowerRent;
    }


    boolean isAssertPower(String property_leasing_num, String assert_num) {
        List<AssertPower> assertPowers = assertPowerService.selectAssertPowerListByAssertNum(property_leasing_num, assert_num);
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

}
