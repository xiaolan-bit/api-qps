package com.ksyun.train;

import java.time.LocalDateTime;

public class LogTest {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogTest.class);

    public static void println() {
        log.info("系统启动了, time={}", LocalDateTime.now());
    }
}