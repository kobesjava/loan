package com.qtt.jinrong.bean.user;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/7.
 */
public class UserInfoResponse extends Response {

    private UserInfo data;

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }
}
