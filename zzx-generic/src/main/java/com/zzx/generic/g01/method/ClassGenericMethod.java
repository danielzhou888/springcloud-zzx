package com.zzx.generic.g01.method;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2020-03-20
 */
public class ClassGenericMethod {

    /**
     * 将指定Object集合，转化为指定Class类型List<T>输出
     * @author        zhangqiang
     * @date          2020-03-20 01:14
     * @return
     */
    public <T> List<T> convert(Object obj, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        String jsonList = JSONArray.toJSONString(obj);
        return JSONObject.parseArray(jsonList, clazz);
    }
}
