package com.qtt.jinrong.bean.credit;

import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.config.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/26.
 */
public class CreditListRequest implements IRequest{

    private String creBank;
    private String creType;
    private String creClass;
    private Integer pageSize = Constants.PAGE_SIZE;
    private Integer pageNo;

    public String getCreBank() {
        return creBank;
    }

    public void setCreBank(String creBank) {
        this.creBank = creBank;
    }

    public String getCreType() {
        return creType;
    }

    public void setCreType(String creType) {
        this.creType = creType;
    }

    public String getCreClass() {
        return creClass;
    }

    public void setCreClass(String creClass) {
        this.creClass = creClass;
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
        map.put("creBank",creBank);
        map.put("creType",creType);
        map.put("creClass",creClass);
        map.put("pageSize",pageSize);
        map.put("pageNo",pageNo);
        return map;
    }
}
