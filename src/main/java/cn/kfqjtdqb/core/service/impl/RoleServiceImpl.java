package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.Permission;
import cn.kfqjtdqb.core.bean.Role;
import cn.kfqjtdqb.core.bean.UserInfo;
import cn.kfqjtdqb.core.dao.RoleDao;
import cn.kfqjtdqb.core.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private RoleDao  roleDao;
    @Override
    public Page<Role> findRoleList(Integer page, Integer rows, Long id,Long userId) {
        Role role = new Role();
        //判断客户名称(公司名称)
        if (id!=null) {
            role.setId(id);
        }
        if(userId!=null){
            role.setUserId(userId);
        }
        //当前页
        role.setStart((page - 1) * rows);
        //每页数
        role.setRows(rows);
        //查询客户列表
        List<Role> roles = roleDao.selectRoleList(role);
        //查询客户列表总记录数
        Integer count = roleDao.selectRoleListCount(role);
        //创建Page返回对象
        Page<Role> result = new Page<>();
        result.setPage(page);
        result.setRows(roles);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleDao.deleteRole(id);
    }

    @Override
    public int createRole(Role role) {
        return  roleDao.addRole(role);
    }

    @Override
    public List<Permission> findOtherPermissions(Long roleId) {
        return  roleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(Long roleId, Long permissionId) throws Exception {
        roleDao.addPermissionToRole(roleId,permissionId);
    }
}
