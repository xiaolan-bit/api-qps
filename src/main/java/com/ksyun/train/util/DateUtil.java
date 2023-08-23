package com.ksyun.train.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final String defaultPattern = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern(defaultPattern);

    public static String format(LocalDateTime localDateTime) {
        return DTF.format(localDateTime);
    }

    public static LocalDateTime parse(String value) {
        return LocalDateTime.parse(value, DTF);
    }
}
