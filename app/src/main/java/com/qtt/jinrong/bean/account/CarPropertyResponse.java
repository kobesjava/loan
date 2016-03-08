package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/8.
 */
public class CarPropertyResponse extends Response {

    private CarPropertyModel data;

    public CarPropertyModel getData() {
        return data;
    }

    public void setData(CarPropertyModel data) {
        this.data = data;
    }
}
