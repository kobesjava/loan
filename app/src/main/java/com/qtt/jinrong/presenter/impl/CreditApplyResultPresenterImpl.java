package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.credit.CreditListResponse;
import com.qtt.jinrong.model.ICreditBS;
import com.qtt.jinrong.model.impl.CreditBSImpl;
import com.qtt.jinrong.presenter.ICreditApplyResultPresenter;
import com.qtt.jinrong.view.ICreditApplyResultView;

/**
 * Created by yanxin on 16/3/14.
 */
public class CreditApplyResultPresenterImpl implements ICreditApplyResultPresenter {

    ICreditApplyResultView mView;
    ICreditBS mBs;

    public CreditApplyResultPresenterImpl(ICreditApplyResultView mView) {
        this.mView = mView;
        this.mBs = new CreditBSImpl();
    }

    @Override
    public void requestRecommend() {
        mBs.requestList(mView.getContext(), mView.getRequest(), new MCListenerObj.IObjResListener<CreditListResponse>() {
            @Override
            public void onSuccess(CreditListResponse response, String url) {
                if(response.isSuccess()) mView.onRequestRecommend(response.getData());
            }

            @Override
            public void onFail(Exception exception, String url) {

            }
        });
    }
}
