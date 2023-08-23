package com.ksyun.train.aspect;

import com.ksyun.train.RateLimiterAnnotation;
import com.ksyun.train.config.RateLimiterConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Order(1)
public class RateLimiterAspect {

    @Autowired
    private RateLimiterConfig rateLimiterConfig;


    @Around("@annotation(rateLimiterAnnotation)")
    public Object applyRateLimit(ProceedingJoinPoint joinPoint, RateLimiterAnnotation rateLimiterAnnotation) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        // 获取当前用户信息，假设从请求中获取
        String currentUser = getCurrentUser();
        System.out.println(currentUser);

        // 根据当前用户和方法名获取访问频率配置
        Map<String, Integer> methodLimits = rateLimiterConfig.getMethodLimits(currentUser);
        if (methodLimits != null) {
            // 获取当前方法的限制次数
            Integer methodLimit = methodLimits.get(methodName);
            System.out.println(methodLimit);
            if (methodLimit != null) {
                // 检查当前用户是否超过限制次数
                if (isExceedingLimit(currentUser, methodName, methodLimit)) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "request too much");
                }
            }
        }

        // 执行被注解的方法
        return joinPoint.proceed(methodArgs);
    }

    // 检查用户是否超过访问限制
    private boolean isExceedingLimit(String user, String methodName, int limit) {
        // 根据用户和方法名，进行实际的访问频率检查
        // 可以使用缓存、数据库或其他存储来记录用户的访问次数
        // 这里简单返回了一个固定的值来模拟检查结果
        int currentCount = getCurrentAccessCount(user, methodName);
        System.out.println(currentCount);
        return currentCount >= limit;
    }

    // 获取当前用户
    private String getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String userID = request.getParameter("userID");
        return userID;
    }

    // 获取当前用户对特定方法的访问次数
    private int getCurrentAccessCount(String user, String methodName) {
        // 从缓存、数据库或其他存储中获取用户对方法的访问次数
        // 可以使用缓存、数据库或其他存储来记录用户的访问次数
        // 这里简单返回了一个固定的值来模拟访问次数
        return 9;
    }
}
