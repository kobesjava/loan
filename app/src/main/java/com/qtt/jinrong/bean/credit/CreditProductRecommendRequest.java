package com.qtt.jinrong.bean.credit;

import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.config.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/7.
 */
public class CreditProductRecommendRequest implements IRequest {

    /** 贷款金额*/
    private Integer quota;
    /** 贷款期限*/
    private Integer limi;
    private Integer pageSize = Constants.RECOMMEND_SIZE;
    private Integer pageNo = 1;

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public Integer getLimi() {
        return limi;
    }

    public void setLimi(Integer limi) {
        this.limi = limi;
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
        map.put("quota",quota);
        map.put("limi",limi);
        map.put("pageSize",pageSize);
        map.put("pageNo",pageNo);
        return map;
    }

}
