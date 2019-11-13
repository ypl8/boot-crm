package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.SystemService;
import cn.kfqjtdqb.core.service.UserInfoService;
import cn.kfqjtdqb.core.utils.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SystemService systemService;

    @Value("${userInfo.state.type}")
    private String STATE_TYPE;

    @RequestMapping(value = "/userInfo")
    public String showTotalRental() {
        return "redirect:/userInfo/list.action";
    }

    // 客户列表

    @RequestMapping(value = "/userInfo/list")
    @Secured("ROLE_ADMIN")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String userName, String status, Model model) {

        Page<UserInfo> userInfoList = userInfoService.findUserInfoList(page, rows, userName, status);
        model.addAttribute("page", userInfoList);
        List<BaseDict> stateType = systemService.findBaseDictListByType(STATE_TYPE);
        model.addAttribute("stateType", stateType);
        //参数回显
        model.addAttribute("userName", userName);
        model.addAttribute("status", status);
        return "userInfo";
    }


   /* @RequestMapping("/userInfo/login")
    public String login(){
        return "login";
    }
  /*  @RolesAllowed({"ADMIN"})*/
   /*@Secured("ROLE_ADMIN")*/
    /*
    @RequestMapping(value = "/userInfo/login",method = RequestMethod.POST)
    public String login(String userName, String password, Model model ,HttpSession session){
        //通过账号和密码查询客户
        UserInfo user = userInfoService.findUser(userName, password);
        if (user!=null){
            //将用户对象添加到Session中
            session.setAttribute("USER_SESSION",user);
            //跳转到主页面
            return "redirect:/index.action";
        }
        model.addAttribute("msg","账号或密码错误，请重新输入");
        //返回登录页面
        return "login";
    }*/


    @RequestMapping("/userInfo/edit")
    @ResponseBody

    public UserInfo getUserInfoById(Long id) {
        UserInfo userInfo = userInfoService.getUserInfoById(id);
        return userInfo;
    }


    @RequestMapping("/userInfo/update")
    @ResponseBody
    public ResultCode updateUserInfo(@Valid UserInfo userInfo, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            userInfoService.updateUserInfo(userInfo);
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("用户信息更新成功");
        return resultCode;
    }

    @RequestMapping("/userInfo/updatePassword")
    @ResponseBody
    public ResultCode updatePassword( UserInfo userInfo, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            userInfoService.updateUserInfo(userInfo);
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("用户密码更新成功");
        return resultCode;
    }


    @RequestMapping("/userInfo/delete")
    public String deleteUserInfo(Long id) {
        userInfoService.deleteUserInfo(id);
        return "OK";
    }


    /**
     * 创建水信息
     */
    @RequestMapping("/userInfo/create")
    @ResponseBody
/*    @PreAuthorize("hasRole('ROLE_ADMIN')")*/


    public ResultCode createUserInfo(@Valid UserInfo userInfo, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            int rows = userInfoService.createUserInfo(userInfo);
            if (rows > 0) {
                resultCode.setCode(0);
                resultCode.setMsg("用户信息创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("用户信息创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
    }


    @RequestMapping("/userInfo/findUserByIdAndAllRole.action")
    @ResponseBody
    public ResultCode findUserByIdAndAllRole(Long id) throws Exception {
        UserInfo user = userInfoService.findById(id);
        List<Role> roleList = userInfoService.findOtherRole(id);

        ResultCode resultCode = new ResultCode();
        Map map=new HashMap<String,Object>();
        map.put("user",user);
        map.put("roleList",roleList);
        resultCode.setData(map);
        resultCode.setCode(0);
        return resultCode;
    }


    @RequestMapping("/userInfo/addRoleToUser.action")
    @ResponseBody
    public ResultCode addRoleToUser(Long userId, Long roleId) {
        ResultCode  resultCode=new ResultCode();
        try {
            userInfoService.addRoleToUser(userId,roleId);
        }catch (Exception e){
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("用户角色添加成功");
        return resultCode;
    }


}
