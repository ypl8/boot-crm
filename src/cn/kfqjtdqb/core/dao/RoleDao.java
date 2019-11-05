package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.Role;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {
    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    List<Role> findRoleByUserId(String userId) throws Exception;
}
