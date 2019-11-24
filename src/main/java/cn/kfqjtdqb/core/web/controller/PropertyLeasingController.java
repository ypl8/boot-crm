package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.common.utils.StringUtils;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.*;
import cn.kfqjtdqb.core.utils.*;


import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PropertyLeasingController {

    // 依赖注入
    @Autowired
    private PropertyLeasingService propertyLeasingService;
    @Autowired
    private AssertInfolService assertInfolService;
    @Autowired
    private AssertRentalService assertRentalService;
    @Autowired
    private AssertEstateService assertEstateService;
    @Autowired
    private TotalRentalService totalRentalService;
    @Autowired
    private TotalEstateService totalEstateService;

    @Autowired
    private AssertWaterService assertWaterService;
    @Autowired
    private AssertPowerService assertPowerService;

    @Autowired
    private AssertPowerRentService assertPowerRentService;
    @Autowired
    private AssertWaterRentService assertWaterRentService;

    @Autowired
    private AssertDepositService assertDepositService;
    @Autowired
    private ActivitiService activitiService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private UserInfoService userInfoService;

    @Value("${deposit.state.type}")
    private String STATE_DEPOSIT_TYPE;

    @Value("${rental.state.type}")
    private String STATE_RENTAL_TYPE;


    @Value("${estate.state.type}")
    private String STATE_ESTATE_TYPE;

    @Value("${rental.way.type}")
    private String WAY_RENTAL_TYPE;

    @Value("${waterandelectricity.way.type}")
    private String WAY_WATERANDELETRIC_TYPE;

    @Value("${contract.state.type}")
    private String STATE_CONTRACT_TYPE;

    @Value("${water.state.type}")
    private String STATE_WATER_TYPE;

    @Value("${power.state.type}")
    private String STATE_POWER_TYPE;
    @Value("${contract.type}")
    private String CONTRACT_TYPE;

    @Value("${check.state.type}")
    private String CHECK_STATE_TYPE;

    public static final String csv_propertyLeasing_upload_path = "/home/csv/assertInfol";
    private static final String POINT_SUCCESS_URL = "/propertyLeasing/list.action";
    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/propertyLeasing")
    public String showPropertyLeasing() {
        return "redirect:/propertyLeasing/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/propertyLeasing/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String property_leasing_num, Model model, String collect_rent_way, String collect_rate_way, String property_leasing_state, String community_name, String assert_num, String property_leasing_type,String status) {
      /* if(community_name!=null){
           try {
               community_name = new String(community_name.getBytes("ISO8859-1"), "utf-8");
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
       }*/

        List<String> communityNameList = assertInfolService.findAllCommunityName();
        Page<PropertyLeasing> assertInfolList = propertyLeasingService.findPropertyLeasingList(page, rows, property_leasing_num, collect_rent_way, collect_rate_way, property_leasing_state, community_name, assert_num, property_leasing_type,status);
        model.addAttribute("page", assertInfolList);
        List<BaseDict> depositstateType = systemService.findBaseDictListByType(STATE_DEPOSIT_TYPE);
        List<BaseDict> rentalstateType = systemService.findBaseDictListByType(STATE_RENTAL_TYPE);
        List<BaseDict> estatestateType = systemService.findBaseDictListByType(STATE_ESTATE_TYPE);

        List<BaseDict> rentalWayType = systemService.findBaseDictListByType(WAY_RENTAL_TYPE);
        List<BaseDict> waterAndEletricWayType = systemService.findBaseDictListByType(WAY_WATERANDELETRIC_TYPE);
        List<BaseDict> contractStateType = systemService.findBaseDictListByType(STATE_CONTRACT_TYPE);
        List<BaseDict> waterStateType = systemService.findBaseDictListByType(STATE_WATER_TYPE);
        List<BaseDict> powerStateType = systemService.findBaseDictListByType(STATE_POWER_TYPE);
        List<BaseDict> contractType = systemService.findBaseDictListByType(CONTRACT_TYPE);
        List<BaseDict> checkType = systemService.findBaseDictListByType(CHECK_STATE_TYPE);
        model.addAttribute("checkType", checkType);
        //参数回显

        model.addAttribute("property_leasing_num", property_leasing_num);
        model.addAttribute("collect_rent_way", collect_rent_way);
        model.addAttribute("collect_rate_way", collect_rate_way);
        model.addAttribute("community_name", community_name);
        model.addAttribute("property_leasing_type", property_leasing_type);
        model.addAttribute("assert_num", assert_num);
        model.addAttribute("property_leasing_state", property_leasing_state);
        model.addAttribute("depositStateType", depositstateType);
        model.addAttribute("rentalStateType", rentalstateType);
        model.addAttribute("contractType", contractType);
        model.addAttribute("estateStateType", estatestateType);
        model.addAttribute("waterStateType", waterStateType);
        model.addAttribute("rentalWayType", rentalWayType);
        model.addAttribute("waterAndEletricWayType", waterAndEletricWayType);
        model.addAttribute("contractStateType", contractStateType);
        model.addAttribute("powerStateType", powerStateType);
        model.addAttribute("communityNameList", communityNameList);
        model.addAttribute("status", status);
        return "propertyLeasing";
    }


    @RequestMapping(value = "/propertyLeasing/show")
    public String queryContract(Long id, Model model) {
        PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(id);

        String monthRental = propertyLeasing.getMonthly_rental();
        String[] months = monthRental.split(";");
        if (months.length == 1) {
            model.addAttribute("monthRental", months[0]);
        } else {
            String  str="";

            for (int i = 0; i < months.length; i++) {
                 str+= DateUtils.getNextYear(propertyLeasing.getRent_start_time(),i)+"到"+DateUtils.getNextYear(propertyLeasing.getRent_end_time(), i-months.length+1)+"月租"+months[i]+";\r\n";
            }
            model.addAttribute("monthRental",str);
        }

        model.addAttribute("propertyLeasing", propertyLeasing);
        List<BaseDict> contractType = systemService.findBaseDictListByType(CONTRACT_TYPE);
        List<BaseDict> contractStateType = systemService.findBaseDictListByType(STATE_CONTRACT_TYPE);
        List<BaseDict> rentalWayType = systemService.findBaseDictListByType(WAY_RENTAL_TYPE);
        List<BaseDict> waterAndEletricWayType = systemService.findBaseDictListByType(WAY_WATERANDELETRIC_TYPE);
        model.addAttribute("rentalWayType", rentalWayType);
        model.addAttribute("waterAndEletricWayType", waterAndEletricWayType);
        model.addAttribute("contractStateType", contractStateType);
        model.addAttribute("contractType", contractType);
        return "showContract";
    }


    // 客户列表
    @RequestMapping(value = "/propertyLeasing/queryList")
    public String querylist(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                            String depositState, Model model, String rentalState, String estateState, String waterState, String powerState, String community_name) {
        Page<PropertyLeasing> assertInfolList = propertyLeasingService.findPropertyLeasingByStateList(page, rows, depositState, rentalState, estateState, waterState, powerState, community_name);
        model.addAttribute("page", assertInfolList);
        List<BaseDict> depositstateType = systemService.findBaseDictListByType(STATE_DEPOSIT_TYPE);
        List<BaseDict> rentalstateType = systemService.findBaseDictListByType(STATE_RENTAL_TYPE);
        List<BaseDict> estatestateType = systemService.findBaseDictListByType(STATE_ESTATE_TYPE);
        List<BaseDict> waterStateType = systemService.findBaseDictListByType(STATE_WATER_TYPE);
        List<BaseDict> powerStateType = systemService.findBaseDictListByType(STATE_POWER_TYPE);
        Integer empty_assert_num = assertInfolService.findEmptyAssertCount(ConstUtils.ASSERTINFOLCOUNTUNRENT);
        if (empty_assert_num == null) {
            model.addAttribute("empty_assert_num", 0);
        } else {
            model.addAttribute("empty_assert_num", empty_assert_num);
        }
        CountEmpty countEmpty = propertyLeasingService.findEmptyPropertyLeasingCount();

        if (countEmpty != null) {
            model.addAttribute("empty_rental_num", countEmpty.getEmpty_rental_num());
            model.addAttribute("empty_estate_num", countEmpty.getEmpty_estate_num());
            model.addAttribute("empty_power_num", countEmpty.getEmpty_power_num());
            model.addAttribute("empty_water_num", countEmpty.getEmpty_water_num());
        } else {
            model.addAttribute("empty_rental_num", 0);
            model.addAttribute("empty_estate_num", 0);
            model.addAttribute("empty_power_num", 0);
            model.addAttribute("empty_water_num", 0);
        }
        UserDetails userInfo = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();
        UserInfo userInfol = userInfoService.findUser(userInfo.getUsername(), userInfo.getPassword());
        String processDefinitionKey = "assert";
        List<Task> tasks = activitiService.findTaskByUserId(userInfol.getId().toString(), processDefinitionKey);
        if (tasks == null) {
            model.addAttribute("empty_user_num", 0);
        } else {
            model.addAttribute("empty_user_num", tasks.size());
        }
       /* Integer empty_user_num = userInfoService.findUserCount("23");
        if (empty_assert_num == null) {
            model.addAttribute("empty_user_num", 0);
        } else {
            model.addAttribute("empty_user_num", empty_user_num);
        }*/

        //参数回显
        model.addAttribute("community_name", community_name);
        model.addAttribute("depositStateType", depositstateType);
        model.addAttribute("rentalStateType", rentalstateType);
        model.addAttribute("estateStateType", estatestateType);
        model.addAttribute("waterStateType", waterStateType);
        model.addAttribute("powerStateType", powerStateType);

        model.addAttribute("depositState", depositState);
        model.addAttribute("rentalState", rentalState);
        model.addAttribute("estateState", estateState);
        model.addAttribute("waterState", waterState);
        model.addAttribute("powerState", powerState);

        return "mainTip";
    }

    @RequestMapping("/propertyLeasing/edit")
    @ResponseBody
    public PropertyLeasing getPropertyLeasingById(Long id) {
        PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(id);
        String location = propertyLeasing.getRentalLocation();
        location = location.replaceAll(";", ",");
        propertyLeasing.setRentalLocation(location);
        return propertyLeasing;
    }


    @RequestMapping("/propertyLeasing/editRental")
    @ResponseBody
    public RentalVo getPropertyLeasingAndRentalById(Long id) {

        PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(id);

        //实际已经交的租金
        BigDecimal rent_recivied = assertRentalService.getAssertRentalCountByLeasingNum(propertyLeasing.getProperty_leasing_num());
        List<TotalRental> totalRentals = totalRentalService.getTotalRentalByLeasingNum(propertyLeasing.getProperty_leasing_num());
        List<TotalRental> results = new ArrayList<TotalRental>();

        for (int i = 0; i < totalRentals.size(); i++) {   //添加数据
            if (totalRentals.get(i).getReality_rental().compareTo(totalRentals.get(i).getRental()) < 0) {
                results.add(totalRentals.get(i));
            }
        }
        if (rent_recivied == null) rent_recivied = BigDecimal.ZERO;
        //截止本月应收租金
        BigDecimal rent_should = BigDecimal.ZERO;
        Date currentDate = new Date();  //表示的是当前时间
        rent_should = new BigDecimal(RentUtils.getToalRentByCurrentDate(propertyLeasing, currentDate) + "");
        RentalVo rentalVo = new RentalVo();
        rentalVo.setRent_start_time(propertyLeasing.getRent_start_time());
        rentalVo.setRent_end_time(propertyLeasing.getRent_end_time());
        rentalVo.setRent_recivied(rent_recivied);
        rentalVo.setTotalRentals(results);
        rentalVo.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
        rentalVo.setRental(rent_should);
       /* Double rent_current = RentUtils.getMonthRent(propertyLeasing, currentDate);
        rentalVo.setReality_rental(rent_current);*/
        Date start_time = propertyLeasing.getRent_start_time();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String day = simpleDateFormat.format(start_time).substring(6, 8);
        rentalVo.setDay(day);
     /*   AssertRental assertRental = new AssertRental();
        assertRental.setRent_start_time(propertyLeasing.getRent_start_time());
        assertRental.setRent_end_time(propertyLeasing.getRent_end_time());
        assertRental.setRental(rent_should);  //截止本月本次应该收多少钱
        assertRental.setRent_recivied(rent_recivied);  //实际已经收了多少钱
        assertRental.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());*/
        return rentalVo;
    }


   /* @RequestMapping("/propertyLeasing/editRentalByStartDate")
    @ResponseBody
    public ResultCode getPropertyLeasingAndRentalById(String startDate, String property_leasing_num) {

        PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingByNum(property_leasing_num);

        ResultCode resultCode = new ResultCode();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date currentDate = simpleDateFormat.parse(startDate);
            Double rent_current = RentUtils.getMonthRent(propertyLeasing, currentDate);
            resultCode.setMsg(rent_current + "");
        } catch (ParseException e) {
            e.printStackTrace();
            resultCode.setMsg(e.getMessage());
            resultCode.setCode(-1);
            return resultCode;
        }
        return resultCode;
    }*/


    @RequestMapping("/propertyLeasing/editRentalByStartDate")
    @ResponseBody
    public ResultCode editPropertyLeasingAndRentalById(Long id) {

        TotalRental totalRental = totalRentalService.getTotalRentalById(id);
        ResultCode resultCode = new ResultCode();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            resultCode.setMsg(totalRental.getRental().subtract(totalRental.getReality_rental()) + "");
        } catch (Exception e) {
            resultCode.setMsg(e.getMessage());
            resultCode.setCode(-1);
            return resultCode;
        }
        return resultCode;
    }


    @RequestMapping("/propertyLeasing/editEstateByid")
    @ResponseBody
    public ResultCode editEstateByid(Long id) {
        TotalEstate totalEstate = totalEstateService.getTotalEstateById(id);
        ResultCode resultCode = new ResultCode();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            resultCode.setMsg(totalEstate.getEstate().subtract(totalEstate.getReality_estate()) + "");
        } catch (Exception e) {
            resultCode.setMsg(e.getMessage());
            resultCode.setCode(-1);
            return resultCode;
        }
        return resultCode;
    }


    @RequestMapping("/propertyLeasing/changeState")
    @ResponseBody
    public ResultCode changeState(Long id, String comment_state, @RequestParam(defaultValue = "") String comment) {
        String status = comment_state;
        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();

            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());

            // String processDefinitionKey = "assert";  //第一个版本

            String processDefinitionKey = "contract";

            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);
          /*  PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(id);
            propertyLeasing.setStatus(status);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);*/
            for (Task task : tasks) {
                //2  通过任务对象获取流程实例
                ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                //3 通过流程实例获取“业务键”
                String businessKey = pi.getBusinessKey();
                if (businessKey != null && id.toString().equals(businessKey)) {
                    activitiService.completeTask(userInfo.getId().toString(), task.getId(), status, pi.getId(), comment);
                    PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(id);
                    if(ConstUtils.CheckPASS.equals(propertyLeasing.getStatus())){  //表示的是审核通过的时候
                        Integer month = propertyLeasing.getRent_period();
                        Date startDate = propertyLeasing.getRent_start_time();  //租金开始时间
                        //进行保证金
                        if (propertyLeasing.getDeposit_time() != null) {
                            AssertDeposit assertDeposit = new AssertDeposit();
                            assertDeposit.setRent_start_time(propertyLeasing.getRent_start_time());
                            assertDeposit.setRent_end_time(propertyLeasing.getRent_end_time());
                            assertDeposit.setDeposit(propertyLeasing.getDeposit());
                            assertDeposit.setReality_deposit(propertyLeasing.getDeposit());
                            assertDeposit.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                            assertDeposit.setState(ConstUtils.DEPOSITSTATESUCCESS);
                            assertDeposit.setDeadline(propertyLeasing.getDeposit_time());
                            assertDepositService.createAssertDeposit(assertDeposit);
                        }


                        for (int i = 0; i < month; i++) {  //创建租金明细
                            TotalRental totalRental = new TotalRental();
                            totalRental.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                            totalRental.setCommunity_name(propertyLeasing.getCommunity_name());
                            totalRental.setBuilding_num(propertyLeasing.getBuilding_num());
                            totalRental.setRentalLocation(propertyLeasing.getRentalLocation());
                            totalRental.setTenant(propertyLeasing.getTenant());
                            totalRental.setYear_months(DateUtils.getYearMonth(startDate, i));
                            totalRental.setRental(new BigDecimal(RentUtils.getMonthRent(propertyLeasing, DateUtils.getMonth(startDate, i)) + ""));  //表示的是应该收的租金
                            totalRentalService.createTotalRental(totalRental);
                        }

                        for (int i = 0; i < month; i++) {   //创建物业明细
                            TotalEstate totalEstate = new TotalEstate();
                            totalEstate.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                            totalEstate.setCommunity_name(propertyLeasing.getCommunity_name());
                            totalEstate.setYear_months(DateUtils.getYearMonth(startDate, i));
                            totalEstate.setEstate(propertyLeasing.getEstate_charge_month());
                            totalEstate.setBuilding_num(propertyLeasing.getBuilding_num());
                            totalEstate.setRentalLocation(propertyLeasing.getRentalLocation());
                            totalEstate.setTenant(propertyLeasing.getTenant());
                            //表示的是应该收的租金
                            totalEstateService.createTotalEstate(totalEstate);
                        }
                    }
                }
            }
        } catch (Exception e) {
            resultCode.setMsg(e.getMessage());
            resultCode.setCode(-1);
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("操作成功");

        return resultCode;
    }


    @RequestMapping("/propertyLeasing/propertyLeasingSubmit")
    @ResponseBody
    public ResultCode assertSubmit(Long id) {
        String processDefinitionKey = "contract";
        ResultCode resultCode = new ResultCode();
        try {
            UserDetails userDetails = (UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();
            UserInfo userInfo = userInfoService.findUser(userDetails.getUsername(), userDetails.getPassword());
            activitiService.startProcesser(id, processDefinitionKey, userInfo.getId());  //开启流程
            /*            activitiService.changeState(id, "1");*/
            PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(id);
            propertyLeasing.setStatus(ConstUtils.CheckING);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
            List<Task> tasks = activitiService.findTaskByUserId(userInfo.getId().toString(), processDefinitionKey);
            for (Task task : tasks) {
                //2  通过任务对象获取流程实例
                ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                //3 通过流程实例获取“业务键”
                String businessKey = pi.getBusinessKey();
                if (businessKey != null && id.toString().equals(businessKey)) {
                    activitiService.completeTask(userInfo.getId().toString(), task.getId(), pi.getId(), "合同申请");
                }
            }
        } catch (Exception e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("提交成功");
        return resultCode;
    }


    @RequestMapping("/propertyLeasing/getComments")
    @ResponseBody
    public ResultCode getComment(Long id) {

        ResultCode resultCode = new ResultCode();
        String processDefinitionKey = "contract";
        List<Comment> data = null;
        try {
            data = activitiService.getProcessComments(id, processDefinitionKey);
        } catch (Exception e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setData(data);
        resultCode.setMsg("查询成功");

        return resultCode;
    }


    @RequestMapping("/propertyLeasing/editRentalByLeasingNum")
    @ResponseBody
    public RentalVo getPropertyLeasingAndRentalByLeasingNum(String property_leasing_num) {

        PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingByNum(property_leasing_num);
        //实际已经交的租金
        BigDecimal rent_recivied = assertRentalService.getAssertRentalCountByLeasingNum(propertyLeasing.getProperty_leasing_num());
        List<TotalRental> totalRentals = totalRentalService.getTotalRentalByLeasingNum(propertyLeasing.getProperty_leasing_num());
        List<TotalRental> results = new ArrayList<TotalRental>();

        for (int i = 0; i < totalRentals.size(); i++) {   //添加数据
            if (totalRentals.get(i).getReality_rental().compareTo(totalRentals.get(i).getRental()) < 0) {
                results.add(totalRentals.get(i));
            }
        }
        if (rent_recivied == null) rent_recivied = BigDecimal.ZERO;
        //截止本月应收租金
        BigDecimal rent_should = BigDecimal.ZERO;
        Date currentDate = new Date();  //表示的是当前时间
        rent_should = new BigDecimal(RentUtils.getToalRentByCurrentDate(propertyLeasing, currentDate) + "");
        RentalVo rentalVo = new RentalVo();
        rentalVo.setRent_recivied(rent_recivied);
        rentalVo.setTotalRentals(results);
        rentalVo.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
        rentalVo.setRental(rent_should);
  /*      Double rent_current = RentUtils.getMonthRent(propertyLeasing, currentDate);
        rentalVo.setReality_rental(rent_current);*/
        Date start_time = propertyLeasing.getRent_start_time();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String day = simpleDateFormat.format(start_time).substring(6, 8);
        rentalVo.setDay(day);
     /*   AssertRental assertRental = new AssertRental();
        assertRental.setRent_start_time(propertyLeasing.getRent_start_time());
        assertRental.setRent_end_time(propertyLeasing.getRent_end_time());
        assertRental.setRental(rent_should);  //截止本月本次应该收多少钱
        assertRental.setRent_recivied(rent_recivied);  //实际已经收了多少钱
        assertRental.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());*/
        return rentalVo;
    }


    @RequestMapping("/propertyLeasing/editEstate")
    @ResponseBody
    public EstateVo getPropertyLeasingAndEstateById(Long id) {
        PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(id);
        //实际已经交的物业费
        BigDecimal rent_recivied = assertEstateService.getAssertEstateCountByLeasingNum(propertyLeasing.getProperty_leasing_num());

        List<TotalEstate> totalEstates = totalEstateService.getTotalEstateByLeasingNum(propertyLeasing.getProperty_leasing_num());
        List<TotalEstate> results = new ArrayList<TotalEstate>();
        for (int i = 0; i < totalEstates.size(); i++) {   //添加数据
            if (totalEstates.get(i).getReality_estate().compareTo(totalEstates.get(i).getEstate()) < 0) {
                results.add(totalEstates.get(i));
            }
        }
        if (rent_recivied == null) rent_recivied = BigDecimal.ZERO;
        //截止本月应收租金
        BigDecimal rent_should = BigDecimal.ZERO;
        Date startRentDate = propertyLeasing.getRent_start_time();  //租金开始时间
        Date currentDate = new Date();  //表示的是当前时间
        int month = DateUtils.getDifMonth(startRentDate, currentDate) + 1;   //需要交押金的月份
        rent_should = new BigDecimal(month).multiply(propertyLeasing.getEstate_charge_month());
        EstateVo estateVo = new EstateVo();
        estateVo.setRent_start_time(propertyLeasing.getRent_start_time());
        estateVo.setRent_end_time(propertyLeasing.getRent_end_time());
        estateVo.setReality_estate(propertyLeasing.getEstate_charge_month());
        estateVo.setEstate(rent_should);  //截止本月本次应该收多少钱
        estateVo.setEstate_recivied(rent_recivied);  //实际已经收了多少钱
        estateVo.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
        estateVo.setTotalEstates(results);
        Date start_time = propertyLeasing.getRent_start_time();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String day = simpleDateFormat.format(start_time).substring(6, 8);
        estateVo.setDay(day);
        return estateVo;
    }

    @RequestMapping("/propertyLeasing/update")
    /*    @PreAuthorize("authentication.principal.username=='ypl'")*/
    @ResponseBody
    public ResultCode updatePropertyLeasing(@Valid PropertyLeasing propertyLeasing, Errors errors) throws Exception {

        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }

        Double result = RentUtils.getToalRent(propertyLeasing);
        propertyLeasing.setTotal_rent(result);
        String location = propertyLeasing.getRentalLocation();
        String[] locations = location.split(",");
        location = location.replaceAll(",", ";");
        propertyLeasing.setRentalLocation(location);
        List<String> assertInfolList = new ArrayList<>();

        for (int i = 0; i < locations.length; i++) {
            AssertInfol assertInfol = assertInfolService.findAssertInfolListByCommunityName(propertyLeasing.getCommunity_name(), propertyLeasing.getBuilding_num(), locations[i]);
            assertInfolList.add(assertInfol.getAssert_num());
        }

        //判断有没有更换资产
        List<AssertLeasing> assertLeasings = propertyLeasingService.selectAssertLeasingByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());

        List<String> assertLeasingList = new ArrayList<String>();

        for (AssertLeasing assertLeasing : assertLeasings) {
            assertLeasingList.add(assertLeasing.getAssert_num());
        }

        boolean flag = assertInfolList.containsAll(assertLeasingList) && (assertInfolList.size() == assertLeasingList.size());

        if (!flag) {
            //移除掉以前所有的关联关系
            propertyLeasingService.deleteAssertLeasingByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());

            for (int i = 0; i < assertLeasings.size(); i++) {  //修改以前合同对应资产状态
                AssertInfol assertInfol = new AssertInfol();
                assertInfol.setAssert_num(assertLeasings.get(i).getAssert_num());
                assertInfol.setFloor_state("2");  //表示的是已经被占用了的
                assertInfolService.updateAssertInfol(assertInfol);
            }

            for (String assert_num : assertInfolList) {  //添加新的资产关联。
                AssertLeasing assertLeasing = new AssertLeasing();
                assertLeasing.setAssert_num(assert_num);
                assertLeasing.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                AssertInfol assertInfol = new AssertInfol();
                assertInfol.setAssert_num(assert_num);
                if (propertyLeasing.getProperty_leasing_state().equals("17")) {
                    assertInfol.setFloor_state("2");  //表示的是空闲的
                } else {
                    assertInfol.setFloor_state("1");  //表示的是已经被占用了的
                }
                /*  assertInfol.setFloor_state("1");  //表示的是已经被占用了的*/
                assertInfolService.updateAssertInfol(assertInfol);
                propertyLeasingService.addAssertLeasing(assertLeasing);

            }

        }
       /* //修改保证金
        AssertDeposit assertDeposit = new AssertDeposit();
        assertDeposit.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
        assertDeposit.setDeposit(propertyLeasing.getDeposit());
        assertDeposit.setReality_deposit(propertyLeasing.getDeposit());
        assertDeposit.setDeadline(propertyLeasing.getDeposit_time());
        assertDeposit.setState(ConstUtils.DEPOSITSTATESUCCESS);
        assertDepositService.updateAssertDeposit(assertDeposit);*/


       /* //删除记录信息
        assertRentalService.deleteAssertRentalByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());
        assertEstateService.deleteAssertEstateByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());

        //删除汇总信息
        totalRentalService.deleteTotalRentalByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());
        totalEstateService.deleteTotalEstateByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());

        //删除水费信息
        assertWaterService.deleteAssertWaterByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());
        assertWaterRentService.deleteAssertWaterRentByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());

        //删除电信息
        assertPowerRentService.deleteAssertPowerRentByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());
        assertPowerService.deleteAssertPowerByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());*/

/*
        Integer month = propertyLeasing.getRent_period();
        Date startDate = propertyLeasing.getRent_start_time();*/

        //租金的判断
        BigDecimal rent_recivied = assertRentalService.getAssertRentalCountByLeasingNum(propertyLeasing.getProperty_leasing_num());
        if (rent_recivied == null) rent_recivied = BigDecimal.ZERO;
        //截止本月应收租金
        BigDecimal rent_should = BigDecimal.ZERO;
        rent_should = new BigDecimal(RentUtils.getToalRentByCurrentDate(propertyLeasing, new Date()));
        if (rent_should.compareTo(rent_recivied) <= 0) {
            propertyLeasing.setRentalState(ConstUtils.RENTALSTATESUCCESS);  //表示的已经缴清
        } else {
            propertyLeasing.setRentalState(ConstUtils.RENTALSTATEFAIL);
        }

        //物业费判断
        BigDecimal estate_recivied = assertEstateService.getAssertEstateCountByLeasingNum(propertyLeasing.getProperty_leasing_num());   //表示的是已经收了的物业费
        if (estate_recivied == null) estate_recivied = BigDecimal.ZERO;
        //截止本月应收物业费
        BigDecimal estate_should = BigDecimal.ZERO;
        Date startRentDate = propertyLeasing.getRent_start_time();  //租金开始时间
        Date currentDate = new Date();  //表示的是当前时间
        int monthNum = DateUtils.getDifMonth(startRentDate, currentDate) + 1;   //需要交物业费的月份
        estate_should = new BigDecimal(monthNum).multiply(propertyLeasing.getEstate_charge_month());
        if (estate_should.compareTo(estate_recivied) <= 0) {
            propertyLeasing.setEstateState(ConstUtils.ESTATESTATESUCCESS);  //表示的已经缴清
        } else {
            propertyLeasing.setEstateState(ConstUtils.ESTATESTATEFAIL);
        }
        propertyLeasing.setStatus(ConstUtils.CheckUNCOMINT);

        try {
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
           /* for (int i = 0; i < month; i++) {  //创建租金明细
                TotalRental totalRental = new TotalRental();
                totalRental.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                totalRental.setBuilding_num(propertyLeasing.getBuilding_num());
                totalRental.setRentalLocation(propertyLeasing.getRentalLocation());
                totalRental.setTenant(propertyLeasing.getTenant());
                totalRental.setCommunity_name(propertyLeasing.getCommunity_name());
                totalRental.setYear_months(DateUtils.getYearMonth(startDate, i));
                totalRental.setRental(new BigDecimal(RentUtils.getMonthRent(propertyLeasing, DateUtils.getMonth(startDate, i)) + ""));  //表示的是应该收的租金
                totalRentalService.createTotalRental(totalRental);
            }

            for (int i = 0; i < month; i++) {   //创建物业明细
                TotalEstate totalEstate = new TotalEstate();
                totalEstate.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                totalEstate.setCommunity_name(propertyLeasing.getCommunity_name());
                totalEstate.setBuilding_num(propertyLeasing.getBuilding_num());
                totalEstate.setRentalLocation(propertyLeasing.getRentalLocation());
                totalEstate.setTenant(propertyLeasing.getTenant());
                totalEstate.setYear_months(DateUtils.getYearMonth(startDate, i));
                totalEstate.setEstate(propertyLeasing.getEstate_charge_month());

                 //表示的是应该收的租金
                totalEstateService.createTotalEstate(totalEstate);
            }*/

        } catch (Exception e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setMsg("合同信息修改成功");
        resultCode.setCode(0);
        return resultCode;

    }


    @RequestMapping("/propertyLeasing/delete")
    @ResponseBody
    @Secured("ROLE_ADMIN")
    public ResultCode deletePropertyLeasing(Long id) {
        //在删除合同之前去查找关联关系中是否还有对于的资产关系

        ResultCode resultCode = new ResultCode();
        PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingWithAssert(id);
        if (propertyLeasing != null && propertyLeasing.getAssertInfols() != null && propertyLeasing.getAssertInfols().size() > 0) {  //表示的是存在关联关系
            resultCode.setCode(-1);
            resultCode.setMsg("请先移除合同上的所有资产");
            return resultCode;
        }

        String property_leasing_num = propertyLeasing.getProperty_leasing_num();

        try {
            //删除押金信息
            assertDepositService.deleteAssertDepositByLeasingNum(property_leasing_num);
            //删除记录信息
            totalEstateService.deleteTotalEstateByPropertyLeasingNum(property_leasing_num);
            totalRentalService.deleteTotalRentalByPropertyLeasingNum(property_leasing_num);
            //删除记录信息
            assertRentalService.deleteAssertRentalByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());
            assertEstateService.deleteAssertEstateByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());

            //删除水费信息
            assertWaterService.deleteAssertWaterByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());
            assertWaterRentService.deleteAssertWaterRentByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());

            //删除电信息
            assertPowerRentService.deleteAssertPowerRentByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());
            assertPowerService.deleteAssertPowerByPropertyLeasingNum(propertyLeasing.getProperty_leasing_num());

            //删除关联关系
            propertyLeasingService.deletePropertyLeasing(id);

        } catch (Exception e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("合同删除成功");
        return resultCode;
    }


    /**
     * 创建合同
     */
    @RequestMapping("/propertyLeasing/create")
    @ResponseBody
    public ResultCode propertyLeasingCreate(@Valid PropertyLeasing propertyLeasing, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            //关联资产
            String community_name = propertyLeasing.getCommunity_name();
            String building_num = propertyLeasing.getBuilding_num();
            String location = propertyLeasing.getRentalLocation();
            String[] locations = location.split(",");
            location = location.replaceAll(",", ";");
            propertyLeasing.setRentalLocation(location);
            List<AssertInfol> list = new ArrayList<>();
            for (int i = 0; i < locations.length; i++) {
                AssertInfol assertInfol = assertInfolService.findAssertInfolListByCommunityName(community_name, building_num, locations[i]);
                list.add(assertInfol);
            }


            //租金的判断
            BigDecimal rent_recivied = assertRentalService.getAssertRentalCountByLeasingNum(propertyLeasing.getProperty_leasing_num());
            if (rent_recivied == null) rent_recivied = BigDecimal.ZERO;
            //截止本月应收租金
            BigDecimal rent_should = BigDecimal.ZERO;
            rent_should = new BigDecimal(RentUtils.getToalRentByCurrentDate(propertyLeasing, new Date()));
            if (rent_should.compareTo(rent_recivied) <= 0) {
                propertyLeasing.setRentalState(ConstUtils.RENTALSTATESUCCESS);  //表示的已经缴清
            } else {
                propertyLeasing.setRentalState(ConstUtils.RENTALSTATEFAIL);
            }

            //物业费判断
            BigDecimal estate_recivied = assertEstateService.getAssertEstateCountByLeasingNum(propertyLeasing.getProperty_leasing_num());   //表示的是已经收了的物业费
            if (estate_recivied == null) estate_recivied = BigDecimal.ZERO;
            //截止本月应收物业费
            BigDecimal estate_should = BigDecimal.ZERO;
            Date startRentDate = propertyLeasing.getRent_start_time();  //租金开始时间
            Date currentDate = new Date();  //表示的是当前时间
            int monthNum = DateUtils.getDifMonth(startRentDate, currentDate) + 1;   //需要交物业费的月份
            estate_should = new BigDecimal(monthNum).add(propertyLeasing.getEstate_charge_month());
            if (estate_should.compareTo(estate_recivied) <= 0) {
                propertyLeasing.setEstateState(ConstUtils.ESTATESTATESUCCESS);  //表示的已经缴清
            } else {
                propertyLeasing.setEstateState(ConstUtils.ESTATESTATEFAIL);
            }
            propertyLeasing.setDepositState(ConstUtils.DEPOSITSTATEFAIL);
            propertyLeasing.setWaterState(ConstUtils.WATERSTATESUCCESSL);
            propertyLeasing.setPowerState(ConstUtils.POWERSTATESUCCESS);
            Double result = RentUtils.getToalRent(propertyLeasing);
            propertyLeasing.setTotal_rent(result);

            propertyLeasing.setStatus(ConstUtils.CheckUNCOMINT);
            int rows = propertyLeasingService.addPropertyLeasing(propertyLeasing);
            if (rows > 0) {
                Integer month = propertyLeasing.getRent_period();
                Date startDate = propertyLeasing.getRent_start_time();
                try {
                    for (int i = 0; i < list.size(); i++) {
                        AssertLeasing assertLeasing = new AssertLeasing();
                        assertLeasing.setAssert_num(list.get(i).getAssert_num());
                        assertLeasing.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                        AssertInfol assertInfol = new AssertInfol();
                        assertInfol.setAssert_num(list.get(i).getAssert_num());
                        if (propertyLeasing.getProperty_leasing_state().equals("17")) {
                            assertInfol.setFloor_state("2");  //表示的是已经被占用了的
                        } else {
                            assertInfol.setFloor_state("1");  //表示的是已经被占用了的
                        }
                        propertyLeasingService.addAssertLeasing(assertLeasing);
                        assertInfolService.updateAssertInfol(assertInfol);
                    }

                 /*   //进行保证金
                    if (propertyLeasing.getDeposit_time() != null) {
                        AssertDeposit assertDeposit = new AssertDeposit();
                        assertDeposit.setRent_start_time(propertyLeasing.getRent_start_time());
                        assertDeposit.setRent_end_time(propertyLeasing.getRent_end_time());
                        assertDeposit.setDeposit(propertyLeasing.getDeposit());
                        assertDeposit.setReality_deposit(propertyLeasing.getDeposit());
                        assertDeposit.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                        assertDeposit.setState(ConstUtils.DEPOSITSTATESUCCESS);
                        assertDeposit.setDeadline(propertyLeasing.getDeposit_time());
                        assertDepositService.createAssertDeposit(assertDeposit);
                    }


                    for (int i = 0; i < month; i++) {  //创建租金明细
                        TotalRental totalRental = new TotalRental();
                        totalRental.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                        totalRental.setCommunity_name(propertyLeasing.getCommunity_name());
                        totalRental.setBuilding_num(propertyLeasing.getBuilding_num());
                        totalRental.setRentalLocation(propertyLeasing.getRentalLocation());
                        totalRental.setTenant(propertyLeasing.getTenant());
                        totalRental.setYear_months(DateUtils.getYearMonth(startDate, i));
                        totalRental.setRental(new BigDecimal(RentUtils.getMonthRent(propertyLeasing, DateUtils.getMonth(startDate, i)) + ""));  //表示的是应该收的租金
                        totalRentalService.createTotalRental(totalRental);
                    }

                    for (int i = 0; i < month; i++) {   //创建物业明细
                        TotalEstate totalEstate = new TotalEstate();
                        totalEstate.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                        totalEstate.setCommunity_name(propertyLeasing.getCommunity_name());
                        totalEstate.setYear_months(DateUtils.getYearMonth(startDate, i));
                        totalEstate.setEstate(propertyLeasing.getEstate_charge_month());
                        totalEstate.setBuilding_num(propertyLeasing.getBuilding_num());
                        totalEstate.setRentalLocation(propertyLeasing.getRentalLocation());
                        totalEstate.setTenant(propertyLeasing.getTenant());
                        ;  //表示的是应该收的租金
                        totalEstateService.createTotalEstate(totalEstate);
                    }*/
                } catch (Exception e) {
                    resultCode.setCode(-1);
                    resultCode.setMsg(e.getMessage());
                    return resultCode;
                }
                resultCode.setCode(0);
                resultCode.setMsg("合同信息创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("合同信息创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
    }

    /**
     * 添加依赖关系
     */
    @RequestMapping("/propertyLeasing/add")
    @ResponseBody
    public ResultCode propertyLeasingCreate(String property_leasing_num, String assert_num, String water_num, String electric_num) {
        AssertLeasing assertLeasing = new AssertLeasing();
        assertLeasing.setAssert_num(assert_num);
        assertLeasing.setProperty_leasing_num(property_leasing_num);
        assertLeasing.setWater_num(new BigDecimal(water_num));
        assertLeasing.setElectric_num(new BigDecimal(electric_num));
        PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingByNum(property_leasing_num);
        ResultCode resultCode = new ResultCode();
       /* if(propertyLeasing!=null&&ConstUtils.CheckPASS.equals(propertyLeasing.getStatus())){
            resultCode.setCode(-1);
            resultCode.setMsg("该合同还没有初始化通过");
            return resultCode;
        }*/

        /*    int count = propertyLeasingService.selectAssertLeasingListCount(assertLeasing);*/
        try {
            propertyLeasingService.updateAssertLeasing(assertLeasing);
        } catch (Exception e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("初始化水电成功");
        return resultCode;
    }


    @RequestMapping("/propertyLeasing/find")
    @ResponseBody
    public ResultCode findPropertyLeasingWithAssert(Long id) {
        ResultCode resultCode = new ResultCode();
        PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingWithAssert(id);
        resultCode.setCode(0);
        resultCode.setData(propertyLeasing);
        return resultCode;
    }

    @RequestMapping("/propertyLeasing/findByPropertyLeasingNum")
    @ResponseBody
    public PropertyLeasing findPropertyLeasingByPropertyLeasingNum(String property_leasing_num) {
        PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingByNum(property_leasing_num);
        return propertyLeasing;
    }

    @RequestMapping("/propertyLeasing/idlestate")
    @ResponseBody
    public List<AssertLeasing> getAssertInfolIdleState(String property_leasing_num) {
        /* PropertyLeasing propertyLeasing = propertyLeasingService.findPropertyLeasingByNum(property_leasing_num);*/
        List<AssertLeasing> assertLeasings = propertyLeasingService.selectAssertLeasingByPropertyLeasingNum(property_leasing_num);
        return assertLeasings;
    }

    @RequestMapping("/propertyLeasing/showUpload")
    public ModelAndView showUpload() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("propertyLeasingUpload");
        return mv;
    }

    @RequestMapping("/propertyLeasing/uploadPropertyLeasing")
    public ModelAndView uploadPropertyLeasing(HttpServletRequest request, @RequestParam MultipartFile uploadFile) {
        ModelAndView mv = new ModelAndView();
        try {
            String url = csv_propertyLeasing_upload_path;
            String fileName = uploadFile.getOriginalFilename();
            Assert.isEmpty(fileName, "请先上传csv文件");
            File file = new File(url, fileName);
            uploadFile.transferTo(file);
            ArrayList<String[]> dateList = CSVUtils.csvReader(file);
            //根据制定贷款线索csv文件表头含义，将字段逐个保存到实体中并保存到数据库
            for (int i = 0; i < dateList.size(); i++) {
                String[] content = dateList.get(i);
                PropertyLeasing propertyLeasing = new PropertyLeasing();
                propertyLeasing.setProperty_leasing_num(content[1]);
                propertyLeasing.setCommunity_name(content[2]);
                propertyLeasing.setBuilding_num(content[3]);
                propertyLeasing.setRentalLocation(content[4]);
                propertyLeasing.setTenant(content[5]);
                propertyLeasing.setRental_area(new BigDecimal(content[6]));
                propertyLeasing.setRent_charge_standard(content[7]);
                propertyLeasing.setMonthly_rental(content[8]);
                propertyLeasing.setSign_in_time(StringUtils.convert(content[9]));
                propertyLeasing.setRent_free_period(Integer.parseInt(content[10]));
                propertyLeasing.setRent_period(Integer.parseInt(content[11]));
                propertyLeasing.setCollect_rent_way(content[12]);
                propertyLeasing.setCollect_rent_time(content[13]);
                propertyLeasing.setEstate_charge_standard(new BigDecimal(content[14]));
                propertyLeasing.setEstate_charge_month(new BigDecimal(content[15]));
                propertyLeasing.setDeposit(new BigDecimal(content[16]));
                propertyLeasing.setDeposit_time(StringUtils.convert(content[17]));
                propertyLeasing.setWater_rate(new BigDecimal(content[18]));
                propertyLeasing.setPower_rate(new BigDecimal(content[19]));
                propertyLeasing.setCollect_rate_way(content[20]);
                propertyLeasing.setRent_start_time(StringUtils.convert(content[21]));
                propertyLeasing.setRent_end_time(StringUtils.convert(content[22]));
                propertyLeasing.setRemark(content[23]);
                propertyLeasing.setStatus(ConstUtils.CheckUNCOMINT);
                //租金的判断
                BigDecimal rent_recivied = assertRentalService.getAssertRentalCountByLeasingNum(propertyLeasing.getProperty_leasing_num());
                if (rent_recivied == null) rent_recivied = BigDecimal.ZERO;
                //截止本月应收租金
                BigDecimal rent_should = BigDecimal.ZERO;
                rent_should = new BigDecimal(RentUtils.getToalRentByCurrentDate(propertyLeasing, new Date()) + "");
                if (rent_should.compareTo(rent_recivied) <= 0) {
                    propertyLeasing.setRentalState(ConstUtils.RENTALSTATESUCCESS);  //表示的已经缴清
                } else {
                    propertyLeasing.setRentalState(ConstUtils.RENTALSTATEFAIL);
                }

                //物业费判断
                BigDecimal estate_recivied = assertEstateService.getAssertEstateCountByLeasingNum(propertyLeasing.getProperty_leasing_num());   //表示的是已经收了的物业费
                if (estate_recivied == null) estate_recivied = BigDecimal.ZERO;
                //截止本月应收物业费
                BigDecimal estate_should = BigDecimal.ZERO;
                Date startRentDate = propertyLeasing.getRent_start_time();  //租金开始时间
                Date currentDate = new Date();  //表示的是当前时间
                int monthNum = DateUtils.getDifMonth(startRentDate, currentDate) + 1;   //需要交物业费的月份
                estate_should = new BigDecimal(monthNum).multiply(propertyLeasing.getEstate_charge_month());
                if (estate_should.compareTo(estate_recivied) <= 0) {
                    propertyLeasing.setEstateState(ConstUtils.ESTATESTATESUCCESS);  //表示的已经缴清
                } else {
                    propertyLeasing.setEstateState(ConstUtils.ESTATESTATEFAIL);
                }

                propertyLeasing.setWaterState(ConstUtils.WATERSTATESUCCESSL);
                propertyLeasing.setPowerState(ConstUtils.POWERSTATESUCCESS);
                propertyLeasing.setDepositState(ConstUtils.DEPOSITSTATEFAIL);
                if (propertyLeasing.getRent_end_time().before(new Date())) {  //表示过期
                    propertyLeasing.setProperty_leasing_state("17");
                } else {
                    propertyLeasing.setProperty_leasing_state("16");
                }
                Double result = RentUtils.getToalRent(propertyLeasing);
                propertyLeasing.setTotal_rent(result);
                String community_name = propertyLeasing.getCommunity_name();
                String building_num = propertyLeasing.getBuilding_num();
                String location = propertyLeasing.getRentalLocation();
                String[] locations = location.split(";");
                List<AssertInfol> list = new ArrayList<>();
                for (int j = 0; j < locations.length; j++) {
                    AssertInfol assertInfol = assertInfolService.findAssertInfolListByCommunityName(community_name, building_num, locations[j]);
                    if (assertInfol != null) {
                        list.add(assertInfol);
                    }
                }
                if (list == null || list.size() == 0) {
                    String errorMsg = "资产信息csv导入失败,原因:" + "不存在对应的资产信息";
                    mv.addObject("msg", errorMsg);
                    mv.setViewName("error");
                    return mv;
                }
                int rows = propertyLeasingService.addPropertyLeasing(propertyLeasing);
                if (rows > 0) {


                    for (int j = 0; j < list.size(); j++) {
                        AssertLeasing assertLeasing = new AssertLeasing();
                        assertLeasing.setAssert_num(list.get(j).getAssert_num());
                        assertLeasing.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                        AssertInfol assertInfol = new AssertInfol();
                        assertInfol.setAssert_num(list.get(j).getAssert_num());
                        if (propertyLeasing.getProperty_leasing_state().equals("17")) {
                            assertInfol.setFloor_state("2");  //表示的是已经被占用了的
                        } else {
                            assertInfol.setFloor_state("1");  //表示的是已经被占用了的
                        }
                        propertyLeasingService.addAssertLeasing(assertLeasing);
                        assertInfolService.updateAssertInfol(assertInfol);
                    }

                    /*Integer month = propertyLeasing.getRent_period();
                    Date startDate = propertyLeasing.getRent_start_time();
                    //进行保证金
                    if (propertyLeasing.getDeposit_time() != null) {
                        AssertDeposit assertDeposit = new AssertDeposit();
                        assertDeposit.setRent_start_time(propertyLeasing.getRent_start_time());
                        assertDeposit.setRent_end_time(propertyLeasing.getRent_end_time());
                        assertDeposit.setDeposit(propertyLeasing.getDeposit());
                        assertDeposit.setReality_deposit(propertyLeasing.getDeposit());
                        assertDeposit.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                        assertDeposit.setState(ConstUtils.DEPOSITSTATESUCCESS);
                        assertDeposit.setDeadline(propertyLeasing.getDeposit_time());
                        assertDepositService.createAssertDeposit(assertDeposit);
                    }
                    for (int index = 0; index < month; index++) {
                        TotalRental totalRental = new TotalRental();
                        totalRental.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                        totalRental.setTenant(propertyLeasing.getTenant());
                        totalRental.setCommunity_name(propertyLeasing.getCommunity_name());
                        totalRental.setBuilding_num(propertyLeasing.getBuilding_num());
                        totalRental.setRentalLocation(propertyLeasing.getRentalLocation());
                        totalRental.setYear_months(DateUtils.getYearMonth(startDate, index));
                        totalRental.setRental(new BigDecimal(RentUtils.getMonthRent(propertyLeasing, DateUtils.getMonth(startDate, index))));  //表示的是应该收的租金
                        totalRentalService.createTotalRental(totalRental);
                    }

                    for (int index = 0; index < month; index++) {   //创建物业明细
                        TotalEstate totalEstate = new TotalEstate();
                        totalEstate.setBuilding_num(propertyLeasing.getBuilding_num());
                        totalEstate.setRentalLocation(propertyLeasing.getRentalLocation());
                        totalEstate.setTenant(propertyLeasing.getTenant());
                        totalEstate.setProperty_leasing_num(propertyLeasing.getProperty_leasing_num());
                        totalEstate.setCommunity_name(propertyLeasing.getCommunity_name());
                        totalEstate.setYear_months(DateUtils.getYearMonth(startDate, index));
                        totalEstate.setEstate(propertyLeasing.getEstate_charge_month());
                        ;  //表示的是应该收的租金
                        totalEstateService.createTotalEstate(totalEstate);
                    }*/
                }
            }
            String successMsg = "资产信息csv文件导入成功";
            mv.addObject("msg", successMsg);
            mv.addObject("url", POINT_SUCCESS_URL);
            mv.setViewName("success");
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = "资产信息csv导入失败,原因:" + e.getMessage();
            mv.addObject("msg", errorMsg);
            mv.setViewName("error");
        }
        return mv;

    }

    @RequestMapping("/propertyLeasing/downloadPropertyLeasing")
    public void downloadAssertInfol(HttpServletResponse response, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                                    @RequestParam String property_leasing_num, @RequestParam String collect_rent_way, @RequestParam String collect_rate_way, @RequestParam String property_leasing_state, @RequestParam String community_name, @RequestParam String assert_num, String property_leasing_type,String status) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Page<PropertyLeasing> propertyLeasingList = propertyLeasingService.findPropertyLeasingList(page, rows, property_leasing_num, collect_rent_way, collect_rate_way, property_leasing_state, community_name, assert_num, property_leasing_type,status);

        //定义csv文件名称
        String csvFileName = "合同信息表";

        //定义csv表头
        String colNames = "序号,租赁合同编号,小区名称,栋号,承租位置,承租人,承租面积,租金收费标准（元/平米/月),月租（元）," +
                "合同签订时间,免租期（月）,合同期（年）,租金缴纳方式,租金缴纳时间,物业收费标准（元/平米/月）,月物业费（元）," +
                "保证金（元）,	保证金到账时间	,水费（元/吨）,电费（元/度）,	水电费收费方式,租赁实际起始时间,租赁实际结束时间,备注,状态,总租金";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //定义表头对应字段的key
        String mapKey = "id,property_leasing_num,community_name,building_num,rentalLocation,tenant,rental_area,rent_charge_standard,monthly_rental,sign_in_time,rent_free_period,rent_period,collect_rent_way,collect_rent_time,estate_charge_standard," +
                "estate_charge_month,deposit,deposit_time,water_rate,power_rate,collect_rate_way,rent_start_time,rent_end_time,remark,property_leasing_state,total_rent";        //遍历保存查询数据集到dataList中
        for (PropertyLeasing propertyLeasing : propertyLeasingList.getRows()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", propertyLeasing.getId());
            map.put("property_leasing_num", propertyLeasing.getProperty_leasing_num());
            map.put("community_name", propertyLeasing.getCommunity_name());
            map.put("building_num", propertyLeasing.getBuilding_num());
            map.put("rentalLocation", propertyLeasing.getRentalLocation());
            map.put("tenant", propertyLeasing.getTenant());
            map.put("rental_area", propertyLeasing.getRental_area());
            map.put("rent_charge_standard", propertyLeasing.getRent_charge_standard());
            map.put("monthly_rental", propertyLeasing.getMonthly_rental());
            map.put("sign_in_time", sdf.format(propertyLeasing.getSign_in_time()));
            map.put("rent_free_period", propertyLeasing.getRent_free_period());
            map.put("rent_period", propertyLeasing.getRent_period());
            map.put("collect_rent_way", propertyLeasing.getCollect_rent_way());
            map.put("collect_rent_time", propertyLeasing.getCollect_rent_time());
            map.put("estate_charge_standard", propertyLeasing.getEstate_charge_standard());
            map.put("estate_charge_month", propertyLeasing.getEstate_charge_month());
            map.put("deposit", propertyLeasing.getDeposit());
            map.put("deposit_time", sdf.format(propertyLeasing.getDeposit_time()));
            map.put("water_rate", propertyLeasing.getWater_rate());
            map.put("power_rate", propertyLeasing.getPower_rate());
            map.put("collect_rate_way", propertyLeasing.getCollect_rate_way());
            map.put("rent_start_time", sdf.format(propertyLeasing.getRent_start_time()));
            map.put("rent_end_time", sdf.format(propertyLeasing.getRent_end_time()));
            map.put("remark", propertyLeasing.getRemark());
            map.put("property_leasing_state", propertyLeasing.getProperty_leasing_state());
            map.put("total_rent", propertyLeasing.getTotal_rent());
            dataList.add(map);
        }

        CSVUtils.csvWrite(csvFileName, dataList, colNames, mapKey, response);
    }


}
