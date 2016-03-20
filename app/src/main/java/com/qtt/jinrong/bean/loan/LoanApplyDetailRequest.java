package com.qtt.jinrong.bean.loan;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/16.
 */
public class LoanApplyDetailRequest implements IRequest{

    /** 用户ID*/
    private String userId;
    /** 产品ID*/
    private String id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("id",id);
        return map;
    }
}
