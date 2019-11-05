package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.Role;
import cn.kfqjtdqb.core.bean.UserInfo;
import cn.kfqjtdqb.core.dao.BaseDictDao;
import cn.kfqjtdqb.core.dao.UserInfoDao;
import cn.kfqjtdqb.core.service.UserInfoService;
import cn.kfqjtdqb.core.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    // 定义dao属性
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Page<UserInfo> findUserInfoList(Integer page, Integer rows, String userName, String status) {
        UserInfo userInfo = new UserInfo();
        //判断客户名称(公司名称)
        if (StringUtils.isNotBlank(userName)) {
            userInfo.setUserName(userName);
        }
        if (StringUtils.isNotBlank(status)) {
            userInfo.setStatus(status);
        }
        //当前页
        userInfo.setStart((page - 1) * rows);
        //每页数
        userInfo.setRows(rows);
        //查询客户列表
        List<UserInfo> customers = userInfoDao.selectUserInfoList(userInfo);
        //查询客户列表总记录数
        Integer count = userInfoDao.selectUserInfoListCount(userInfo);
        //创建Page返回对象
        Page<UserInfo> result = new Page<>();
        result.setPage(page);
        result.setRows(customers);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public UserInfo getUserInfoById(Long id) {
        UserInfo caser = userInfoDao.getUserInfoById(id);
        return caser;
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoDao.updateUserInfo(userInfo);
    }

    @Override
    public void deleteUserInfo(Long id) {
        userInfoDao.deleteUserInfo(id);
    }


    @Override
    public int createUserInfo(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userInfoDao.addUserInfo(userInfo);
    }

    @Override
    public UserInfo findUser(String userName, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
        userInfo.setStatus("24");
        userInfo = userInfoDao.findUserByUserName(userInfo);
        return userInfo;
    }

    /*@Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserDetails  userDetails=new UserDetails() ;
        return null;
    }*/



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userInfoDao.findByUsername(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean  flag="23".equals(userInfo.getStatus());
        //处理自己的用户对象封装成UserDetails
        //  User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUserName(), "{noop}" + userInfo.getPassword(), flag , true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }
}
