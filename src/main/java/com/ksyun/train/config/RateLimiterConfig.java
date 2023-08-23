package com.ksyun.train.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class RateLimiterConfig {

    @Autowired
    private ResourceLoader resourceLoader;

    private Map<String, Map<String, Integer>> rateLimiterConfig;

    @PostConstruct
    public void initialize() {
        rateLimiterConfig = new HashMap<>();
        try {
            Resource resource = resourceLoader.getResource("classpath:ratelimiter.properties");
            InputStream inputStream = resource.getInputStream();
            Properties properties = new Properties();
            properties.load(inputStream);

            for (String user : properties.stringPropertyNames()) {
                String config = properties.getProperty(user);
                Map<String, Integer> methodLimits = parseConfig(config);
                rateLimiterConfig.put(user, methodLimits);
            }
        } catch (IOException e) {
            // Handle file loading exception
        }
    }

    private Map<String, Integer> parseConfig(String config) {
        Map<String, Integer> methodLimits = new HashMap<>();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(config);
        JsonObject jsonObject = element.getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String methodName = entry.getKey();
            int limit = entry.getValue().getAsInt();
            methodLimits.put(methodName, limit);
        }

        return methodLimits;
    }

    public Map<String, Integer> getMethodLimits(String user) {
        return rateLimiterConfig.get(user);
    }
}
