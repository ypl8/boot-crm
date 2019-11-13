package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.AssertLeasingService;
import cn.kfqjtdqb.core.service.PropertyLeasingService;
import cn.kfqjtdqb.core.utils.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    private PropertyLeasingService propertyLeasingService;


    @RequestMapping(value = "/assertLeasing")
    public String showAssertLeasing() {
        return "redirect:/assertLeasing/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertLeasing/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String assert_num, Model model) {

        Page<AssertLeasing> assertLeasingPage = assertLeasingService.selectAssertLeasingList(page, rows, property_leasing_num, assert_num);
        model.addAttribute("page", assertLeasingPage);
        //参数回显
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
            int rows = assertLeasingService.createAssertLeasing(assertLeasing);
            if (rows > 0) {
                resultCode.setCode(0);
                resultCode.setMsg("资产合同信息信息创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("资产合同信息信息创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
    }


}
