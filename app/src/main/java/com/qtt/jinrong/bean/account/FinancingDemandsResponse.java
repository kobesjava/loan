package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/8.
 */
public class FinancingDemandsResponse extends Response {

    private FinancingDemandsModel data;

    public FinancingDemandsModel getData() {
        return data;
    }

    public void setData(FinancingDemandsModel data) {
        this.data = data;
    }
}
