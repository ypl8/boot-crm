package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.Permission;

import cn.kfqjtdqb.core.bean.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
     List<Permission> findPermissionByRoleId(Long id) throws Exception;

  /*  @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;*/

    List<Permission> selectPermissionList(Permission permission);

    Integer selectPermissionListCount(Permission permission);

    Permission getPermissionById(Long id);

    void updatePermission(Permission permission);

    void deletePermission(Long id);

    int addPermission(Permission role);

}
