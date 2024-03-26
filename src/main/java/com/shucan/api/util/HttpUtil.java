package com.shucan.api.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * http工具类
 * @author: ningkun
 * @date: 2024-03-21 09:27
 */
@Configuration
public class HttpUtil {

    private static final Logger logger = LogManager.getLogger(HttpUtil.class);

    private static Environment env;

    @Autowired
    public void setEnvironment(Environment env) {
        HttpUtil.env = env;
    }

    /**
     * 获取公共 ID
     */
    public static String getPublicId() {
        return env.getProperty("weather.public-id");
    }

    /**
     * 获取天气密钥
     */
    public static String getWeatherKey() {
        return env.getProperty("weather.key");
    }

    /**
     * 处理 API 请求 URL
     */
    public static HttpURLConnection handleApiRequest(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.connect();
            return connection;
        } catch (IOException e) {
            logger.error("处理 API 请求 URL 发生异常", e);
            return null;
        }
    }

    /**
     * 处理 URL
     */
    public static String handleUrl(Object entity, String api) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(api);
        Class<?> clazz = entity.getClass();
        try {
            List<Field> fields = getAllFields(clazz);
            HashMap<String, String> params = new HashMap<>((int) (fields.size() / 0.75 + 1));
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(entity);
                appendQueryParam(builder, field.getName(), String.valueOf(value));
                params.put(field.getName(), String.valueOf(value));
            }
            //进行数字签名加密
            params.put("publicid", getPublicId());
            params.put("t", String.valueOf(System.currentTimeMillis() / 1000));
            appendQueryParam(builder, "sign", EncryptUtil.getSignature(params, getWeatherKey()));
            appendQueryParam(builder, "publicid", getPublicId());
            appendQueryParam(builder, "t", String.valueOf(System.currentTimeMillis() / 1000));
        } catch (IllegalAccessException e) {
            logger.error("处理 URL 发生异常", e);
            return null;
        } catch (Exception e) {
            logger.error("进行加密签名认证发生异常", e);
            return null;
        }
        return builder.toUriString();
    }

    /**
     * 获取类的所有属性
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            fields.addAll(getAllFields(superClass));
        }
        return fields;
    }

    /**
     * 添加请求参数
     */
    private static void appendQueryParam(UriComponentsBuilder builder, String paramName, String paramValue) {
        if (StringUtils.isNotBlank(paramValue) && !"null".equals(paramValue)) {
            builder.queryParam(paramName, paramValue);
        }
    }
}
