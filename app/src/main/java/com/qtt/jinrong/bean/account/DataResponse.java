package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.Response;

import java.util.List;

/**
 * Created by yanxin on 16/4/16.
 */
public class DataResponse extends Response{

    private List<DataModel> data;

    public List<DataModel> getData() {
        return data;
    }

    public void setData(List<DataModel> data) {
        this.data = data;
    }
}
