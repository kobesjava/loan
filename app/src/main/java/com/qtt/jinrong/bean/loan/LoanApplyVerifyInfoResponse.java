package com.qtt.jinrong.bean.loan;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/20.
 */
public class LoanApplyVerifyInfoResponse extends Response {

    private LoanApplyVerifyInfoModel data;

    public LoanApplyVerifyInfoModel getData() {
        return data;
    }

    public void setData(LoanApplyVerifyInfoModel data) {
        this.data = data;
    }
}
