package com.qtt.jinrong.bean.credit;

import com.qtt.jinrong.bean.Response;

import java.util.List;

/**
 * Created by yanxin on 16/3/20.
 */
public class CreditApplyDetailResponse extends Response {

    private List<CreditApplyDetailModel> data;

    public List<CreditApplyDetailModel> getData() {
        return data;
    }

    public void setData(List<CreditApplyDetailModel> data) {
        this.data = data;
    }
}
