package com.qtt.jinrong.bean.user;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/7.
 */
public class UserLoginPwdRequest implements IRequest {

    private String cell;
    private String pwd;

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String,Object> map = new HashMap<>();
        map.put("cell",cell);
        map.put("pwd",pwd);
        return map;
    }
}
