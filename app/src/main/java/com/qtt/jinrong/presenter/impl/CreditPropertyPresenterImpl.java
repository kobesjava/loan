package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.account.CreditPropertyRequest;
import com.qtt.jinrong.bean.account.CreditPropertyResponse;
import com.qtt.jinrong.bean.account.CreditPropertySaveRequest;
import com.qtt.jinrong.model.IAccountBS;
import com.qtt.jinrong.model.impl.AccountBSImpl;
import com.qtt.jinrong.presenter.ICreditPropertyPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.ICreditPropertyView;

/**
 * Created by yanxin on 16/3/8.
 */
public class CreditPropertyPresenterImpl implements ICreditPropertyPresenter {

    ICreditPropertyView mView;
    IAccountBS mBs;

    public CreditPropertyPresenterImpl(ICreditPropertyView mView) {
        this.mView = mView;
        this.mBs = new AccountBSImpl();
    }

    @Override
    public void request() {
        CreditPropertyRequest request = new CreditPropertyRequest();
        request.setUserId(mView.getUserId());
        mBs.requestCreditProperty(mView.getContext(), request, new MCListenerObj.IObjResListener<CreditPropertyResponse>() {
            @Override
            public void onSuccess(CreditPropertyResponse response, String url) {
                if(!response.isSuccess()) {
                    ToastUtil.showShortToast(response.getMessage());
                } else {
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
        CreditPropertySaveRequest request = mView.getSaveRequest();
        mBs.saveCreditProperty(mView.getContext(), request, new MCListenerObj.IObjResListener<Response>() {
            @Override
            public void onSuccess(Response response, String url) {
                mView.hideLoading();
                if(response.isSuccess()) {
                    mView.onSaveSuccess();
                }
            }

            @Override
            public void onFail(Exception exception, String url) {
                mView.hideLoading();
            }
        });
    }
}
