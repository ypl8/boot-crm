package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.Permission;
import cn.kfqjtdqb.core.bean.Role;

import cn.kfqjtdqb.core.bean.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    List<Role> selectRoleList(Role role);

    Integer selectRoleListCount(Role role);

    Role getRoleById(Long id);

    void updateRole(Role role);

    void deleteRole(Long id);

    int addRole(Role role);

    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "cn.kfqjtdqb.core.dao.PermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(Long userId) throws Exception;


    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(Long roleId);

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);


    @Select("select * from users where id  in (select userId from users_role where roleId=#{roleId})")
    List<UserInfo> findUser(Long roleId);
   /* @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);*/
}
