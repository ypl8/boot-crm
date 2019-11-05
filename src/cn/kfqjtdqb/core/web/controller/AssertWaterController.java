package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.common.utils.StringUtils;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.*;
import cn.kfqjtdqb.core.utils.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class AssertWaterController {

    // 依赖注入
    @Autowired
    private AssertWaterService assertWaterService;

    @Autowired
    private AssertWaterRentService assertWaterRentService;
    @Autowired
    private PropertyLeasingService propertyLeasingService;


    @RequestMapping(value = "/assertWater")
    public String showAssertWater() {
        return "redirect:/assertWater/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertWater/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String assert_num, Model model) {

        Page<AssertWater> assertWaterPage = assertWaterService.selectAssertWaterList(page, rows, property_leasing_num, assert_num);
        model.addAttribute("page", assertWaterPage);
        //参数回显
        model.addAttribute("assert_num", assert_num);
        model.addAttribute("property_leasing_num", property_leasing_num);
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
        try {
            int rows = assertWaterService.createAssertWater(assertWater);
            if (rows > 0) {
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


    @RequestMapping("/assertWater/find")
    @ResponseBody
    public AssertWaterRent getAssertWaterByLeasingNum(String property_leasing_num, String assert_num) {
        List<AssertWater> assertWater = assertWaterService.selectAssertWaterListByAssertNum(property_leasing_num, assert_num);
        AssertWaterRent assertWaterRent = new AssertWaterRent();
        assertWaterRent.setProperty_leasing_num(property_leasing_num);
        assertWaterRent.setAssert_num(assert_num);
        BigDecimal water_rent_recivied = assertWaterRentService.getAssertWaterRentCountByLeasingNum(assertWaterRent);//表示的是实际已收的水费
        if (water_rent_recivied == null) {
            water_rent_recivied = BigDecimal.ZERO;
        }
        assertWaterRent.setWater_rent_recivied(water_rent_recivied);  //实际已支付
        if (assertWater == null || assertWater.size() == 0) {
            return null;
        } else {
            AssertLeasing assertLeasing = new AssertLeasing();
            assertLeasing.setProperty_leasing_num(property_leasing_num);
            assertLeasing.setAssert_num(assert_num);
            assertLeasing = propertyLeasingService.selectAssertLeasingByAssertNum(assertLeasing);
            BigDecimal initWaterNum = assertLeasing.getWater_num();
            BigDecimal water_num = assertWater.get(0).getWater_num();  // 表示的是当前的水表度数
            PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingByNum(property_leasing_num);
            BigDecimal water_rate = propertyLeasing.getWater_rate();   //一顿水多少钱。
            assertWaterRent.setWater_rent((water_num.subtract(initWaterNum)).multiply( water_rate));  //表示的是应该收的水费
            assertWaterRent.setWatermeter_num(assertWater.get(0).getWatermeter_num());  //表示的是水表号
            assertWaterRent.setWater_num(assertWater.get(0).getWater_num());   //表示的是当前的水号
            assertWaterRent.setDeadline(assertWater.get(0).getDeadline());  //截止时间
        }

        return assertWaterRent;
    }


    boolean isAssertWater(String property_leasing_num, String assert_num) {
        List<AssertWater> assertWater = assertWaterService.selectAssertWaterListByAssertNum(property_leasing_num, assert_num);
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
}
