package com.android.finance.ui.fragment.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.finance.bean.user.UserInfo;
import com.android.finance.ui.widget.dialog.AlertDialogUtils;
import com.android.finance.util.UserInfoUtil;
import com.finance.framework.umeng.CommonAnalysis;

import de.greenrobot.event.EventBus;

/**
 * author yanxin
 */
public abstract class BaseFragment extends Fragment {

    protected UserInfo mUserInfo;

    public Bundle mBundle;

    protected Dialog mLoadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserInfo = UserInfoUtil.getLocalLoginResponse(getActivity());
        mBundle = getArguments();

    }

    @Override
    public void onResume() {
        super.onResume();
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

    public View inflateView(int viewSoureId) {
        return View.inflate(getActivity(), viewSoureId, null);
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
        return mUserInfo==null?"":mUserInfo.getId();
    }

    public Context getContext() {
        return getActivity();
    }

}