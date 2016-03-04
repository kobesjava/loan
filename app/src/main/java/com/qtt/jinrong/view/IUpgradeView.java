package com.qtt.jinrong.view;

import android.app.ProgressDialog;

import com.qtt.jinrong.bean.app.UpgradeModel;

/**
 * Created by yanxin on 2015/12/14.
 */
public interface IUpgradeView extends IView {

    /**
     * 获取升级bean
     *
     * @return
     */
    UpgradeModel getUpgradeModel();

    /**
     * 获取进度条控件
     *
     * @return
     */
    ProgressDialog getProgressDialog();

    /**
     * 关闭进度条
     */
    void hideProgressDialog();

    /**
     * 下载apk失败
     */
    void downloadFail();

    /**
     * 升级失败
     */
    void onUpgradeFail();

    /**
     * 升级成功
     */
    void onUpgradeSucess();

}
