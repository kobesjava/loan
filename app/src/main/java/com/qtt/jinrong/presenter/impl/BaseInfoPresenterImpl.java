package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.account.BaseInfoRequest;
import com.qtt.jinrong.bean.account.BaseInfoResponse;
import com.qtt.jinrong.bean.account.BaseInfoSaveRequest;
import com.qtt.jinrong.model.IAccountBS;
import com.qtt.jinrong.model.impl.AccountBSImpl;
import com.qtt.jinrong.presenter.IBaseInfoPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IBaseInfoView;

/**
 * Created by yanxin on 16/3/8.
 */
public class BaseInfoPresenterImpl implements IBaseInfoPresenter {

    IBaseInfoView mView;
    IAccountBS mBs;

    public BaseInfoPresenterImpl(IBaseInfoView mView) {
        this.mView = mView;
        this.mBs = new AccountBSImpl();
    }

    @Override
    public void request() {
        BaseInfoRequest request = new BaseInfoRequest();
        request.setUserId(mView.getUserId());
        mBs.requestBaseInfo(mView.getContext(), request, new MCListenerObj.IObjResListener<BaseInfoResponse>() {
            @Override
            public void onSuccess(BaseInfoResponse response, String url) {
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
        BaseInfoSaveRequest request =mView.getSaveRequest();
        mBs.saveBaseInfo(mView.getContext(), request, new MCListenerObj.IObjResListener<Response>() {
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
