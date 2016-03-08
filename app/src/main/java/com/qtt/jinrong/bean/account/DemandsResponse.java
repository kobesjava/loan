package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/8.
 */
public class DemandsResponse extends Response {

    private DemandModel data;

    public DemandModel getData() {
        return data;
    }

    public void setData(DemandModel data) {
        this.data = data;
    }
}
