package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.loan.LoanProductDetailRequest;
import com.qtt.jinrong.bean.loan.LoanProductDetailResponse;
import com.qtt.jinrong.model.ILoanBS;
import com.qtt.jinrong.model.impl.LoanBSImpl;
import com.qtt.jinrong.presenter.ILoanProductPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.ILoanProductDetailView;

/**
 * Created by yanxin on 16/3/8.
 */
public class LoanProductPresenterImpl implements ILoanProductPresenter {

    private ILoanProductDetailView mView;
    private ILoanBS mBs;

    public LoanProductPresenterImpl(ILoanProductDetailView mView) {
        this.mView = mView;
        this.mBs = new LoanBSImpl();
    }

    @Override
    public void request() {
        mView.showLoading();
        LoanProductDetailRequest request = new LoanProductDetailRequest();
        request.setId(mView.getProductId());
        mBs.requestLoanProductDetail(mView.getContext(), request, new MCListenerObj.IObjResListener<LoanProductDetailResponse>() {
            @Override
            public void onSuccess(LoanProductDetailResponse response, String url) {
                mView.hideLoading();
                if(!response.isSuccess()) {
                    ToastUtil.showShortToast(response.getMessage());
                } else {
                    mView.onRequest(response.getData());
                }
            }

            @Override
            public void onFail(Exception exception, String url) {
                mView.hideLoading();
            }
        });
    }

}
