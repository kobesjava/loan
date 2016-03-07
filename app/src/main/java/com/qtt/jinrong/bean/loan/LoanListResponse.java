package com.qtt.jinrong.bean.loan;

import com.qtt.jinrong.bean.Response;

import java.util.List;

/**
 * Created by yanxin on 16/2/24.
 */
public class LoanListResponse extends Response {

    private List<LoanModel> data;

    public List<LoanModel> getData() {
        return data;
    }

    public void setData(List<LoanModel> data) {
        this.data = data;
    }
}
