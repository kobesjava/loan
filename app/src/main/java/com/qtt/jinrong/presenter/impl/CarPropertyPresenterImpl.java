package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.account.CarPropertyRequest;
import com.qtt.jinrong.bean.account.CarPropertyResponse;
import com.qtt.jinrong.bean.account.CarPropertySaveRequest;
import com.qtt.jinrong.model.IAccountBS;
import com.qtt.jinrong.model.impl.AccountBSImpl;
import com.qtt.jinrong.presenter.ICarPropertyPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.ICarPropertyView;

/**
 * Created by yanxin on 16/3/9.
 */
public class CarPropertyPresenterImpl implements ICarPropertyPresenter {

    ICarPropertyView mView;
    IAccountBS mBs;

    public CarPropertyPresenterImpl(ICarPropertyView mView) {
        this.mView = mView;
        this.mBs = new AccountBSImpl();
    }

    @Override
    public void request() {
        CarPropertyRequest request = new CarPropertyRequest();
        request.setUserId(mView.getUserId());
        mBs.requestCarProperty(mView.getContext(), request, new MCListenerObj.IObjResListener<CarPropertyResponse>() {
            @Override
            public void onSuccess(CarPropertyResponse response, String url) {
                if (response.isSuccess()) {
                    mView.onRequest(response.getData());
                } else {
                    ToastUtil.showShortToast(response.getMessage());
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
        CarPropertySaveRequest request = mView.getSaveRequest();
        mBs.saveCarProperty(mView.getContext(), request, new MCListenerObj.IObjResListener<Response>() {
            @Override
            public void onSuccess(Response response, String url) {
                mView.hideLoading();
                if(!response.isSuccess()) {
                    ToastUtil.showShortToast(response.getMessage());
                } else {
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
