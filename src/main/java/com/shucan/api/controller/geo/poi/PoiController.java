package com.shucan.api.controller.geo.poi;

import com.shucan.api.constant.ApiConstant;
import com.shucan.api.entity.geo.poi.PoiLookup;
import com.shucan.api.entity.geo.poi.PoiRange;
import com.shucan.api.enums.PoiTypeEnum;
import com.shucan.api.util.WeatherUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * POI
 * @author: ningkun
 * @date: 2024-03-22 10:20
 */
@RestController
@RequestMapping("poi")
public class PoiController {

    /**
     * 使用关键字和坐标查询POI信息（景点、火车站、飞机场、港口等）
     * 请求URL GET https://geoapi.qweather.com/v2/poi/lookup?{查询参数}
     * @param poiLookup POI
     * @return
     */
    @PostMapping("/lookup")
    public String getPoiLookupData(@RequestBody PoiLookup poiLookup){

        if(StringUtils.isBlank(poiLookup.getLocation())){
            return "location不能为空";
        }
        String type = poiLookup.getType();
        if(StringUtils.isBlank(type)){
            return "type不能为空";
        }

        if(!PoiTypeEnum.SCENIC.getValue().equals(type) &&
                !PoiTypeEnum.CSTA.getValue().equals(type)&&
                !PoiTypeEnum.TSTA.getValue().equals(type)){
            return "type值无效";
        }

        return WeatherUtil.getApiData(poiLookup, ApiConstant.POI_LOOKUP);

    }

    /**
     * POI范围搜索
     * 请求URL GET https://geoapi.qweather.com/v2/poi/range
     *
     * location(必选)需要查询地区的以英文逗号分隔的经度,纬度坐标（十进制，最多支持小数点后两位）。例如 location=116.41,39.92
     * @param poiRange
     * @return
     */
    @PostMapping("/range")
    public String getPoiRangeData(@RequestBody PoiRange poiRange){

        if(StringUtils.isBlank(poiRange.getLocation())){
            return "location不能为空";
        }
        String type = poiRange.getType();
        if(StringUtils.isBlank(type)){
            return "type不能为空";
        }

        if(!PoiTypeEnum.SCENIC.getValue().equals(type) &&
                !PoiTypeEnum.CSTA.getValue().equals(type)&&
                !PoiTypeEnum.TSTA.getValue().equals(type)){
            return "type值无效";
        }

        return WeatherUtil.getApiData(poiRange, ApiConstant.POI_RANGE);

    }
}
