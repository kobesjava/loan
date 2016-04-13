package com.qtt.jinrong.bean.pay;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/4/13.
 */
public class PaySignResponse extends Response {

    private PayModel data;

    public PayModel getData() {
        return data;
    }

    public void setData(PayModel data) {
        this.data = data;
    }
}
