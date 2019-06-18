package com.qst.ssm.controller;

import com.qst.ssm.domain.SysLog;
import com.qst.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAOP {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;
    private Date visitTime;
    private Class clazz;
    private Method method;

    //用来获取开始时间，执行的是哪个类的哪个方法
    @Before("execution(* com.qst.ssm.controller.*.*(..))")
 public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();// 获取访问的方法的参数
        if (args == null || args.length == 0) {// 无参数
            method = clazz.getMethod(methodName); // 只能获取无参数方法
        } else {
        // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method=clazz.getMethod(methodName,classArgs);
        }
    }

    @After("execution(* com.qst.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {

    long time = new Date().getTime() - visitTime.getTime();

        String url="";
        if(clazz!=null&&method!=null&&clazz!=LogAOP.class) {
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }
            }
        }

        String ip = request.getRemoteAddr();



        // 可以通过securityContext获取，也可以从request.getSession中获取
        SecurityContext context = SecurityContextHolder.getContext();

        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();


        //将数据封装到Syslog对象中
        SysLog syslog = new SysLog();
        syslog.setUsername(username);
        syslog.setExecutionTime(time);
        syslog.setUrl(url);
        syslog.setVisitTime(visitTime);
        syslog.setIp(ip);
        syslog.setMethod(clazz.getName()+method.getName());

        sysLogService.Save(syslog);
    }
}
