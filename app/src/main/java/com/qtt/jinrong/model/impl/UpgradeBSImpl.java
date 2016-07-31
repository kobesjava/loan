package com.qtt.jinrong.model.impl;

import android.app.ProgressDialog;
import android.content.Context;

import com.qtt.jinrong.R;
import com.qtt.jinrong.config.Constants;
import com.qtt.jinrong.http.downloader.FileDownloadListener;
import com.qtt.jinrong.model.IUpgradeBS;

import java.io.File;

/**
 * Created by yanxin on 2015/12/14.
 */
public class UpgradeBSImpl implements IUpgradeBS {

    private final String TAG = "UPGRADE";

    private final String DOWNLOAD_DIR = Constants.FILE_ROOT_DIR + File.separator + Constants.APK_DIR;

    @Override
    public void download(Context context, ProgressDialog mProgress, String downloadUrl, FileDownloadListener.DownloadCallBack callBack) {
        new FileDownloadListener(context, mProgress, downloadUrl, callBack, R.drawable.ic_launcher, "秒融金融超市", DOWNLOAD_DIR);
    }

}
