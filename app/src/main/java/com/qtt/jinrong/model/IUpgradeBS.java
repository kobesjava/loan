package com.qtt.jinrong.model;

import android.app.ProgressDialog;
import android.content.Context;

import com.qtt.jinrong.http.downloader.FileDownloadListener;

/**
 * Created by yanxin on 2015/12/14.
 */
public interface IUpgradeBS extends IBS{

    /**
     * 下载APK
     * @param context
     * @param mProgress
     * @param downloadUrl
     * @param callBack
     */
    void download(Context context, ProgressDialog mProgress, String downloadUrl, FileDownloadListener.DownloadCallBack callBack);

}
