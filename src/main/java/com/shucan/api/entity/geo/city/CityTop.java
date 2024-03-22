package com.shucan.api.entity.geo.city;

import com.shucan.api.entity.geo.Common;

/**
 * 城市TOP
 * @author: ningkun
 * @date: 2024-03-22 10:11
 */
public class CityTop extends Common {

    /**
     * 搜索范围，可设定只在某个国家或地区范围内进行搜索，国家和地区名称需使用ISO 3166 所定义的国家代码
     * 详情请见LocationList下iso3166.csv文件
     * 如果不设置此参数，搜索范围将在所有城市。例如 range=cn
     */
    private String range;

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
