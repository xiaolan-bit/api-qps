package com.ksyun.train.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.apache.commons.lang3.StringUtils;
//import org.springframework.util.StringUtils;
//import org.springframework.util.StringUtils;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;


import java.io.IOException;
import java.time.LocalDateTime;

public class JsonMapper {

    private ObjectMapper mapper;

    public JsonMapper(JsonInclude.Include include) {
        this.mapper = new ObjectMapper();
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(include);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerialize.LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeSerialize.LocalDateTimeJsonDeserialize());
        mapper.registerModule(module);
    }

    public static JsonMapper buildNormalMapper() {
        return new JsonMapper(JsonInclude.Include.ALWAYS);
    }

    public String toJsonStr(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {

        }
        return StringUtils.EMPTY;
    }

    public <T> T toBean(String jsonStr, Class<T> clz) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        try {
            return mapper.readValue(jsonStr, clz);
        } catch (Exception e) {
        }
        return null;
    }

    public <T> T toBean(String jsonStr, JavaType javaType) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        try {
            return mapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T toBean(String jsonStr, TypeReference<T> tTypeReference) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        try {
            return mapper.readValue(jsonStr, tTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
        return mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
    }
}