package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.account.IdentityRequest;
import com.qtt.jinrong.bean.account.IdentityResponse;
import com.qtt.jinrong.model.IAccountBS;
import com.qtt.jinrong.model.impl.AccountBSImpl;
import com.qtt.jinrong.presenter.IIdentityPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IIdentityView;

/**
 * Created by yanxin on 16/3/18.
 */
public class IdentityPresenterImpl implements IIdentityPresenter {

    IIdentityView mView;
    IAccountBS mBs;

    public IdentityPresenterImpl(IIdentityView mView) {
        this.mView = mView;
        this.mBs = new AccountBSImpl();
    }

    @Override
    public void request() {
        IdentityRequest request = new IdentityRequest();
        request.setUserId(mView.getUserId());
        mBs.requestIdentity(mView.getContext(), request, new MCListenerObj.IObjResListener<IdentityResponse>() {
            @Override
            public void onSuccess(IdentityResponse response, String url) {
                if(response.isSuccess()) {
                    mView.onRequest(response.getData());
                }
            }

            @Override
            public void onFail(Exception exception, String url) {

            }
        });
    }

    @Override
    public void save() {
        mView.showLoading();
        mBs.saveIdentity(mView.getContext(), mView.getSaveRequest(), new MCListenerObj.IObjResListener<Response>() {
            @Override
            public void onSuccess(Response response, String url) {
                mView.hideLoading();
                if(response.isSuccess()) {
                    mView.onSaveSuccess();
                }else {
                    ToastUtil.showShortToast(response.getMessage());
                }
            }

            @Override
            public void onFail(Exception exception, String url) {
                mView.hideLoading();
            }
        });
    }
}
