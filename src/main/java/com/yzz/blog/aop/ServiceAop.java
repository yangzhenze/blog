package com.yzz.blog.aop;

import com.yzz.blog.entity.User;
import com.yzz.blog.util.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zzy
 * @Date 2018/9/13 下午1:42
 */
public class ServiceAop {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAop.class);

    @Autowired
    private RedisUtil redisUtil;

    public Object execute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;

        Object [] params = proceedingJoinPoint.getArgs();
        System.out.println(params);
        result = proceedingJoinPoint.proceed();

        return result;
    }


}
