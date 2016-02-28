package com.android.finance.config;

import android.os.Environment;

import java.io.File;

/**
 * 常量数据
 */
public class Constants {

    // common
    public static final String SD_CARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String SEPARATOR = File.separator;
    /** SDK 根目录*/
    public static final String FILE_ROOT_DIR = "Loan";
    public static final String ROOT_PATH = SD_CARD_PATH + SEPARATOR + FILE_ROOT_DIR + SEPARATOR;
    public static final String VIDEO_PATH = ROOT_PATH + "Video" + SEPARATOR;
    public static final String APK_DIR = "apk";

    // AreaChildDialogFragment
    public final static String SELECTED_ESTATE_ID = "selected_estate_id";
    public final static String SELECTED_ESTATE_DATA = "selected_estate_data";
    public final static String SELECTED_ESTATE_NAME = "selected_estate_name";

    // SearchLivingAreaFragment
    public final static int MAX_LOCAL_HISTORY_SEARCH_LIVING = 50;

    //SearchFragment
    public final static int MAX_LOCAL_HISTORY = 50;

    //SearchResultListFragment
    public static final long TWO_WEEKS = 2 * 7 * 24 * 60 * 60 * 1000;

    //AgendaFragment
    public static final String APPOINT_INFO = "appoint_info";
    public static final String MINE = "mine";

    // cursor loader id
    public static final int SEARCHFRAGMENT_LOADER_ID = 0;
    public static final int SEARCHLIVINGAREAFRAGMENT_LOADER_ID = 1;
    public static final int SUBMIT_PICTURE_LOADER_ID = 2000;
    public static final int CHOOSE_TAG_LOADER_ID = 3000;

    // NewHouseFragment CtyId
    public static final int ID_CITY_BEIJING = 12438; //北京

    /** 登录失效码*/
    public static final int LOGIN_EXPIRED = 110001;

    /** 开启热修复*/
    public static final boolean CHECK_HOTFIX = false;

    public static final String INTENTFILTER_ACTION_INSTALL          = "INTENTFILTER_ACTION_INSTALL";            //安装
    public static final String INTENTFILTER_ACTION_CANCEL_DOWNLOAD  =  "INTENTFILTER_ACTION_CANCEL_DOWNLOAD";   //取消下载
    public static final String INTENTFILTER_ACTION_UPDATE           = "INTENTFILTER_ACTION_UPDATE";             //升级
    public static final String INTENTFILTER_ACTION_SHOW_UPDATE      = "INTENTFILTER_ACTION_SHOW_UPDATE";        //提示升级

    /** 搜房帮 包名*/
    public static final String SOUFANGBAO_PACKAGENAME = "com.soufun";

    /** 客服电话*/
    public static final String CUSTOMER_SERVICE_PHONE = "400-685-9966";

    public static final int EXIT_INTERVAL = 2000;
}
