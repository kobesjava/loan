package com.qtt.jinrong.bean.pay;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/4/13.
 */
public class PayResultResponse extends Response {

    private PayResultModel data;

    public PayResultModel getData() {
        return data;
    }

    public void setData(PayResultModel data) {
        this.data = data;
    }
}
