package com.qtt.jinrong.config;

import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;

import com.qtt.jinrong.app.MyApplication;
import com.qtt.framework.config.AppConfig;
import com.qtt.framework.util.FileUtils;
import com.qtt.framework.util.LogUtil;
import com.qtt.framework.util.MD5Util;
import com.qtt.framework.util.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by yanxin on 2015/6/8.
 */
public class ConfigManager {

    private final String TAG = "ConfigManager";

    private static ConfigManager instance;

    private final static String fontPath = "iconfont/iconfont.ttf";
    private Typeface iconfont;

    private ConfigManager() {
    }

    public static ConfigManager build() {
        if (instance == null) instance = new ConfigManager();
        return instance;
    }

    public Typeface getIconfont() {
        if (iconfont == null)
            iconfont = Typeface.createFromAsset(MyApplication.getInstance().getAssets(), fontPath);
        return iconfont;
    }

    public void init() {
        if (!checkmd5()) {
            android.os.Process.killProcess(android.os.Process.myPid());
            return;
        }

        loadConfig();
    }

    /**
     * 配置文件合法性校验和加载配置文件开始-by brucexie
     */
    private boolean checkmd5() {
        String md5_orgin = "";
        String md5_new = "";
        try {
            AssetFileDescriptor fd1 = MyApplication.getInstance().getAssets().openFd("config.properties.txt");
            FileInputStream fis1 = fd1.createInputStream();
            long length1 = fd1.getLength();

            md5_orgin = FileUtils.readFileContentStr(fis1, length1);

            if (!StringUtil.isEmptyNull(md5_orgin)) {
                md5_orgin = md5_orgin.trim();
            }

            InputStream fis2 = MyApplication.getInstance().getAssets().open("config.properties");

            md5_new = MD5Util.getFileMD5String(fis2);

            fis1.close();
            fis2.close();
            return md5_orgin.equals(md5_new);
        } catch (Exception e) {
            LogUtil.d(TAG, e.getMessage());
        }

        return false;
    }

    /**
     * 加载配置文件
     */
    private void loadConfig() {
        Properties p = new Properties();
        try {
            InputStream in_orgin = MyApplication.getInstance().getAssets().open("config.properties");
            int count = in_orgin.available();
            byte[] buffer_new = new byte[count];
            in_orgin.read(buffer_new);
            for (int i = 0; i < count; i++) {
                buffer_new[i] = (byte) (buffer_new[i] - 100);
            }
            InputStream in_new = new ByteArrayInputStream(buffer_new);
            p.load(in_new);
            in_orgin.close();
            in_new.close();

            AppConfig.host_env = p.getProperty("HOST_ENV");
            AppConfig.host_name = p.getProperty("HOST_NAME");
            AppConfig.host_port = Integer.parseInt(p.getProperty("HOST_PORT").trim());
            AppConfig.host_protocol = p.getProperty("HOST_PROTOCOL");
            AppConfig.host_path = p.getProperty("HOST_PATH");

        } catch (IOException e) {
            LogUtil.d(TAG, e.getMessage());
            android.os.Process.killProcess(android.os.Process.myPid());
        }

    }

}
