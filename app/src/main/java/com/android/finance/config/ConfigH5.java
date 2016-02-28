package com.android.finance.config;


import com.finance.framework.config.AppConfig;

/**
 * Created by yanxin on 2015/9/30.
 */
public class ConfigH5 {

    public static final String CENTER_PATH = "/agentAppServer";

    public static final String CONTRACT_URL = "/html/contractFAQ.html";

    public static final String ATTENDANCE_FAQ_URL = "/html/attendanceFAQ.html";

    public static final String LANDLORD_TRANSFER_FAQ = "/html/FAQ.html";

    /**
     * 获取合同帮助说明H5地址
     *
     * @return
     */
    public static String getContractUrl() {
        return AppConfig.getDomain() + CENTER_PATH + CONTRACT_URL;
    }

    /**
     * 获取考勤说明页面
     *
     * @return
     */
    public static String getAttendanceFaqUrl() {
        return AppConfig.getDomain() + CENTER_PATH + ATTENDANCE_FAQ_URL;
    }


    /**
     * 房东转接
     * @return
     */
    public static String getLandlordTransferFaq()
    {
        return AppConfig.getDomain() + CENTER_PATH + LANDLORD_TRANSFER_FAQ;
    }

}
