package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.credit.CreditApplyDetailRequest;
import com.qtt.jinrong.bean.credit.CreditApplyDetailResponse;
import com.qtt.jinrong.model.ICreditBS;
import com.qtt.jinrong.model.impl.CreditBSImpl;
import com.qtt.jinrong.presenter.ICreditApplyDetailPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.ICreditApplyDetailView;

/**
 * Created by yanxin on 16/3/20.
 */
public class CreditApplyDetailPresenterImpl implements ICreditApplyDetailPresenter {

    private ICreditApplyDetailView mView;
    private ICreditBS mBs;

    public CreditApplyDetailPresenterImpl(ICreditApplyDetailView mView) {
        this.mView = mView;
        this.mBs = new CreditBSImpl();
    }

    @Override
    public void request() {
        mView.showLoading();
        CreditApplyDetailRequest request = new CreditApplyDetailRequest();
        request.setUserId(mView.getUserId());
        request.setId(mView.getApplyId());
        mBs.requestApplyDetail(mView.getContext(), request, new MCListenerObj.IObjResListener<CreditApplyDetailResponse>() {
            @Override
            public void onSuccess(CreditApplyDetailResponse response, String url) {
                mView.hideLoading();
                if (response.isSuccess()) {
                    mView.onRequest(response.getData());
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
