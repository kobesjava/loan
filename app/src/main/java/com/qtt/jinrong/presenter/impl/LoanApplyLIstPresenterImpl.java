package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.loan.LoanApplyListResponse;
import com.qtt.jinrong.model.ILoanBS;
import com.qtt.jinrong.model.impl.LoanBSImpl;
import com.qtt.jinrong.presenter.ILoanApplyListPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.ILoanApplyListView;

/**
 * Created by yanxin on 16/3/17.
 */
public class LoanApplyLIstPresenterImpl implements ILoanApplyListPresenter {

    ILoanApplyListView mView;
    ILoanBS mBs;

    public LoanApplyLIstPresenterImpl(ILoanApplyListView mView) {
        this.mView = mView;
        this.mBs = new LoanBSImpl();
    }

    @Override
    public void request() {
        mBs.requestApplyList(mView.getContext(), mView.getRequest(), new MCListenerObj.IObjResListener<LoanApplyListResponse>() {
            @Override
            public void onSuccess(LoanApplyListResponse response, String url) {
                if(response.isSuccess()) {
                    mView.onRequest(response.getData());
                } else {
                    ToastUtil.showShortToast(response.getMessage());
                }
            }

            @Override
            public void onFail(Exception exception, String url) {
                mView.onRequestFail();
            }
        });
    }
}
