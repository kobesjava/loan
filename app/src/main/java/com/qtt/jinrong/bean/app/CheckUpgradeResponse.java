package com.qtt.jinrong.bean.app;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/17.
 */
public class CheckUpgradeResponse extends Response {

    private UpgradeModel data;

    public UpgradeModel getData() {
        return data;
    }

    public void setData(UpgradeModel data) {
        this.data = data;
    }
}
