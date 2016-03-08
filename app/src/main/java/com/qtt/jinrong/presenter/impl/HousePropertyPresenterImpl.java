package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.account.HousePropertyRequest;
import com.qtt.jinrong.bean.account.HousePropertyResponse;
import com.qtt.jinrong.bean.account.HousePropertySaveRequest;
import com.qtt.jinrong.model.IAccountBS;
import com.qtt.jinrong.model.impl.AccountBSImpl;
import com.qtt.jinrong.presenter.IHousePropertyPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IHousePropertyView;

/**
 * Created by yanxin on 16/3/9.
 */
public class HousePropertyPresenterImpl implements IHousePropertyPresenter {

    IHousePropertyView mView;
    IAccountBS mBs;

    public HousePropertyPresenterImpl(IHousePropertyView mView) {
        this.mView = mView;
        this.mBs = new AccountBSImpl();
    }

    @Override
    public void request() {
        HousePropertyRequest request = new HousePropertyRequest();
        request.setUserId(mView.getUserId());
        mBs.requestHouseProperty(mView.getContext(), request, new MCListenerObj.IObjResListener<HousePropertyResponse>() {
            @Override
            public void onSuccess(HousePropertyResponse response, String url) {
                if(response.isSuccess()) {
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
        HousePropertySaveRequest request = mView.getSaveRequest();
        mBs.saveHouseProperty(mView.getContext(), request, new MCListenerObj.IObjResListener<Response>() {
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
