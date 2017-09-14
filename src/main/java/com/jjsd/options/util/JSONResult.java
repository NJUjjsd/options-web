package com.jjsd.options.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by john on 2017/9/14.
 */
public class JSONResult {
    /**
     *
     * @param status 0为正确，其他均为错误
     * @param message 正确或错误的信息
     * @param result
     * @return
     */
    public static String fillResultString(Integer status, String message, Object result){
        JSONObject jsonObject = new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", result);
        }};
        return jsonObject.toString();
    }
}
