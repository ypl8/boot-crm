package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.ResultCode;
import cn.kfqjtdqb.core.bean.Permission;
import cn.kfqjtdqb.core.service.PermissionService;
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

@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "/permission")
    public String showPermission() {
        return "redirect:/permission/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/permission/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       Long id, Model model,Long roleId) {


        Page<Permission> permissionList = permissionService.findPermissionList(page, rows,id,roleId );
        model.addAttribute("page", permissionList);
        //参数回显
        model.addAttribute("id", id);
        model.addAttribute("roleId", roleId);
        return "permission";
    }


    @RequestMapping("/permission/edit")
    @ResponseBody
    public Permission getPermissionById(Long id) {
        Permission permission = permissionService.getPermissionById(id);
        return permission;
    }


    @RequestMapping("/permission/update")
    @ResponseBody
    public ResultCode updatePermission(@Valid Permission permission, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            permissionService.updatePermission(permission);
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("权限信息更新成功");
        return resultCode;
    }

    @RequestMapping("/permission/delete")
    @ResponseBody
    public String deletePermission(Long id) {
        permissionService.deletePermission(id);
        return "OK";
    }


    /**
     * 创建水信息
     */
    @RequestMapping("/permission/create")
    @ResponseBody

    public ResultCode createPermission(@Valid Permission permission, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            int rows = permissionService.createPermission(permission);
            if (rows==-2147482646||rows > 0) {
                resultCode.setCode(0);
                resultCode.setMsg("权限信息创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("权限信息创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
    }

}
