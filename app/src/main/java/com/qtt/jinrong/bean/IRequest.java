package com.qtt.jinrong.bean;

import java.util.Map;

/**
 * Created by blue on 15/8/6.
 * 请求接口有些需要实现的东西
 */
public interface IRequest {

    /**
     * 接口请求参数封装成map
     * @return
     */
    Map<String, Object> getParams();

}
