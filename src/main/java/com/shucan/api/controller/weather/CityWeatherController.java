package com.shucan.api.controller.weather;

import com.shucan.api.constant.ApiConstant;
import com.shucan.api.entity.weather.CityWeather;
import com.shucan.api.entity.weather.MinutePrecipitation;
import com.shucan.api.util.WeatherUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 天气
 * @author: ningkun
 * @date: 2024-03-26 15:33
 */
@RestController
@RequestMapping("cityWeather")
public class CityWeatherController {

    /**
     * 获取中国3000+市县区和海外20万个城市实时天气、未来3-30天、未来24-168小时逐小时数据，包括实时温度、体感温度、风力风向、相对湿度、大气压强、降水量、能见度、露点温度、云量等。
     * 注意：实况数据均为近实时数据，相比真实的物理世界有5-20分钟的延迟，请根据实况数据中的obsTime确定数据对应的准确时间。
     * 请求URL GET https://api.qweather.com/v7/weather/xx?{查询参数}
     * @param cityWeather 城市天气
     * @return
     */
    @PostMapping("/query")
    public String getCityWeatherData(@RequestBody CityWeather cityWeather){

        if(StringUtils.isBlank(cityWeather.getLocation())){
            return "location不能为空";
        }

        if(StringUtils.isBlank(cityWeather.getType())){
            return "type不能为空";
        }
        //处理请求实际path
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiConstant.WEATHER);
        builder.path(cityWeather.getType());

        //将不用的参数移除
        cityWeather.setType(null);

        return WeatherUtil.getApiData(cityWeather, builder.toUriString());

    }

    @PostMapping("/minutely")
    public String getMinutePrecipitationData(@RequestBody MinutePrecipitation minutePrecipitation){

        if(StringUtils.isBlank(minutePrecipitation.getLocation())){
            return "location不能为空";
        }

        return WeatherUtil.getApiData(minutePrecipitation, ApiConstant.MINUTE_PRECIPITATION);

    }
}
