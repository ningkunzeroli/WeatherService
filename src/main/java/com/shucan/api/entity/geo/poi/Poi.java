package com.shucan.api.entity.geo.poi;

import com.shucan.api.entity.geo.Common;

/**
 * POI
 * @author: ningkun
 * @date: 2024-03-22 10:23
 */
public class Poi extends Common {

    /**
     * 需要查询地区的名称，支持文字、以英文逗号分隔的经度,纬度坐标（十进制，最多支持小数点后两位）、LocationID或Adcode（仅限中国城市）。
     * 例如 location=北京 或 location=116.41,39.92
     */
    private String location;

    /**
     * type(必选)POI类型，可选择搜索某一类型的POI
     * scenic 景点
     * CSTA 潮流站点
     * TSTA 潮汐站点
     */
    private String type;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
