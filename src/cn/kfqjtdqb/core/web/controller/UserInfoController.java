package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.SystemService;
import cn.kfqjtdqb.core.service.UserInfoService;
import cn.kfqjtdqb.core.utils.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

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
    public ResultCode updateAssertWater(@Valid UserInfo userInfo, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            userInfoService.updateUserInfo(userInfo);
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg("每个月租金信息更新失败");
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("每个月租金信息更新成功");
        return resultCode;
    }

    @RequestMapping("/userInfo/delete")
    @ResponseBody
    public String deleteUserInfo(Long id) {
        userInfoService.deleteUserInfo(id);
        return "OK";
    }


    /**
     * 创建水信息
     */
    @RequestMapping("/userInfo/create")
    @ResponseBody

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

}
