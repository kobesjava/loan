package com.android.finance.http;

import com.finance.framework.config.AppConfig;

/**
 * Created by yanxin on 16/2/23.
 */
public class Api {

    public final static String ROOT_URL = AppConfig.getDomain();
    public final static String CENTER_URL = "/leshifu/api/";

    public final static String URL = ROOT_URL+CENTER_URL;

    public final static String AD = URL + "service";

}
