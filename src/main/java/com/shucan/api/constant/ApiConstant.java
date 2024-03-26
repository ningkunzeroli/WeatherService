package com.shucan.api.constant;

/**
 * api常量类
 * @author: ningkun
 * @date: 2024-03-22 10:01
 */
public interface ApiConstant {

    /**
     * 城市搜索
     */
    String CITY_LOOKUP = "https://geoapi.qweather.com/v2/city/lookup";

    /**
     * 热门城市查询
     */
    String CITY_TOP = "https://geoapi.qweather.com/v2/city/top";

    /**
     * POI搜素
     */
    String POI_LOOKUP = "https://geoapi.qweather.com/v2/poi/lookup";

    /**
     * POI范围搜索
     */
    String POI_RANGE = "https://geoapi.qweather.com/v2/poi/range";

    /**
     * 天气
     */
    String WEATHER = "https://devapi.qweather.com/v7/weather/";

    /**
     * 分钟级降水
     */
    String MINUTE_PRECIPITATION="https://devapi.qweather.com/v7/minutely/5m";
}
