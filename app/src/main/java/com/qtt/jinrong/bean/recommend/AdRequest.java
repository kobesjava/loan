package com.qtt.jinrong.bean.recommend;

import com.qtt.jinrong.bean.IRequest;

import java.util.Map;

/**
 * Created by yanxin on 16/2/23.
 */
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @Override
    public Map<String, Object> getParams() {
        return null;
    }
}