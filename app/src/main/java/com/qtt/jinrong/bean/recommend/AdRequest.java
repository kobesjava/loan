package com.qtt.jinrong.bean.recommend;

import com.qtt.jinrong.bean.IRequest;

import java.util.Map;

import lombok.Data;

/**
 * Created by yanxin on 16/2/23.
 */
@Data
public class AdRequest implements IRequest {

    private String version = "1.0";
    private long timestamp = 1418465127261l;
    private String appId = "leshifu";
    private String deviceId = "99000549765866";
    private String deviceType = "iOS";
    private String method = "downAdvert";
    private String token = "Klzt03rdn6snk1vcnfD3asp9TjanPLU1";

    public String position = "homepage";

    private String checkCode = "1";


    @Override
    public Map<String, Object> getParams() {
        return null;
    }
}