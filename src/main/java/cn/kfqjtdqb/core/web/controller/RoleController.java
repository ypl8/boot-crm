package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.service.SystemService;
import cn.kfqjtdqb.core.service.RoleService;
import cn.kfqjtdqb.core.utils.ErrorUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;
    public  final Log logger = LogFactory.getLog(LogAop.class);

    @RequestMapping(value = "/role")
    public String showRole() {
        return "redirect:/role/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/role/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                       Long id, Model model,Long  userId) {

        Page<Role> roleList = roleService.findRoleList(page, rows,id, userId);
        model.addAttribute("page", roleList);
        //参数回显
        model.addAttribute("id", id);
        model.addAttribute("userId", userId);
        return "role";
    }


    @RequestMapping("/role/edit")
    @ResponseBody
    public Role getRoleById(Long id) throws Exception {
        Role role = roleService.getRoleById(id);
        return role;
    }


    @RequestMapping("/role/update")
    @ResponseBody
    public ResultCode updateRole(@Valid Role role, Errors errors) {
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            roleService.updateRole(role);
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("角色信息更新成功");
        return resultCode;
    }

    @RequestMapping("/role/delete")
    @ResponseBody
    public String deleteRole(Long id) {
        roleService.deleteRole(id);
        return "OK";
    }


    /**
     * 创建水信息
     */
    @RequestMapping("/role/create")
    @ResponseBody

    public ResultCode createRole(@Valid Role role, Errors errors) {
        logger.info("createRole");
        ResultCode resultCode = new ResultCode();
        if (errors.hasErrors()) {
            return ErrorUtils.getRsult(errors);
        }
        try {
            int rows = roleService.createRole(role);
            if (rows==-2147482646||rows > 0) {
                resultCode.setCode(0);
                resultCode.setMsg("角色信息创建成功");
                return resultCode;
            } else {
                resultCode.setCode(-1);
                resultCode.setMsg("角色信息创建失败");
                return resultCode;
            }
        } catch (DataAccessException e) {
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
    }


    public static void main(String[] args) throws NoSuchMethodException {

        Method method=  RoleController.class.getMethod("createRole",Role.class,Errors.class);
        System.out.println(method.getName());
    }

    //根据roleId查询role，并查询出可以添加的权限
    @RequestMapping("/role/findRoleByIdAndAllPermission")
    @ResponseBody
    public ResultCode findRoleByIdAndAllPermission(Long roleId) throws Exception {

        //根据roleId查询role
        Role role = roleService.getRoleById(roleId);
        //根据roleId查询可以添加的权限
        List<Permission> otherPermissions = roleService.findOtherPermissions(roleId);
        Map map=new HashMap<String,Object>();
        map.put("role",role);
        map.put("permissionList",otherPermissions);
        ResultCode  resultCode=new ResultCode();
        resultCode.setData(map);
        resultCode.setCode(0);
        return resultCode;
    }


    @RequestMapping("/role/addPermissionToRole.action")
    @ResponseBody
    public ResultCode addRoleToUser(Long roleId, Long permissionId) {
        ResultCode  resultCode=new ResultCode();
        try {
            roleService.addPermissionToRole(roleId,permissionId);
        }catch (Exception e){
            resultCode.setCode(-1);
            resultCode.setMsg(e.getMessage());
            return resultCode;
        }
        resultCode.setCode(0);
        resultCode.setMsg("角色权限添加成功");
        return resultCode;
    }
}
