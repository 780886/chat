package com.wgq.chat.common.json;

import com.alibaba.fastjson.JSON;
import com.wgq.chat.common.constant.Constant;
import com.wgq.chat.protocol.POJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FastJsonExtensionJsonImpl implements Json{


    private static Logger logger =  LoggerFactory.getLogger(FastJsonExtensionJsonImpl.class);


    @Override
    public String toString(POJO model) {
        return JSON.toJSONString(model);
    }

    @Override
    public String toString(Map<String, Object> map) {
        if(map == null || map.size() == 0){
            return Constant.NULL_JSON;
        }
        return JSON.toJSONString(map);
    }

    @Override
    public <T> String toString(Collection<T> models) {
        return JSON.toJSONString(models);
    }

    /**
     * 解析json至实体类
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> T parse(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    @Override
    public Map<String, Object> parse(String json) {
        return JSON.parseObject(json);
    }

    @Override
    public <T> List<T> parseList(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }
}
