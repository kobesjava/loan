package com.qtt.jinrong.bean.credit;

import com.qtt.jinrong.bean.Response;

import java.util.List;

/**
 * Created by yanxin on 16/3/26.
 */
public class CreditApplyListResponse extends Response {

    List<CreditApplyModel> data;

    public List<CreditApplyModel> getData() {
        return data;
    }

    public void setData(List<CreditApplyModel> data) {
        this.data = data;
    }
}
