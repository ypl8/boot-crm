package cn.kfqjtdqb.core.web.controller;

import cn.kfqjtdqb.core.bean.SysLog;
import cn.kfqjtdqb.core.service.SysLogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;
    public final Log logger = LogFactory.getLog(LogAop.class);

    private Class clazz; //访问的类
    private Method method;//访问的方法

    //前置通知  主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* cn.kfqjtdqb.core.web.controller.*.edit*(..)) || execution(* cn.kfqjtdqb.core.web.controller.*.create*(..)) || execution(* cn.kfqjtdqb.core.web.controller.*.add*(..)) || execution(* cn.kfqjtdqb.core.web.controller.*.update*(..))||execution(* cn.kfqjtdqb.core.web.controller.*.delete*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        logger.info("doBefore");
        clazz = jp.getTarget().getClass(); //具体要访问的类
        String methodName = jp.getSignature().getName(); //获取访问的方法的名称
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method2 : methods) {
            if (methodName.equals(method2.getName())) {
                method = method2;
            }
        }

       /* //获取具体执行的方法Method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName); //只能获取无参数的方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }*/
    }

    //后置通知
    @After("execution(* cn.kfqjtdqb.core.web.controller.*.edit*(..)) || execution(* cn.kfqjtdqb.core.web.controller.*.create*(..)) || execution(* cn.kfqjtdqb.core.web.controller.*.add*(..)) || execution(* cn.kfqjtdqb.core.web.controller.*.update*(..))||execution(* cn.kfqjtdqb.core.web.controller.*.delete*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        String url = "";
        //获取url
        if (clazz != null && method != null && clazz != LogAop.class) {
            //1.获取类上的@RequestMapping("/orders")
            //2.获取方法上的@RequestMapping(xxx)
            RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
            if (methodAnnotation != null) {
                String[] methodValue = methodAnnotation.value();
                url = methodValue[0];
                //获取访问的ip
                String ip = request.getRemoteAddr();
                //获取当前操作的用户
                SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
                User user = (User) context.getAuthentication().getPrincipal();
                String username = user.getUsername();
                //将日志相关信息封装到SysLog对象
                SysLog sysLog = new SysLog();
                sysLog.setIp(ip);
                sysLog.setVisitTime(new Date());
                sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                sysLog.setUrl(url);
                sysLog.setUsername(username);
                //调用Service完成操作
                sysLogService.createSysLog(sysLog);
            }

        }

    }
}
