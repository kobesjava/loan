package com.qtt.jinrong.bean.credit;

import com.qtt.jinrong.bean.Response;

import java.util.List;

/**
 * Created by yanxin on 16/2/24.
 */
public class CreditListResponse extends Response {

    private List<CreditModel> data;

    public List<CreditModel> getData() {
        return data;
    }

    public void setData(List<CreditModel> data) {
        this.data = data;
    }
}
