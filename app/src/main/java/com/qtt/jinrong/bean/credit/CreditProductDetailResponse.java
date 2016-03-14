package com.qtt.jinrong.bean.credit;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/8.
 */
public class CreditProductDetailResponse extends Response {

    private CreditDetailModel data;

    public CreditDetailModel getData() {
        return data;
    }

    public void setData(CreditDetailModel data) {
        this.data = data;
    }
}
