package com.qtt.jinrong.bean.account;

/**
 * Created by yanxin on 16/3/8.
 */
public class IdentityModel {

    //身份
    private Integer capacity;
    //法人或股东
    private Integer corporation;
    //企业名称
    private String epName;
    //企业地址
    private String epAddr;
    //所属行业
    private Integer epIndustry;
    //企业规模（人）
    private Integer epScale;
    //企业经营地
    private Integer epBuss;
    //经营年限
    private Integer epPeriod;
    //注册资金
    private Integer epCapital;
    //主营业务
    private String epMain;
    //年营业额（万元）
    private Integer epAnnualTurnover;
    //年开票额
    private Integer epTicket;
    //年营业收入
    private Integer epAnnualRevenue;
    //月对公流水
    private Integer epContraryWater;
    //月对私流水
    private Integer epPrivateWater;
    //资产负债率
    private Integer epLeverage;
    //年净利润
    private Integer epNetProfit;
    //企业欠款情况
    private Integer epDebt;
    //欠款机构名称
    private String epDebtName;
    //欠款余额
    private Integer epDebtAmt;
    //月还款额（万元）
    private Integer epMonthRepay;
    //就职公司类型
    private Integer companyType;
    //就职公司名称
    private String companyName;
    //职位
    private Integer job;
    //现单位工龄
    private Integer currSeniority;
    //工作地（省）
    private Integer workProvince;
    //工作地（市）
    private Integer workCity;
    //工作详细地址
    private String workAddr;
    //收入发放方式
    private Integer payWay;
    //月打卡工资（元）
    private Integer wages;
    //月均总收入（元）
    private Integer monthlyIncome;
    //能否提供收入证明
    private Integer verifiableIncome;
    //社保和公积金
    private Integer socialSecurity;
    //公积金连续缴纳年限
    private Integer accuFundAge;
    //社保连续缴纳年限
    private Integer socialSecurityAge;
    //有无副业
    private Integer avocation;
    //副业内容
    private String avocationInfo;
    //副业月收入（元）
    private Integer avocationAmt;

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

    public Integer getWorkProvince() {
        return workProvince;
    }

    public void setWorkProvince(Integer workProvince) {
        this.workProvince = workProvince;
    }

    public Integer getWorkCity() {
        return workCity;
    }

    public void setWorkCity(Integer workCity) {
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
