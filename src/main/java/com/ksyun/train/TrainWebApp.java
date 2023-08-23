package com.ksyun.train;

import com.ksyun.train.util.JacksonUtil;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;
import java.util.UUID;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
@PropertySource("classpath:ratelimiter.properties")
@SpringBootApplication
public class TrainWebApp {

    public static void main(String[] args) {
        MDC.put("requestId", UUID.randomUUID().toString());
        //log.info("系统启动了, time={}", JacksonUtil.toJsonStr(LocalDateTime.now()));
        SpringApplication.run(TrainWebApp.class);
    }
}