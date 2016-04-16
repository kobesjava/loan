package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/4/16.
 */
public class DataUploadRequest implements IRequest{

    private String userId;
    private Integer imgType;
    private byte[] fileByte;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("imgType",imgType);
        map.put("fileByte",fileByte);
        return map;
    }
}
