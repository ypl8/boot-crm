package cn.kfqjtdqb.core.service;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.Permission;

public interface PermissionService {

    // 查询客户列表
    Page<Permission> findPermissionList(Integer page, Integer rows, Long id,Long roleId);

    Permission getPermissionById(Long id);

    void updatePermission(Permission userInfo);

    void deletePermission(Long id);

    int createPermission(Permission userInfo);
}
