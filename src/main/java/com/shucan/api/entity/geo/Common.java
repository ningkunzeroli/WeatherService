package com.shucan.api.entity.geo;

/**
 * 地理通用
 * @author: ningkun
 * @date: 2024-03-22 09:20
 */
public class Common {

    /**
     * 返回结果的数量，取值范围1-20，默认返回10个结果
     */
    private String number;

    /**
     * 多语言设置
     */
    private String lang;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
