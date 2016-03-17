package com.qtt.jinrong.presenter.impl;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.qtt.jinrong.bean.app.UpgradeModel;
import com.qtt.jinrong.http.downloader.FileDownloadListener;
import com.qtt.jinrong.model.IUpgradeBS;
import com.qtt.jinrong.model.impl.UpgradeBSImpl;
import com.qtt.jinrong.presenter.IUpgradePresenter;
import com.qtt.jinrong.view.IUpgradeView;

import java.io.File;

/**
 * Created by yanxin on 2015/12/14.
 */
public class UpgradePresenterImpl implements IUpgradePresenter {

    private final String TAG = "UPGRADE";

    private IUpgradeView mView;
    private IUpgradeBS mBs;

    public UpgradePresenterImpl(IUpgradeView mView) {
        this.mView = mView;
        this.mBs = new UpgradeBSImpl();
    }

    @Override
    public void download() {

        FileDownloadListener.DownloadCallBack callBack = new FileDownloadListener.DownloadCallBack() {
            @Override
            public void downloadSuccess(File file) {

                mView.hideProgressDialog();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                mView.getContext().startActivity(intent);
                NotificationManager notificationMgr = (NotificationManager) mView.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationMgr.cancelAll();
                mView.onUpgradeSucess();
            }

            @Override
            public void downloadFail() {
                mView.hideProgressDialog();
                mView.downloadFail();
            }
        };

        UpgradeModel model = mView.getUpgradeModel();
        mBs.download(mView.getContext(), mView.getProgressDialog(), model.getUrl(), callBack);
    }

}
