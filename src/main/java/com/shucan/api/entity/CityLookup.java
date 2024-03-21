package com.shucan.api.entity;

import lombok.Data;

/**
 * @author: ningkun
 * @date: 2024-03-21 11:04
 */
@Data
public class CityLookup {

    /**
     * 需要查询地区的名称
     * 支持文字、以英文逗号分隔的经度,纬度坐标（十进制，最多支持小数点后两位）、LocationID或Adcode（仅限中国城市）
     * 例如 location=北京 或 location=116.41,39.92
     *
     * 1、模糊搜索，当location传递的为文字时，支持模糊搜索，即用户可以只输入城市名称一部分进行搜索，最少一个汉字或2个字符，结果将按照相关性和Rank值进行排列，便于开发或用户进行选择他们需要查看哪个城市的天气。例如location=bei，将返回与bei相关性最强的若干结果，包括黎巴嫩的贝鲁特和中国的北京市
     * 2、重名，当location传递的为文字时，可能会出现重名的城市，例如陕西省西安市、吉林省辽源市下辖的西安区和黑龙江省牡丹江市下辖的西安区，此时会根据Rank值排序返回所有结果。在这种情况下，可以通过adm参数的方式进一步确定需要查询的城市或地区，例如location=西安&adm=黑龙江
     */
    private String location;

    /**
     * 城市的上级行政区划，可设定只在某个行政区划范围内进行搜索，用于排除重名城市或对结果进行过滤
     * 例如 adm=beijing
     * 如请求参数为location=chaoyang&adm=beijing时，返回的结果只包括北京市的朝阳区，而不包括辽宁省的朝阳市
     *
     * 如请求参数仅为location=chaoyang时，返回的结果包括北京市的朝阳区、辽宁省的朝阳市以及长春市的朝阳区
     */
    private String adm;

    /**
     * 搜索范围，可设定只在某个国家或地区范围内进行搜索，国家和地区名称需使用ISO 3166 所定义的国家代码
     * 详情请见iso3166.csv文件
     * 如果不设置此参数，搜索范围将在所有城市。例如 range=cn
     */
    private String range;

    /**
     * 返回结果的数量，取值范围1-20，默认返回10个结果
     */
    private String number;

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

    public String getAdm() {
        return adm;
    }

    public void setAdm(String adm) {
        this.adm = adm;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

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
