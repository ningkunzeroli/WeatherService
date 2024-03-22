package com.shucan.api.enums;

/**
 * POI类型枚举
 * @author: ningkun
 * @date: 2024-03-22 10:40
 */
public enum PoiTypeEnum {

    /**
     * POI类型，可选择搜索某一类型的POI
     */
    SCENIC("scenic","景点"),
    CSTA("CSTA","潮流站点"),
    TSTA("TSTA","潮汐站点");

    /**
     * 枚举值
     */
    private final String value;

    /**
     * 描述
     */
    private final String desc;

    PoiTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
