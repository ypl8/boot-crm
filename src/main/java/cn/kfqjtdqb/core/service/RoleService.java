package cn.kfqjtdqb.core.service;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.Permission;
import cn.kfqjtdqb.core.bean.Role;

import java.util.List;

public interface RoleService {

    // 查询客户列表
    Page<Role> findRoleList(Integer page, Integer rows, Long  id,Long userId);

    Role getRoleById(Long id) throws  Exception;

    void updateRole(Role userInfo);

    void deleteRole(Long id);

    int createRole(Role userInfo);

    List<Permission> findOtherPermissions(Long roleId);

    void addPermissionToRole(Long roleId, Long permissionId) throws Exception;
}
