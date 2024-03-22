package com.shucan.api.entity.geo.poi;

/**
 * POI搜索
 * @author: ningkun
 * @date: 2024-03-22 10:27
 */
public class PoiLookup extends Poi{

    /**
     * 选择POI所在城市，可设定只搜索在特定城市内的POI信息。城市名称可以是文字或城市的LocationID。默认不限制特定城市。
     * 城市名称为精准匹配，建议使用LocaitonID，如文字无法匹配，则数据返回为空。
     */
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
