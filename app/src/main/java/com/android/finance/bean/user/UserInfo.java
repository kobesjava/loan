package com.android.finance.bean.user;

import com.android.finance.bean.Response;

/**
 * @author yanxin
 */
public class UserInfo extends Response {

    private String id;

    private String name;

    private String mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
