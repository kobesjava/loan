package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.credit.CreditApplyRequest;
import com.qtt.jinrong.bean.credit.CreditDetailRequest;
import com.qtt.jinrong.bean.credit.CreditProductDetailResponse;
import com.qtt.jinrong.model.ICreditBS;
import com.qtt.jinrong.model.impl.CreditBSImpl;
import com.qtt.jinrong.presenter.ICreditApplyPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.ICreditDetailView;

/**
 * Created by yanxin on 16/3/14.
 */
public class CreditApplyPresenterImpl implements ICreditApplyPresenter {

    ICreditDetailView mView;
    ICreditBS mBs;

    public CreditApplyPresenterImpl(ICreditDetailView mView) {
        this.mView = mView;
        this.mBs = new CreditBSImpl();
    }

    @Override
    public void request() {
        mView.showLoading();
        CreditDetailRequest request = new CreditDetailRequest();
        request.setId(mView.getCreditId());
        mBs.requestCreditProductDetail(mView.getContext(), request, new MCListenerObj.IObjResListener<CreditProductDetailResponse>() {
            @Override
            public void onSuccess(CreditProductDetailResponse response, String url) {
                mView.hideLoading();
                if(response.isSuccess()) {
                    mView.onRequestDetail(response.getData());
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

    @Override
    public void apply() {
        CreditApplyRequest request = new CreditApplyRequest();
        request.setCreditId(mView.getCreditId());
        request.setUserId(mView.getUserId());
        mBs.apply(mView.getContext(), request, new MCListenerObj.IObjResListener<Response>() {
            @Override
            public void onSuccess(Response response, String url) {
                mView.onApply(response);
            }

            @Override
            public void onFail(Exception exception, String url) {
                mView.hideLoading();
            }
        });
    }

}
