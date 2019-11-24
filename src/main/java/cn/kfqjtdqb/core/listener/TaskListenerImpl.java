package cn.kfqjtdqb.core.listener;

import javax.servlet.http.HttpServletRequest;

import cn.kfqjtdqb.core.bean.UserInfo;
import cn.kfqjtdqb.core.service.UserInfoService;
import cn.kfqjtdqb.core.service.UserService;
import cn.kfqjtdqb.core.utils.DgbSecurityUserHelper;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.WebApplicationContextUtils;



@Repository
public class TaskListenerImpl implements TaskListener{


	@Autowired
	private UserInfoService userInfoService;

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {
		UserDetails userDetails=(UserDetails) DgbSecurityUserHelper.getCurrentPrincipal();

		UserInfo userInfo=userInfoService.findUser(userDetails.getUsername(),userDetails.getPassword());

/*
		userInfoService.

		//从IOC容器里面取出UserService
		UserService userService=applicationContext.getBean(UserService.class);
		//3查询领导信息
		User leaderUser = userService.queryUserById(mgr);
		//4,设置办理人
		delegateTask.setAssignee(leaderUser.getName());*/
	}

}
