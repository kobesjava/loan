package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/8.
 */
public class HousePropertyResponse extends Response {

    private HousePropertyModel data;

    public HousePropertyModel getData() {
        return data;
    }

    public void setData(HousePropertyModel data) {
        this.data = data;
    }
}
