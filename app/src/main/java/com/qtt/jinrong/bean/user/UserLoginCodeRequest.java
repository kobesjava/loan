package com.qtt.jinrong.bean.user;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/7.
 */
public class UserLoginCodeRequest implements IRequest {

    private String cell;
    private String verifyCode;

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String,Object> map = new HashMap<>();
        map.put("cell",cell);
        map.put("verifyCode",verifyCode);
        return map;
    }
}
