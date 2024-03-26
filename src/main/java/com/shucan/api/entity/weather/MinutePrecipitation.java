package com.shucan.api.entity.weather;

/**
 * 分钟级降水
 * @author: ningkun
 * @date: 2024-03-26 16:25
 */
public class MinutePrecipitation {

    /**
     * 需要查询地区的LocationID或以英文逗号分隔的经度,纬度坐标（十进制，最多支持小数点后两位），LocationID可通过GeoAPI获取。
     * 例如 location=101010100 或 location=116.41,39.92
     */
    private String location;

    /**
     * 多语言设置
     */
    private String lang;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
