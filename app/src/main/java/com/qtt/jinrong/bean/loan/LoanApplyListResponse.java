package com.qtt.jinrong.bean.loan;

import com.qtt.jinrong.bean.Response;

import java.util.List;

/**
 * Created by yanxin on 16/2/24.
 */
public class LoanApplyListResponse extends Response {

    private List<LoanApplyModel> data;

    public List<LoanApplyModel> getData() {
        return data;
    }

    public void setData(List<LoanApplyModel> data) {
        this.data = data;
    }
}
