package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.loan.LoanApplyDetailRequest;
import com.qtt.jinrong.bean.loan.LoanApplyDetailResponse;
import com.qtt.jinrong.model.ILoanBS;
import com.qtt.jinrong.model.impl.LoanBSImpl;
import com.qtt.jinrong.presenter.ILoanApplyDetailPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.ILoanApplyDetailView;

/**
 * Created by yanxin on 16/3/20.
 */
public class LoanApplyDetailPresenterImpl implements ILoanApplyDetailPresenter {

    private ILoanApplyDetailView mView;
    private ILoanBS mBs;

    public LoanApplyDetailPresenterImpl(ILoanApplyDetailView mView) {
        this.mView = mView;
        this.mBs = new LoanBSImpl();
    }

    @Override
    public void request() {
        mView.showLoading();
        LoanApplyDetailRequest request = new LoanApplyDetailRequest();
        request.setUserId(mView.getUserId());
        request.setId(mView.getApplyId());
        mBs.requestApplyDetail(mView.getContext(), request, new MCListenerObj.IObjResListener<LoanApplyDetailResponse>() {
            @Override
            public void onSuccess(LoanApplyDetailResponse response, String url) {
                mView.hideLoading();
                if(response.isSuccess()) {
                    mView.onRequest(response.getData());
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
