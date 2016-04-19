package com.qtt.jinrong.bean.loan;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/16.
 */
public class LoanApplyRequest implements IRequest {

    /**
     * 用户ID
     */
    public String userId;
    /**
     * 产品ID
     */
    public String productId;
    /**
     * 贷款总额
     */
    public Integer money;
    /**
     * 贷款期限
     */
    public Integer expires;

    /**
     * 身份
     */
    public Integer capacity;

    /**
     * 法人或股东
     */
    public Integer corporation;
    /**
     * 企业经营地
     */
    public Integer epBuss;
    /**
     * 经营年限
     */
    public Integer epPeriod;


    /**
     * 现单位工龄
     */
    public Integer currSeniority;
    /**
     * 收入发放方式
     */
    public Integer payWay;
    /**
     * 月均总收入
     */
    public Integer monthlyIncome;
    /**
     * 社保和公积金
     */
    public Integer socialSecurity;

    /**
     * 店铺类型
     */
    public Integer shopTpye;
    /**
     * 店铺名称
     */
    public String shopName;
    /**
     * 店铺地址
     */
    public String shopAddr;
    /**
     * 店铺用户名
     */
    public String shopUserName;
    /**
     * 店铺账号
     */
    public String shopAccount;
    /**
     * 店铺月均交易额
     */
    public Integer shopAvgMon;
    /**
     * 店铺近180天销售总金额
     */
    public Integer shop180Sales;
    /**
     * 店铺近90天成功支付订单
     */
    public Integer shop90Order;
    /**
     * 店铺月支付金额
     */
    public Integer shopMonPay;
    /**
     * 店铺开始经营时间
     */
    public String shopStartTime;
    /**
     * 店铺实际注册人
     */
    public String shopReg;
    /**
     * 借款人姓名
     */
    public String borrowerName;
    /**
     * 借款人移动电话
     */
    public String borrowerPhone;
    /**
     * 借款人备用电话
     */
    public String borrowerSecondPhone;
    /**
     * 借款人固话
     */
    public String borrowerTel;
    /**
     * 借款人身份证号码
     */
    public String borrowerIdNum;
    /**
     * 借款人单位地址
     */
    public String borrowerCompanyAddr;
    /**
     * 联系人姓名
     */
    public String contactName;
    /**
     * 联系人关系
     */
    public String contactRelation;
    /**
     * 联系人公司单位
     */
    public String contactCompany;
    /**
     * 联系人手机号码
     */
    public String contactPhone;

    /**
     * 年龄
     */
    public Integer age;
    /**
     * 信用情况
     */
    public Integer creInfo;
    /**
     * 逾期情况
     */
    public Integer overdue;
    /**
     * 信用卡总额度
     */
    public Integer creMoney;
    /**
     * 已使用额度
     */
    public Integer creUsed;
    /**
     * 房产信息
     */
    public Integer houseInfo;
    /**
     * 房产位置
     */
    public Integer district;
    /**
     * 房产抵押
     */
    public Integer mortgage;
    /**
     * 车产信息
     */
    public Integer car;
    /**
     * 牌照归属地
     */
    public Integer carBelong;

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("productId", productId);
        map.put("money", money);
        map.put("expires", expires);

        map.put("capacity", capacity);

        map.put("corporation", corporation);
        map.put("epBuss", epBuss);
        map.put("epPeriod", epPeriod);

        map.put("currSeniority", currSeniority);
        map.put("payWay", payWay);
        map.put("monthlyIncome", monthlyIncome);
        map.put("socialSecurity", socialSecurity);

        map.put("shopTpye",shopTpye);
        map.put("shopName",shopName);
        map.put("shopAddr",shopAddr);
        map.put("shopUserName",shopUserName);
        map.put("shopAccount",shopAccount);
        map.put("shopAvgMon",shopAvgMon);
        map.put("shop180Sales",shop180Sales);
        map.put("shop90Order",shop90Order);
        map.put("shopMonPay",shopMonPay);
        map.put("shopStartTime",shopStartTime);
        map.put("shopReg",shopReg);
        map.put("borrowerName",borrowerName);
        map.put("borrowerPhone",borrowerPhone);
        map.put("borrowerSecondPhone",borrowerSecondPhone);
        map.put("borrowerTel",borrowerTel);
        map.put("borrowerIdNum",borrowerIdNum);
        map.put("borrowerCompanyAddr",borrowerCompanyAddr);
        map.put("contactName",contactName);
        map.put("contactRelation",contactRelation);
        map.put("contactCompany",contactCompany);
        map.put("contactPhone",contactPhone);

        map.put("age", age);
        map.put("creInfo", creInfo);
        map.put("overdue", overdue);
        map.put("creMoney", creMoney);
        map.put("creUsed", creUsed);
        map.put("houseInfo", houseInfo);
        map.put("district", district);
        map.put("mortgage", mortgage);
        map.put("car", car);
        map.put("carBelong", carBelong);

        return map;
    }
}
