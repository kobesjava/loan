package com.qtt.jinrong.bean.recommend;

import com.qtt.jinrong.bean.Response;

import java.util.List;

/**
 * Created by yanxin on 16/2/23.
 */
public class AdResponse extends Response {

    private List<AdModel> data;

    public List<AdModel> getData() {
        return data;
    }

    public void setData(List<AdModel> data) {
        this.data = data;
    }
}
