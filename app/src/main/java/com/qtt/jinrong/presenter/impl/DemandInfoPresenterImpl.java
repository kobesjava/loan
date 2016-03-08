package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.account.DemandRequest;
import com.qtt.jinrong.bean.account.DemandSaveRequest;
import com.qtt.jinrong.bean.account.DemandsResponse;
import com.qtt.jinrong.model.IAccountBS;
import com.qtt.jinrong.model.impl.AccountBSImpl;
import com.qtt.jinrong.presenter.IDemandInfoPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IDemandsInfoView;

/**
 * Created by yanxin on 16/3/8.
 */
public class DemandInfoPresenterImpl implements IDemandInfoPresenter {

    IDemandsInfoView mView;
    IAccountBS mBs;

    public DemandInfoPresenterImpl(IDemandsInfoView mView) {
        this.mView = mView;
        mBs = new AccountBSImpl();
    }

    @Override
    public void request() {
        DemandRequest request = new DemandRequest();
        request.setUserId(mView.getUserId());
        mBs.requestDemands(mView.getContext(), request, new MCListenerObj.IObjResListener<DemandsResponse>() {
            @Override
            public void onSuccess(DemandsResponse response, String url) {
                if(response.isSuccess()) mView.onRequest(response.getData());
            }

            @Override
            public void onFail(Exception exception, String url) {

            }
        });
    }

    @Override
    public void save() {
        mView.showLoading();
        DemandSaveRequest request = mView.getSaveRequest();
        mBs.saveDemands(mView.getContext(), request, new MCListenerObj.IObjResListener<Response>() {
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
