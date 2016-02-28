package com.finance.framework.config;

import android.app.Application;

public class AppConfig {
    /**
     * 应用初始化必须赋值
     */
    public static Application application = null;
    public static int versionCode = 1;
    public static String versionName = "1.0";

    /**
     * 根据项目需求选择完善的属性
     */
    public static String UID = "-1"; // 已登录用户的Uid，默认为-1
    public static String u_ticket = ""; // 已登录用户的cookie，默认""


    /**add by w_xiong*/

    /**
     * 渠道号，统计崩溃信息用
     */
    public static String channelNo = "iwjw";

    /**
     * 是否线上版本
     */
    public static boolean IS_RELEASE_VERSION = true;

    /**
     * 平台名称 IWJW或者FYB
     */
    public static String platform = "IWJW";

    public static String distance = "";

    public static String cityId = "";

    /**
     * 发布版本必须确认的字段
     */
    public static String pushserver_ip = "push.fyb365.com";
    public static int pushserver_port = 6502;
    public static String AES_KEY = "0123456789ABCDEF";

    private static String host_pushserver_ip = "";
    private static int host_pushserver_port = 6502;
    private static String host_aes_key = "0123456789ABCDEF";

    private static String host_name = "";
    private static int host_port = 80;
    private static String host_protocol = "http";
    private static String host_path = "";

    private static String host_appkey = "";
    private static String host_env = "";

    private static boolean analysis_enable = true;
    private static String used_analysis_framework = "";
    private static boolean network_framework_enable = true;
    private static String used_network_framework = "";
    private static boolean push_enable = true;
    private static String used_push_framework = "";
    private static boolean map_enable = true;
    private static String used_map_framework = "";
    private static boolean bitmap_thirdloader_enable = true;
    private static String used_bitmap_framework = "";
    private static boolean database_framework_enable = true;
    private static String used_database_framework = "";
    private static boolean annotation_enable = true;
    private static String used_annotation_framework = "";
    private static boolean encrypt_enable = true;
    private static String used_encrypt_framework = "";
    private static boolean open_test_code = true;

    //定位信息上传服务器
    private static String host_location = "";

    private static String host_video_soa = "";

    public static String getHost_video_soa() {
        return host_video_soa;
    }

    public static void setHost_video_soa(String host_video_soa) {
        AppConfig.host_video_soa = host_video_soa;
    }

    public static String get_Host_location() {
        return host_location;
    }

    public static void set_Host_location(String host_location) {
        AppConfig.host_location = host_location;
    }

    public static String getRootUrl() {
        return host_protocol + "://" + host_name + ":" + host_port + host_path;
    }

    public static String getDomain() {
        return host_protocol + "://" + host_name + ":" + host_port;
    }


    public static String getLocationUrl()

    {
        return host_protocol + "://" + host_location;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_host_env(String p_host_env) {
        host_env = p_host_env;
    }

    public static String get_host_env() {
        return host_env;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_host_protocol(String p_host_protocol) {
        host_protocol = p_host_protocol;
    }

    public static String get_host_protocol() {
        return host_protocol;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_host_path(String p_host_path) {
        host_path = p_host_path;
    }

    public static String get_host_path() {
        return host_path;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_host_name(String p_host_name) {
        host_name = p_host_name;
    }

    public static String get_host_name() {
        return host_name;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_host_port(int p_host_port) {
        host_port = p_host_port;
    }

    public static int get_host_port() {
        return host_port;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_host_pushserver_ip(String p_host_pushserver_ip) {
        host_pushserver_ip = p_host_pushserver_ip;
    }

    public static String get_host_pushserver_ip() {
        return host_pushserver_ip;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_host_pushserver_port(int p_host_pushserver_port) {
        host_pushserver_port = p_host_pushserver_port;
    }

    public static String get_host_aeskey() {
        return host_aes_key;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_host_aes_key(String p_host_aes_key) {
        host_aes_key = p_host_aes_key;
    }

    public static int get_host_pushserver_port() {
        return host_pushserver_port;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_appkey(String p_appkey) {
        host_appkey = p_appkey;
    }

    public static String get_appkey() {
        return host_appkey;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_open_test_code(Boolean p_open_test_code) {
        open_test_code = p_open_test_code;
    }

    public static boolean get_open_test_code() {
        return open_test_code;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_used_encrypt_framework(String p_used_encrypt_framework) {
        used_encrypt_framework = p_used_encrypt_framework;
    }

    public static String get_used_encrypt_framework() {
        return used_encrypt_framework;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_annotation_enable(Boolean p_annotation_enable) {
        annotation_enable = p_annotation_enable;
    }

    public static boolean get_annotation_enable() {
        return annotation_enable;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_encrypt_enable(Boolean p_encrypt_enable) {
        encrypt_enable = p_encrypt_enable;
    }

    public static boolean get_encrypt_enable() {
        return encrypt_enable;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_database_framework_enable(Boolean p_database_framework_enable) {
        database_framework_enable = p_database_framework_enable;
    }

    public static boolean get_database_framework_enable() {
        return database_framework_enable;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_bitmap_thirdloader_enable(Boolean p_bitmap_thirdloader_enable) {
        bitmap_thirdloader_enable = p_bitmap_thirdloader_enable;
    }

    public static boolean get_bitmap_thirdloader_enable() {
        return bitmap_thirdloader_enable;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_map_enable(Boolean p_map_enable) {
        map_enable = p_map_enable;
    }

    public static boolean get_map_enable() {
        return map_enable;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_push_enable(Boolean p_push_enable) {
        push_enable = p_push_enable;
    }

    public static boolean get_push_enable() {
        return push_enable;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_network_framework_enable(Boolean p_network_framework_enable) {
        network_framework_enable = p_network_framework_enable;
    }

    public boolean get_network_framework_enable() {
        return network_framework_enable;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_analysis_enable(Boolean p_analysis_enable) {
        analysis_enable = p_analysis_enable;
    }

    public static boolean get_analysis_enable() {
        return analysis_enable;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_used_analysis_framework(String p_used_analysis_framework) {
        used_analysis_framework = p_used_analysis_framework;
    }

    public static String get_used_analysis_framework() {
        return used_analysis_framework;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_used_network_framework(String p_used_network_framework) {
        used_network_framework = p_used_network_framework;
    }

    public static String get_used_network_framework() {
        return used_network_framework;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_used_push_framework(String p_used_push_framework) {
        used_push_framework = p_used_push_framework;
    }

    public static String get_used_push_framework() {
        return used_push_framework;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_used_map_framework(String p_used_map_framework) {
        used_map_framework = p_used_map_framework;
    }

    public static String get_used_map_framework() {
        return used_map_framework;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_used_bitmap_framework(String p_used_bitmap_framework) {
        used_bitmap_framework = p_used_bitmap_framework;
    }

    public static String get_used_bitmap_framework() {
        return used_bitmap_framework;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_used_database_framework(String p_used_database_framework) {
        used_database_framework = p_used_database_framework;
    }

    public static String get_used_database_framework() {
        return used_database_framework;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void set_used_annotation_framework(String p_used_annotation_framework) {
        used_annotation_framework = p_used_annotation_framework;
    }

    public static String get_used_annotation_framework() {
        return used_annotation_framework;
    }

}
