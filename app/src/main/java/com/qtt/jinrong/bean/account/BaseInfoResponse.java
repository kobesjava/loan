package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/8.
 */
public class BaseInfoResponse extends Response {

    private BaseInfoModel data;

    public BaseInfoModel getData() {
        return data;
    }

    public void setData(BaseInfoModel data) {
        this.data = data;
    }
}
