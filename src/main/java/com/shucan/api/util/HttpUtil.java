package com.shucan.api.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http工具类
 * @author: ningkun
 * @date: 2024-03-21 09:27
 */
public class HttpUtil {

    private static final Logger logger  = LogManager.getLogger(HttpUtil.class);

    /**
     * 处理API请求URL
     * @param urlString 地址
     * @return
     */
    public static HttpURLConnection handleApiRequest(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            // 设置请求头信息
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.connect();
            return connection;
        } catch (IOException e) {
            logger.error("处理API请求URL发生异常",e);
            return null;
        }
    }

    /**
     * 处理URL
     * @param entity 实体对象
     * @param api api
     * @return 请求路径
     */
    public static String handleUrl(Object entity,String api) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(api);
        Class<?> clazz = entity.getClass();
        try{
            //获取属性
            List<Field> fields = getAllFields(clazz);
            for (Field field : fields) {
                // 设置字段为可访问，即使是私有字段也可以访问
                field.setAccessible(true);
                Object value = field.get(entity);
                //添加请求参数
                appendQueryParam(builder, field.getName(), String.valueOf(value));
            }
            appendQueryParam(builder, "key","c50d8fc68ade4c6590267b3730c3e505");
        }catch (IllegalAccessException e){
            logger.error("处理URL发生异常",e);
            return null;
        }
        return builder.toUriString();

    }

    /**
     * 获取类的所有属性
     * @param clazz 类
     * @return
     */
    public static List<Field> getAllFields(Class<?> clazz) {

        // 获取当前类声明的所有字段
        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));

        // 如果存在父类，则递归获取父类的所有字段
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            fields.addAll(getAllFields(superClass));
        }

        return fields;
    }

    /**
     * 添加请求参数
     * @param builder URL构造
     * @param paramName 参数名
     * @param paramValue 参数值
     */
    private static void appendQueryParam(UriComponentsBuilder builder, String paramName, String paramValue) {
        if (StringUtils.isNotBlank(paramValue) && !"null".equals(paramValue)) {
            builder.queryParam(paramName, paramValue);
        }
    }
}
