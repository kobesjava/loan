package com.qtt.jinrong.ui.activity.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.qtt.jinrong.bean.user.UserInfo;
import com.qtt.jinrong.common.wrap.WrapHandler;
import com.qtt.jinrong.ui.widget.dialog.AlertDialogUtils;
import com.qtt.jinrong.util.UserInfoUtil;
import com.qtt.framework.http.RequestManger;
import com.qtt.framework.umeng.CommonAnalysis;

/**
 * @author yanxin 
 */
public abstract class BaseActivity extends FragmentActivity {

    private final String TAG = "BaseActivity";

    /**
     * 栈顶的activity
     */
    public static Activity mTopActivity;

    protected Intent mIntent;

    protected UserInfo mUserInfo;

    protected Dialog mLoadingDialog;
    protected Dialog mDialog;
    protected WrapHandler mWrapHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntent = getIntent();
        mUserInfo = UserInfoUtil.getUserInfo();
        mTopActivity = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTopActivity = this;
        initUserInfo();
        CommonAnalysis.onPageStart(this.getClass().getSimpleName());
        CommonAnalysis.onResume(this);
    }

    /**
     * 获取用户登录信息
     */
    private void initUserInfo() {
        mUserInfo = UserInfoUtil.getUserInfo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        CommonAnalysis.onPageEnd(this.getClass().getSimpleName());// 保证onPageEnd在onPause之前调用,因为onPause中会保存信息
        CommonAnalysis.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mTopActivity == this) mTopActivity = null;
        RequestManger.getInstence(this).cancel(getClass().getSimpleName());
        if(mWrapHandler != null) mWrapHandler.destory();
        destroyDialog();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isEnableBackKey()) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 是否禁止系统的返回键
     * @return
     */
    protected boolean isEnableBackKey() {
        return false;
    }

    public void showLoading() {
        if (mLoadingDialog == null) mLoadingDialog = AlertDialogUtils.showLoading(this);
        if (mLoadingDialog.isShowing()) return;
        mLoadingDialog.show();
    }

    public void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    protected void hideDialog() {
        if(mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    protected void destroyDialog() {
        hideDialog();
        hideLoading();
    }

    public Context getContext() {
        return this;
    }

    /**
     * 获取ID
     * @return
     */
    public String getUserId() {
        return mUserInfo!=null?mUserInfo.getUserId():"";
    }

}