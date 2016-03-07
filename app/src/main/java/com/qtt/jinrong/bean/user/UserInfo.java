package com.qtt.jinrong.bean.user;

import com.qtt.jinrong.bean.Response;

/**
 * @author yanxin
 */
public class UserInfo {

    private String userId;

    private String username;

    private String cell;

    private int gender;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
