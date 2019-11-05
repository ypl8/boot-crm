package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.common.utils.StringUtils;
import cn.kfqjtdqb.core.bean.AssertDeposit;
import cn.kfqjtdqb.core.bean.BaseDict;
import cn.kfqjtdqb.core.bean.PropertyLeasing;
import cn.kfqjtdqb.core.bean.ResultCode;
import cn.kfqjtdqb.core.service.AssertDepositService;
import cn.kfqjtdqb.core.service.PropertyLeasingService;
import cn.kfqjtdqb.core.service.SystemService;
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
import java.util.List;

@Controller
public class AssertDepositController {
    // 依赖注入
    @Autowired
    private AssertDepositService assertDepositService;

    @Autowired
    private SystemService systemService;

    @Value("${deposit.state.type}")
    private String STATE_TYPE;


    @Autowired
    private PropertyLeasingService propertyLeasingService;


    @RequestMapping(value = "/assertDeposit")
    public String showAssertDeposit() {
        return "redirect:/assertDeposit/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertDeposit/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String state, Model model) {

        Page<AssertDeposit> assertInfolList = assertDepositService.findAssertDepositList(page, rows, property_leasing_num, state);
        model.addAttribute("page", assertInfolList);
        List<BaseDict> stateType = systemService.findBaseDictListByType(STATE_TYPE);

        model.addAttribute("stateType", stateType);
        model.addAttribute("state", state);

        //参数回显
        model.addAttribute("property_leasing_num", property_leasing_num);
        return "assertDeposit";
    }

    @RequestMapping("/assertDeposit/edit")
    @ResponseBody
    public AssertDeposit getAssertDepositById(Long id) {
        AssertDeposit assertDeposit = assertDepositService.getAssertDepositById(id);
        return assertDeposit;
    }


    @RequestMapping("/assertDeposit/update")
    @ResponseBody
    public ResultCode updateAssertDeposit(@Valid AssertDeposit assertDeposit, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return  ErrorUtils.getRsult(errors);
        }
        PropertyLeasing propertyLeasing = new PropertyLeasing();
        propertyLeasing.setProperty_leasing_num(assertDeposit.getProperty_leasing_num());
        if (assertDeposit.getDeposit() .compareTo(assertDeposit.getReality_deposit())<=0) {  //表示的是缴清
            assertDeposit.setState(ConstUtils.DEPOSITSTATESUCCESS);
            propertyLeasing.setDepositState(ConstUtils.DEPOSITSTATESUCCESS);
        } else {  //表示的是未缴清
            assertDeposit.setState(ConstUtils.DEPOSITSTATEFAIL);
            propertyLeasing.setDepositState(ConstUtils.DEPOSITSTATEFAIL);
        }
        try {
            assertDepositService.updateAssertDeposit(assertDeposit);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setMsg("押金修改成功");
        resultCode.setCode(0);
        return resultCode;

    }

    @RequestMapping("/assertDeposit/delete")
    @ResponseBody
    public String deleteAssertDeposit(Long id) {
        assertDepositService.deleteAssertDeposit(id);
        return "OK";
    }


    /**
     * 创建资产
     */
    @RequestMapping("/assertDeposit/create")
    @ResponseBody
    public ResultCode createDeposit(@Valid AssertDeposit assertDeposit, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return  ErrorUtils.getRsult(errors);
        }
        try {
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(assertDeposit.getProperty_leasing_num());
            if (assertDeposit.getDeposit() .compareTo( assertDeposit.getReality_deposit())<=0) {  //表示的是缴清
                assertDeposit.setState(ConstUtils.DEPOSITSTATESUCCESS);
                propertyLeasing.setDepositState(ConstUtils.DEPOSITSTATESUCCESS);
            } else {  //表示的是未缴清
                assertDeposit.setState(ConstUtils.DEPOSITSTATEFAIL);
                propertyLeasing.setDepositState(ConstUtils.DEPOSITSTATEFAIL);
            }
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
            int rows = assertDepositService.createAssertDeposit(assertDeposit);
            if (rows > 0) {
                resultCode.setCode(0);
                resultCode.setMsg("押金创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("押金创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("押金创建失败");
            return resultCode;
        }
    }


    @RequestMapping("/assertDeposit/find")
    @ResponseBody
    public AssertDeposit getAssertDepositByLeasingNum(String property_leasing_num) {
        AssertDeposit assertDeposit = assertDepositService.getAssertDepositByLeasingNum(property_leasing_num);
        return assertDeposit;
    }


}
