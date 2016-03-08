package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/8.
 */
public class CreditPropertyResponse extends Response {

    private CreditPropertyModel data;

    public CreditPropertyModel getData() {
        return data;
    }

    public void setData(CreditPropertyModel data) {
        this.data = data;
    }
}
