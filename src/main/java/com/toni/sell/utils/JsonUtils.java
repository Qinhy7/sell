package com.toni.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/18 0018 8:46
 * @modified By：
 */
public class JsonUtils {

    /**
     * 转成json格式输出
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

}
