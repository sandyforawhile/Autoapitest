package com.sandy.utils;

import com.alibaba.fastjson.JSON;
import java.util.Map;


/**
 * @ClassName: JsonUtil
 * @Author sandy.n.hao
 * @Date: 2019-03-12
 * @Version v1.0.0
 * @Description: //TODO
 */

public class JsonUtil {

    public static Map<String, Object> json2Map(String jsonstr) {

        Map<String, Object> map = (Map) JSON.parse(jsonstr);
         return map;

    }

    public static String mapToJsonString(Map<String, Object> data) {
        return JSON.toJSONString(data);
    }

}
