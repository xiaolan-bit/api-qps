package com.ksyun.train.controller;


import com.ksyun.train.RateLimiterAnnotation;
//import com.ksyun.train.aspect.RateLimiterAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimiterTestController {

    @RateLimiterAnnotation
    @RequestMapping("/test/instance/query")
    public String describeInstance(@RequestParam String userID) {
        return userID+"describe instance success";
    }

    @RateLimiterAnnotation
    @RequestMapping("/test/instance/create")
    public String createInstance(@RequestParam String userID) {
        return userID+"create instance success";
    }
}
