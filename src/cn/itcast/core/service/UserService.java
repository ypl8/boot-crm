package cn.itcast.core.service;

import cn.itcast.core.bean.User;

/**
 * 用户Service层接口
 */
public interface UserService {
    User findUser(String usercode, String password);
}
