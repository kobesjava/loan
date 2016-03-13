package com.qtt.jinrong.bean.loan;

import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.config.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/7.
 */
public class LoanListRequest implements IRequest {

    /** 贷款金额*/
    private Integer quota;
    /** 贷款期限*/
    private Integer limi;
    /** 职业身份*/
    private Integer identity;
    /** 担保方式*/
    private Integer guaranteeWay;
    /** 还款方式*/
    private Integer repay;
    private Integer orderNo;
    private Integer pageSize = Constants.PAGE_SIZE;
    private Integer pageNo;

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getLimi() {
        return limi;
    }

    public void setLimi(Integer limi) {
        this.limi = limi;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Integer getGuaranteeWay() {
        return guaranteeWay;
    }

    public void setGuaranteeWay(Integer guaranteeWay) {
        this.guaranteeWay = guaranteeWay;
    }

    public Integer getRepay() {
        return repay;
    }

    public void setRepay(Integer repay) {
        this.repay = repay;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
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
        map.put("identity",identity);
        map.put("guaranteeWay",guaranteeWay);
        map.put("repay",repay);
        map.put("orderNo",orderNo);
        map.put("pageSize",pageSize);
        map.put("pageNo",pageNo);
        return map;
    }

}
