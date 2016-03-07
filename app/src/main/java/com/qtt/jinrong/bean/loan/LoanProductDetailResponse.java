package com.qtt.jinrong.bean.loan;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/8.
 */
public class LoanProductDetailResponse extends Response {

    private LoanProductDetail data;

    public LoanProductDetail getData() {
        return data;
    }

    public void setData(LoanProductDetail data) {
        this.data = data;
    }
}
