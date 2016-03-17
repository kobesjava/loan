package com.qtt.jinrong.bean.loan;

import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.config.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/17.
 */
public class LoanApplyListRequest implements IRequest{

    private String userId;

    private Integer pageSize = Constants.PAGE_SIZE;

    private Integer pageNo = 1;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("pageSize",pageSize);
        map.put("pageNo",pageNo);
        return map;
    }
}
