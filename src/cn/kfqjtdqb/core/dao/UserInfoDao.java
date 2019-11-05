package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.UserInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserInfoDao {
    List<UserInfo> selectUserInfoList(UserInfo userInfo);

    Integer selectUserInfoListCount(UserInfo userInfo);

    UserInfo getUserInfoById(Long id);

    void updateUserInfo(UserInfo userInfo);

    void deleteUserInfo(Long id);

    int addUserInfo(UserInfo userInfo);

    UserInfo findUserByUserName(UserInfo userInfo);

    @Select("select * from users where username=#{username}")
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
}