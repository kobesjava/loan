package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/8.
 */
public class IdentityResponse extends Response {

    private IdentityModel data;

    public IdentityModel getData() {
        return data;
    }

    public void setData(IdentityModel data) {
        this.data = data;
    }
}
