package com.qtt.jinrong.bean.account;

/**
 * Created by yanxin on 16/3/8.
 */
public class IdentityModel {

    //身份
    public Integer capacity;
    //法人或股东
    public Integer corporation;
    //企业名称
    public String epName;
    //企业地址
    public String epAddr;
    //所属行业
    public Integer epIndustry;
    //企业规模（人）
    public Integer epScale;
    //企业经营地
    public Integer epBuss;
    //经营年限
    public Integer epPeriod;
    //注册资金
    public Integer epCapital;
    //主营业务
    public String epMain;
    //年营业额（万元）
    public Integer epAnnualTurnover;
    //年开票额
    public Integer epTicket;
    //年营业收入
    public Integer epAnnualRevenue;
    //月对公流水
    public Integer epContraryWater;
    //月对私流水
    public Integer epPrivateWater;
    //资产负债率
    public Integer epLeverage;
    //年净利润
    public Integer epNetProfit;
    //企业欠款情况
    public Integer epDebt;
    //欠款机构名称
    public String epDebtName;
    //欠款余额
    public Integer epDebtAmt;
    //月还款额（万元）
    public Integer epMonthRepay;
    //就职公司类型
    public Integer companyType;
    //就职公司名称
    public String companyName;
    //职位
    public Integer job;
    //现单位工龄
    public Integer currSeniority;
    //工作地（省）
    public String workProvince;
    //工作地（市）
    public String workCity;
    //工作详细地址
    public String workAddr;
    //收入发放方式
    public Integer payWay;
    //月打卡工资（元）
    public Integer wages;
    //月均总收入（元）
    public Integer monthlyIncome;
    //能否提供收入证明
    public Integer verifiableIncome;
    //社保和公积金
    public Integer socialSecurity;
    //公积金连续缴纳年限
    public Integer accuFundAge;
    //社保连续缴纳年限
    public Integer socialSecurityAge;
    //有无副业
    public Integer avocation;
    //副业内容
    public String avocationInfo;
    //副业月收入（元）
    public Integer avocationAmt;

    //电商
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
    public Integer shopStartTime;

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


    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCorporation() {
        return corporation;
    }

    public void setCorporation(Integer corporation) {
        this.corporation = corporation;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getEpAddr() {
        return epAddr;
    }

    public void setEpAddr(String epAddr) {
        this.epAddr = epAddr;
    }

    public Integer getEpIndustry() {
        return epIndustry;
    }

    public void setEpIndustry(Integer epIndustry) {
        this.epIndustry = epIndustry;
    }

    public Integer getEpScale() {
        return epScale;
    }

    public void setEpScale(Integer epScale) {
        this.epScale = epScale;
    }

    public Integer getEpBuss() {
        return epBuss;
    }

    public void setEpBuss(Integer epBuss) {
        this.epBuss = epBuss;
    }

    public Integer getEpPeriod() {
        return epPeriod;
    }

    public void setEpPeriod(Integer epPeriod) {
        this.epPeriod = epPeriod;
    }

    public Integer getEpCapital() {
        return epCapital;
    }

    public void setEpCapital(Integer epCapital) {
        this.epCapital = epCapital;
    }

    public String getEpMain() {
        return epMain;
    }

    public void setEpMain(String epMain) {
        this.epMain = epMain;
    }

    public Integer getEpAnnualTurnover() {
        return epAnnualTurnover;
    }

    public void setEpAnnualTurnover(Integer epAnnualTurnover) {
        this.epAnnualTurnover = epAnnualTurnover;
    }

    public Integer getEpTicket() {
        return epTicket;
    }

    public void setEpTicket(Integer epTicket) {
        this.epTicket = epTicket;
    }

    public Integer getEpAnnualRevenue() {
        return epAnnualRevenue;
    }

    public void setEpAnnualRevenue(Integer epAnnualRevenue) {
        this.epAnnualRevenue = epAnnualRevenue;
    }

    public Integer getEpContraryWater() {
        return epContraryWater;
    }

    public void setEpContraryWater(Integer epContraryWater) {
        this.epContraryWater = epContraryWater;
    }

    public Integer getEpPrivateWater() {
        return epPrivateWater;
    }

    public void setEpPrivateWater(Integer epPrivateWater) {
        this.epPrivateWater = epPrivateWater;
    }

    public Integer getEpLeverage() {
        return epLeverage;
    }

    public void setEpLeverage(Integer epLeverage) {
        this.epLeverage = epLeverage;
    }

    public Integer getEpNetProfit() {
        return epNetProfit;
    }

    public void setEpNetProfit(Integer epNetProfit) {
        this.epNetProfit = epNetProfit;
    }

    public Integer getEpDebt() {
        return epDebt;
    }

    public void setEpDebt(Integer epDebt) {
        this.epDebt = epDebt;
    }

    public String getEpDebtName() {
        return epDebtName;
    }

    public void setEpDebtName(String epDebtName) {
        this.epDebtName = epDebtName;
    }

    public Integer getEpDebtAmt() {
        return epDebtAmt;
    }

    public void setEpDebtAmt(Integer epDebtAmt) {
        this.epDebtAmt = epDebtAmt;
    }

    public Integer getEpMonthRepay() {
        return epMonthRepay;
    }

    public void setEpMonthRepay(Integer epMonthRepay) {
        this.epMonthRepay = epMonthRepay;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getJob() {
        return job;
    }

    public void setJob(Integer job) {
        this.job = job;
    }

    public Integer getCurrSeniority() {
        return currSeniority;
    }

    public void setCurrSeniority(Integer currSeniority) {
        this.currSeniority = currSeniority;
    }

    public String getWorkProvince() {
        return workProvince;
    }

    public void setWorkProvince(String workProvince) {
        this.workProvince = workProvince;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public String getWorkAddr() {
        return workAddr;
    }

    public void setWorkAddr(String workAddr) {
        this.workAddr = workAddr;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getWages() {
        return wages;
    }

    public void setWages(Integer wages) {
        this.wages = wages;
    }

    public Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Integer getVerifiableIncome() {
        return verifiableIncome;
    }

    public void setVerifiableIncome(Integer verifiableIncome) {
        this.verifiableIncome = verifiableIncome;
    }

    public Integer getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(Integer socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public Integer getAccuFundAge() {
        return accuFundAge;
    }

    public void setAccuFundAge(Integer accuFundAge) {
        this.accuFundAge = accuFundAge;
    }

    public Integer getSocialSecurityAge() {
        return socialSecurityAge;
    }

    public void setSocialSecurityAge(Integer socialSecurityAge) {
        this.socialSecurityAge = socialSecurityAge;
    }

    public Integer getAvocation() {
        return avocation;
    }

    public void setAvocation(Integer avocation) {
        this.avocation = avocation;
    }

    public String getAvocationInfo() {
        return avocationInfo;
    }

    public void setAvocationInfo(String avocationInfo) {
        this.avocationInfo = avocationInfo;
    }

    public Integer getAvocationAmt() {
        return avocationAmt;
    }

    public void setAvocationAmt(Integer avocationAmt) {
        this.avocationAmt = avocationAmt;
    }
}
