package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.Role;
import cn.kfqjtdqb.core.bean.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserInfoDao {
    List<UserInfo> selectUserInfoList(UserInfo userInfo);

    Integer selectUserInfoListCount(UserInfo userInfo);

    UserInfo getUserInfoById(Long id);

    void updateUserInfo(UserInfo userInfo);

    void deleteUserInfo(Long id);

    int addUserInfo(UserInfo userInfo);

    UserInfo findUserByUserName(UserInfo userInfo);

    @Select("select * from users where userName=#{userName}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userName", column = "userName"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "cn.kfqjtdqb.core.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findByUsername(String userName) throws Exception;


    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userName", column = "userName"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "cn.kfqjtdqb.core.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findById(Long id) throws Exception;

    //根据用户的id 查询所有其他的角色
    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles(Long userId);


    @Insert("insert into users_role(userId,roleId) value(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") Long userId, @Param("roleId") Long roleId) throws Exception;

}