package com.qtt.jinrong.bean.pay;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/4/13.
 */
public class PayResultRequest implements IRequest {

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("uid",uid);
        return map;
    }
}
