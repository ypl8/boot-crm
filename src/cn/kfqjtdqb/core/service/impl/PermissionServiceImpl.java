package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.Permission;
import cn.kfqjtdqb.core.dao.PermissionDao;
import cn.kfqjtdqb.core.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao  permissionDao;
    @Override
    public Page<Permission> findPermissionList(Integer page, Integer rows, Long id,Long roleId) {
        Permission permission = new Permission();
        //判断客户名称(公司名称)
        if (id!=null) {
            permission.setId(id);
        }
        if(roleId!=null){
            permission.setRoleId(roleId);
        }
        //当前页
        permission.setStart((page - 1) * rows);
        //每页数
        permission.setRows(rows);
        //查询客户列表
        List<Permission> permissions = permissionDao.selectPermissionList(permission);
        //查询客户列表总记录数
        Integer count = permissionDao.selectPermissionListCount(permission);
        //创建Page返回对象
        Page<Permission> result = new Page<>();
        result.setPage(page);
        result.setRows(permissions);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionDao.getPermissionById(id);
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionDao.updatePermission(permission);
    }

    @Override
    public void deletePermission(Long id) {
        permissionDao.deletePermission(id);
    }

    @Override
    public int createPermission(Permission permission) {
        return  permissionDao.addPermission(permission);
    }
}
