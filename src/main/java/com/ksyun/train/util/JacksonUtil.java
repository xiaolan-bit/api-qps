package com.ksyun.train.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;

import java.util.ArrayList;
import java.util.List;

public class JacksonUtil {

    private static final JsonMapper jsonMapper = JsonMapper.buildNormalMapper();

    public static String toJsonStr(Object object) {
        return jsonMapper.toJsonStr(object);
    }

    public static <T> T toBean(String jsonStr, Class<T> tClass) {
        return jsonMapper.toBean(jsonStr, tClass);
    }

    public static <T> List<T> toBeanList(String jsonArrayStr, Class<T> tClass) {
        JavaType javaType = jsonMapper.constructParametricType(ArrayList.class, tClass);
        return jsonMapper.toBean(jsonArrayStr, javaType);
    }

    public static <T> T toBean(String jsonStr, TypeReference<T> typeReference) {
        return jsonMapper.toBean(jsonStr, typeReference);
    }
}