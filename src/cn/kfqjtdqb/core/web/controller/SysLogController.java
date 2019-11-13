package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.Permission;
import cn.kfqjtdqb.core.bean.ResultCode;
import cn.kfqjtdqb.core.bean.Role;
import cn.kfqjtdqb.core.bean.SysLog;
import cn.kfqjtdqb.core.service.RoleService;
import cn.kfqjtdqb.core.service.SysLogService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;


    @RequestMapping(value = "/sysLog")
    public String showRole() {
        return "redirect:/sysLog/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/sysLog/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       String userName, Model model) {

        Page<SysLog> roleList = sysLogService.selectSysLogList(page, rows,userName);
        model.addAttribute("page", roleList);
        //参数回显
        model.addAttribute("userName", userName);
        return "sysLog";
    }




}
