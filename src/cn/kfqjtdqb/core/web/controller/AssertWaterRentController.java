package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.common.utils.StringUtils;
import cn.kfqjtdqb.core.bean.AssertWaterRent;
import cn.kfqjtdqb.core.bean.BaseDict;
import cn.kfqjtdqb.core.bean.PropertyLeasing;
import cn.kfqjtdqb.core.bean.ResultCode;
import cn.kfqjtdqb.core.service.AssertWaterRentService;
import cn.kfqjtdqb.core.service.PropertyLeasingService;
import cn.kfqjtdqb.core.service.SystemService;
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
public class AssertWaterRentController {

    // 依赖注入
    @Autowired
    private AssertWaterRentService AssertWaterRentService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private PropertyLeasingService propertyLeasingService;

    @Value("${water.state.type}")
    private String STATE_TYPE;


    @RequestMapping(value = "/assertWaterRent")
    public String showAssertWaterRent() {
        return "redirect:/assertWaterRent/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/assertWaterRent/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, String state, String assert_num, Model model) {

        Page<AssertWaterRent> AssertWaterRentList = AssertWaterRentService.selectAssertWaterRentList(page, rows, property_leasing_num, assert_num, state);
        model.addAttribute("page", AssertWaterRentList);
        List<BaseDict> stateType = systemService.findBaseDictListByType(STATE_TYPE);
        model.addAttribute("stateType", stateType);
        //参数回显
        model.addAttribute("state", state);
        model.addAttribute("assert_num", assert_num);
        model.addAttribute("property_leasing_num", property_leasing_num);
        return "assertWaterRent";
    }

    @RequestMapping("/assertWaterRent/edit")
    @ResponseBody
    public AssertWaterRent getAssertWaterRentById(Long id) {
        AssertWaterRent AssertWaterRent = AssertWaterRentService.getAssertWaterRentById(id);
        return AssertWaterRent;
    }

    /*   String id, String property_leasing_num,String assert_num ,String state, String water_rent, String water_num,String reality_water_rent, String rent_start_time,
       String rent_end_time, String water_rent_received,String watermeter_num,String deadline*/
    @RequestMapping("/assertWaterRent/update")
    @ResponseBody
    public ResultCode updateAssertWaterRent(@Valid AssertWaterRent assertWaterRent, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            if (assertWaterRent.getWater_rent_recivied() == null) {
                assertWaterRent.setWater_rent_recivied(BigDecimal.ZERO);
            }
            BigDecimal water_rent_recivied = assertWaterRent.getWater_rent_recivied();  // 表示的是已经收的租金
            BigDecimal reality_water_rent = assertWaterRent.getReality_water_rent();  //表示的本次收的租金
            BigDecimal waterRent = assertWaterRent.getWater_rent();   //表示的是本次应收租金
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(assertWaterRent.getProperty_leasing_num());
            if (waterRent.compareTo( water_rent_recivied.add( reality_water_rent))<=0) {  //表示的是已经缴清
                assertWaterRent.setState(ConstUtils.WATERSTATESUCCESSL);
                propertyLeasing.setWaterState(ConstUtils.WATERSTATESUCCESSL);
            } else {
                assertWaterRent.setState(ConstUtils.WATERSTATEFAIL);
                propertyLeasing.setWaterState(ConstUtils.WATERSTATEFAIL);
            }
            AssertWaterRentService.updateAssertWaterRent(assertWaterRent);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);   //存在问题
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("水费信息创建失败");
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("水费信息创建成功");
        return resultCode;

    }

    @RequestMapping("/assertWaterRent/delete")
    @ResponseBody
    public String deleteAssertWaterRent(Long id) {
        AssertWaterRentService.deleteAssertWaterRent(id);
        return "OK";
    }


    /**
     * 创建资产
     */
    @RequestMapping("/assertWaterRent/create")
    @ResponseBody
    public ResultCode createRental(@Valid AssertWaterRent assertWaterRent, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            if (assertWaterRent.getWater_rent_recivied() == null) {
                assertWaterRent.setWater_rent_recivied(BigDecimal.ZERO);
            }
            BigDecimal water_rent_recivied = assertWaterRent.getWater_rent_recivied();  // 表示的是已经收的租金
            BigDecimal reality_water_rent = assertWaterRent.getReality_water_rent();  //表示的本次收的租金
            BigDecimal waterRent = assertWaterRent.getWater_rent();   //表示的是本次应收租金
            PropertyLeasing propertyLeasing = new PropertyLeasing();
            propertyLeasing.setProperty_leasing_num(assertWaterRent.getProperty_leasing_num());
            if (waterRent .compareTo( water_rent_recivied .add( reality_water_rent))<=0) {  //表示的是已经缴清
                assertWaterRent.setState(ConstUtils.WATERSTATESUCCESSL);
                propertyLeasing.setWaterState(ConstUtils.WATERSTATESUCCESSL);
            } else {
                assertWaterRent.setState(ConstUtils.WATERSTATEFAIL);
                propertyLeasing.setWaterState(ConstUtils.WATERSTATEFAIL);
            }
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);   //存在问题
            int rows = AssertWaterRentService.createAssertWaterRent(assertWaterRent);
            if (rows > 0) {
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


/*    @RequestMapping("/assertWaterRent/find")
    @ResponseBody
    public AssertWaterRent getAssertWaterRentByLeasingNum(String id) {
        PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(Long.parseLong(id));
        //实际已经交的租金
        Double rent_recivied = AssertWaterRentService.getAssertWaterRentCountByLeasingNum(propertyLeasing.getProperty_leasing_num());
        if(rent_recivied==null)  rent_recivied=0d;
        //截止本月应收租金
        double rent_should = 0;
        Date startRentDate = propertyLeasing.getRent_start_time();  //租金开始时间
        Date currentDate = new Date();  //表示的是当前时间
        int month = DateUtils.getDifMonth(startRentDate, currentDate) + 1;   //需要交押金的月份
        int freePriod = propertyLeasing.getRent_free_period();  //获取免租期;
        if (month > propertyLeasing.getRent_period() * 12) {   //表示的是租期已经到了
            rent_should = propertyLeasing.getTotal_rent();
        } else {   //表示的是租期没有到
            if (freePriod - month >= 0) {   //表示的是没有过免租期
                rent_should = 0;
            } else {  //表示的是过了免租期
                String monthRental = propertyLeasing.getMonthly_rental();
                String[] months = monthRental.split(",");
                if (months.length == 1) {  //表示的是每月的租金一样的。
                    rent_should = Double.parseDouble(months[0]) * (month - freePriod);
                } else {   //表示的是每个月的租金不一样。
                    double allRent = 0;
                    for (int i = freePriod + 1; i <= month; i++) {
                        allRent += RentUtils.getMonthRent(i, months);
                    }
                    rent_should = allRent;
                }
            }
        }
        AssertWaterRent AssertWaterRent = new AssertWaterRent();
        AssertWaterRent.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
        AssertWaterRent.setRent_start_time(propertyLeasing.getRent_start_time());
        AssertWaterRent.setRent_end_time(propertyLeasing.getRent_end_time());
        AssertWaterRent.setRental(rent_should);  //截止本月本次应该收多少钱
        AssertWaterRent.setRent_recivied(rent_recivied);  //实际已经收了多少钱
        AssertWaterRent.setReality_rental(rent_should - rent_recivied);
        if (rent_should >= rent_recivied) {
            AssertWaterRent.setState("7");  //表示的已经缴清
        } else {
            AssertWaterRent.setState("6");
        }
        return assertWaterRent;
    }*/


}
