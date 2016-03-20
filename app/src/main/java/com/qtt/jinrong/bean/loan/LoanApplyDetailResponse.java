package com.qtt.jinrong.bean.loan;

import com.qtt.jinrong.bean.Response;

import java.util.List;

/**
 * Created by yanxin on 16/3/20.
 */
public class LoanApplyDetailResponse extends Response {

    private List<LoanApplyModel> data;

    public List<LoanApplyModel> getData() {
        return data;
    }

    public void setData(List<LoanApplyModel> data) {
        this.data = data;
    }
}
