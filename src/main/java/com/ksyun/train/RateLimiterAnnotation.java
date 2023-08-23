package com.ksyun.train;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiterAnnotation {
    long ttl() default 10L; // 时间窗口大小，默认为10秒
    String[] users() default {"73404688","73404689"}; // 允许访问的用户列表
}


