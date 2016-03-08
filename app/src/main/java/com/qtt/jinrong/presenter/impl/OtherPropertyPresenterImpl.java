package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.account.OtherPropertyRequest;
import com.qtt.jinrong.bean.account.OtherPropertyResponse;
import com.qtt.jinrong.bean.account.OtherPropertySaveRequest;
import com.qtt.jinrong.model.IAccountBS;
import com.qtt.jinrong.model.impl.AccountBSImpl;
import com.qtt.jinrong.presenter.IOtherPropertyPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IOtherInfoView;

/**
 * Created by yanxin on 16/3/8.
 */
public class OtherPropertyPresenterImpl implements IOtherPropertyPresenter {

    IOtherInfoView mView;
    IAccountBS mBs;

    public OtherPropertyPresenterImpl(IOtherInfoView mView) {
        this.mView = mView;
        this.mBs = new AccountBSImpl();
    }

    @Override
    public void request() {
        OtherPropertyRequest request = new OtherPropertyRequest();
        request.setUserId(mView.getUserId());
        mBs.requestOtherProperty(mView.getContext(), request, new MCListenerObj.IObjResListener<OtherPropertyResponse>() {
            @Override
            public void onSuccess(OtherPropertyResponse response, String url) {
                if (!response.isSuccess()) {
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
        OtherPropertySaveRequest request =mView.getSaveRequest();
        mBs.saveOtherProperty(mView.getContext(), request, new MCListenerObj.IObjResListener<Response>() {
            @Override
            public void onSuccess(Response response, String url) {
                mView.hideLoading();
                if(response.isSuccess()) {
                    mView.onSaveSuccess();
                } else {
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
