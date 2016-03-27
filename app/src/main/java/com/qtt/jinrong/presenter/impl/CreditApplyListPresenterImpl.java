package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.credit.CreditApplyListResponse;
import com.qtt.jinrong.model.ICreditBS;
import com.qtt.jinrong.model.impl.CreditBSImpl;
import com.qtt.jinrong.presenter.ICreditApplyListPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.ICreditApplyListView;

/**
 * Created by yanxin on 16/3/17.
 */
public class CreditApplyListPresenterImpl implements ICreditApplyListPresenter {

    ICreditApplyListView mView;
    ICreditBS mBs;

    public CreditApplyListPresenterImpl(ICreditApplyListView mView) {
        this.mView = mView;
        this.mBs = new CreditBSImpl();
    }

    @Override
    public void request() {
        mBs.requestApplyList(mView.getContext(), mView.getRequest(), new MCListenerObj.IObjResListener<CreditApplyListResponse>() {
            @Override
            public void onSuccess(CreditApplyListResponse response, String url) {
                if (response.isSuccess()) {
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
