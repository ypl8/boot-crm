package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.core.bean.User;

/**
 * 用户Service层接口
 */
public interface UserService {
    User findUser(String usercode, String password);
}
