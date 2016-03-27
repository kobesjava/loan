package com.qtt.jinrong.bean.app;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/17.
 */
public class CheckUpgradeRequest implements IRequest {

    private String versionNum;

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("versionNum",versionNum);
        return map;
    }
}
