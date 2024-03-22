package com.shucan.api.entity.geo.poi;

/**
 * POI范围搜索
 * @author: ningkun
 * @date: 2024-03-22 10:33
 */
public class PoiRange extends Poi{

    /**
     * 搜索范围，可设置搜索半径，取值范围1-50，单位：公里。默认5公里。
     */
    private String radius;

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }
}
