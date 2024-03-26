package com.shucan.api.entity.weather;

/**
 * 天气实体类
 * @author: ningkun
 * @date: 2024-03-26 15:37
 */
public class CityWeather {

    /**
     * 需要查询地区的LocationID或以英文逗号分隔的经度,纬度坐标（十进制，最多支持小数点后两位），LocationID可通过GeoAPI获取。
     * 例如 location=101010100 或 location=116.41,39.92
     */
    private String location;

    /**
     * 数据单位设置，可选值包括unit=m（公制单位，默认）和unit=i（英制单位）。
     */
    private String unit;

    /**
     * 多语言设置
     */
    private String lang;

    /**
     * 查询类型，用于URL路径拼接，不是实际API参数
     * now 实时天气
     * 3d  3天预报
     * 7d  7天预报
     * 10d 10天预报
     * 15d 15天预报
     * 30d 30预报
     * 24h 逐小时预报（未来24小时）
     * 72h 逐小时预报（未来72小时）
     * 168h 逐小时预报（未来168小时）
     */
    private String type;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
