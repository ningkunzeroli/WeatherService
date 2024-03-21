package com.shucan.api.util;

import com.shucan.api.entity.CityLookup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

/**
 * 天气工具类
 * 集成 和风天气开发服务API
 * @author: ningkun
 * @date: 2024-03-20 17:18
 */
public class WeatherUtil {

    private static final Logger logger  = LogManager.getLogger(WeatherUtil.class);

    /**
     * @param entity 入参
     */
    public static String getApiData(Object entity,String api) {
        //处理URL
        String urlString  = HttpUtil.handleUrl(entity,api);
        try {
            HttpURLConnection connection = HttpUtil.handleApiRequest(urlString);
            // 读取响应
            if (connection != null) {
                //返回数据是JSON格式并进行了Gzip压缩。
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream()), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String inputLine;
                    while ((inputLine = reader.readLine()) != null) {
                        response.append(inputLine);
                    }
                    // 输出响应内容
                    return response.toString();
                }
            }else{
                // 连接为空，抛出异常
                throw new IOException("Connection is null, failed to establish connection.");
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }



}
