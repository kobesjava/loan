package com.qtt.jinrong.ui.fragment.common;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.bean.user.UserInfo;
import com.qtt.jinrong.ui.activity.user.LoginActivity;
import com.qtt.jinrong.ui.widget.dialog.AlertDialogUtils;
import com.qtt.jinrong.util.UserInfoUtil;
import com.qtt.framework.umeng.CommonAnalysis;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import de.greenrobot.event.EventBus;

/**
 * author yanxin
 */
@EFragment
public abstract class BaseFragment extends Fragment {

    protected UserInfo mUserInfo;

    public Bundle mBundle;

    protected Dialog mLoadingDialog;

    protected boolean isInit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserInfo = UserInfoUtil.getLocalLoginResponse(getActivity());
        mBundle = getArguments();
    }

    @AfterViews
    protected void initView() {
        isInit = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserInfo = UserInfoUtil.getLocalLoginResponse(getActivity());
        CommonAnalysis.onPageStart(this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        CommonAnalysis.onPageEnd(this.getClass().getSimpleName());// 保证onPageEnd在onPause之前调用,因为onPause中会保存信息
    }

    @Override
    public void onDestroy() {
        hideLoading();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    protected void login() {
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(LoginActivity.class));
        startActivity(intent);
    }

    public void showLoading() {
        if (mLoadingDialog == null) mLoadingDialog = AlertDialogUtils.showLoading(getActivity());
        if (mLoadingDialog.isShowing()) return;
        mLoadingDialog.show();
    }

    public void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.cancel();
            mLoadingDialog = null;
        }
    }

    public String getUserId() {
        return mUserInfo==null?"":mUserInfo.getUserId();
    }

    public Context getContext() {
        return getActivity();
    }

}