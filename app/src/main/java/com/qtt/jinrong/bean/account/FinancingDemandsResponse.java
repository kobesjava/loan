package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/8.
 */
public class FinancingDemandsResponse extends Response {

    private FinancingDemands data;

    public FinancingDemands getData() {
        return data;
    }

    public void setData(FinancingDemands data) {
        this.data = data;
    }
}
