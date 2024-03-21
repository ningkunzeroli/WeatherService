package com.shucan.api.controller;

import com.shucan.api.entity.CityLookup;
import com.shucan.api.util.WeatherUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * 天气控制层
 * @author: shucan
 * @date: 2024-03-20 17:13
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    /**
     * 城市搜索API提供全球地理位位置、全球城市搜索服务，支持经纬度坐标反查、多语言、模糊搜索等功能。
     * 天气数据是基于地理位置的数据，因此获取天气之前需要先知道具体的位置信息。
     * 使用城市搜索，可获取到该城市的基本信息，包括城市的Location ID（你需要这个ID去查询天气），多语言名称、经纬度、时区、海拔、Rank值、归属上级行政区域、所在行政区域等。
     *
     * 另外，城市搜索也可以帮助你在你的APP中实现模糊搜索，用户只需要输入1-2个字即可获得结果。
     *
     * 请求URL GET https://geoapi.qweather.com/v2/city/lookup?{查询参数}
     * @param cityLookup 入参
     * @return
     */
    @PostMapping("/city/lookup")
    public String getCityLookupData(@RequestBody CityLookup cityLookup) {
        if(StringUtils.isBlank(cityLookup.getLocation())){
            return "location不能为空";
        }
        return WeatherUtil.getApiData(cityLookup,"https://geoapi.qweather.com/v2/city/lookup");
    }

    /**
     * 获取全球各国热门城市列表。
     * 请求URL GET https://geoapi.qweather.com/v2/city/top?{查询参数}
     * @param cityLookup 入参
     * @return
     */
    @PostMapping("/city/top")
    public String getCityTopData(@RequestBody CityLookup cityLookup) {
        return WeatherUtil.getApiData(cityLookup,"https://geoapi.qweather.com/v2/city/top");
    }
}
