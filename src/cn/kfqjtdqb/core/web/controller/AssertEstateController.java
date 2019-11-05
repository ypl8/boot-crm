package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.common.utils.StringUtils;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.*;
import cn.kfqjtdqb.core.utils.DateUtils;
import cn.kfqjtdqb.core.utils.ErrorUtils;
import cn.kfqjtdqb.core.utils.RentUtils;
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
import java.util.Date;
import java.util.List;

@Controller
public class AssertEstateController {

    // 依赖注入
    @Autowired
    private AssertEstateService assertEstateService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private PropertyLeasingService propertyLeasingService;
    @Autowired
    private TotalEstateService totalEstateService;
    @Value("${estate.state.type}")
    private String STATE_TYPE;


    @RequestMapping(value = "/assertEstate")
    public String showAssertEstate() {
        return "redirect:/assertEstate/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertEstate/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String state, Model model) {

        Page<AssertEstate> AssertEstateList = assertEstateService.selectAssertEstateList(page, rows, property_leasing_num, state);
        model.addAttribute("page", AssertEstateList);
        List<BaseDict> stateType = systemService.findBaseDictListByType(STATE_TYPE);
        model.addAttribute("stateType", stateType);
        model.addAttribute("state", state);

        //参数回显
        model.addAttribute("property_leasing_num", property_leasing_num);
        return "assertEstate";
    }

    @RequestMapping("/assertEstate/edit")
    @ResponseBody
    public AssertEstate getAssertEstateById(Long id) {
        AssertEstate AssertEstate = assertEstateService.getAssertEstateById(id);
        return AssertEstate;
    }

    /*    String id, String property_leasing_num, String state, String estate, String reality_estate, String rent_start_time,
        String rent_end_time, String estate_received*/
    @RequestMapping("/assertEstate/update")
    @ResponseBody
    public ResultCode updateAssertEstate(@Valid AssertEstate assertEstate, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        if (assertEstate.getEstate_recivied() == null) {
            assertEstate.setEstate_recivied(new BigDecimal("0"));
        }

        try {
            String property_leasing_num = assertEstate.getProperty_leasing_num();
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(property_leasing_num);

            if (assertEstate.getEstate_recivied().compareTo(assertEstate.getEstate_recivied().add(assertEstate.getReality_estate())) <= 0) {  //表示的已经缴清
                assertEstate.setState(ConstUtils.ESTATESTATESUCCESS);
                propertyLeasing.setEstateState(ConstUtils.ESTATESTATESUCCESS);
            } else {
                assertEstate.setState(ConstUtils.ESTATESTATEFAIL);
                propertyLeasing.setEstateState(ConstUtils.ESTATESTATEFAIL);
            }
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
            assertEstateService.updateAssertEstate(assertEstate);
            resultCode.setMsg("物业费修改成功");
            resultCode.setCode(0);
            return resultCode;
        } catch (DataAccessException e) {
            resultCode.setMsg(e.getMessage());
            resultCode.setCode(-1);
            return resultCode;
        }


    }

    @RequestMapping("/assertEstate/delete")
    @ResponseBody
    public String deleteAssertEstate(Long id) {
        assertEstateService.deleteAssertEstate(id);
        return "OK";
    }


    /**
     * 创建物流
     */
    @RequestMapping("/assertEstate/create")
    @ResponseBody
    public ResultCode createEstate(@Valid AssertEstate assertEstate, Errors errors) {

        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            if (assertEstate.getEstate_recivied() == null) {
                assertEstate.setEstate_recivied(BigDecimal.ZERO);
            }
            String property_leasing_num = assertEstate.getProperty_leasing_num();
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(property_leasing_num);

            if (assertEstate.getEstate().compareTo(assertEstate.getEstate_recivied().add(assertEstate.getReality_estate())) <= 0) {  //表示的已经缴清
                assertEstate.setState(ConstUtils.ESTATESTATESUCCESS);
                propertyLeasing.setEstateState(ConstUtils.ESTATESTATESUCCESS);
            } else {
                assertEstate.setState(ConstUtils.ESTATESTATEFAIL);
                propertyLeasing.setEstateState(ConstUtils.ESTATESTATEFAIL);
            }
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
            int rows = assertEstateService.createAssertEstate(assertEstate);
            if (rows > 0) {
                TotalEstate totalEstate = new TotalEstate();
                totalEstate.setProperty_leasing_num(assertEstate.getProperty_leasing_num());
                totalEstate.setId(Long.parseLong(assertEstate.getYear_months()));
                totalEstate.setReality_estate(assertEstate.getReality_estate());
                totalEstate.setDeadline(new Date());
                totalEstateService.updateTotalEstate(totalEstate);
                resultCode.setCode(0);
                resultCode.setMsg("物业费创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("物业费创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("物业费创建失败");
            return resultCode;
        }

    }


    @RequestMapping("/assertEstate/find")
    @ResponseBody
    public AssertEstate getAssertEstateByLeasingNum(String id) {
        PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(Long.parseLong(id));
        //实际已经交的物业费
        BigDecimal rent_recivied = assertEstateService.getAssertEstateCountByLeasingNum(propertyLeasing.getProperty_leasing_num());
        if (rent_recivied == null) rent_recivied = BigDecimal.ZERO;
        //截止本月应收物业费
        BigDecimal rent_should = BigDecimal.ZERO;
        Date startRentDate = propertyLeasing.getRent_start_time();  //租金开始时间
        Date currentDate = new Date();  //表示的是当前时间
        int month = DateUtils.getDifMonth(startRentDate, currentDate) + 1;   //需要交物业费的月份
        rent_should = new BigDecimal(month).multiply(propertyLeasing.getEstate_charge_month());
        AssertEstate AssertEstate = new AssertEstate();
        AssertEstate.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
        AssertEstate.setRent_start_time(propertyLeasing.getRent_start_time());
        AssertEstate.setRent_end_time(propertyLeasing.getRent_end_time());
        AssertEstate.setEstate(rent_should);  //截止本月本次应该收多少钱
        AssertEstate.setEstate_recivied(rent_recivied);  //实际已经收了多少钱
        AssertEstate.setReality_estate(rent_should .subtract( rent_recivied));
        if (rent_should .compareTo( rent_recivied)<=0) {
            AssertEstate.setState("9");  //表示的已经缴清
        } else {
            AssertEstate.setState("8");
        }
        return AssertEstate;
    }


}
