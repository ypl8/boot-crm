package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserInfoService extends UserDetailsService {

    // 查询客户列表
    Page<UserInfo> findUserInfoList(Integer page, Integer rows,
                            String userName,String status);

    UserInfo getUserInfoById(Long id);

    void updateUserInfo(UserInfo userInfo);

    void deleteUserInfo(Long id);

    int createUserInfo(UserInfo userInfo);

    UserInfo findUser(String userName, String password);
}
