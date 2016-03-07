package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.account.FinancingDemandsRequest;
import com.qtt.jinrong.bean.account.FinancingDemandsResponse;
import com.qtt.jinrong.model.IAccountBS;
import com.qtt.jinrong.model.impl.AccountBSImpl;
import com.qtt.jinrong.presenter.IFinancingDemandPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IFinancingDemandsView;

/**
 * Created by yanxin on 16/3/8.
 */
public class FinancingDemandPresenterImpl implements IFinancingDemandPresenter {

    IFinancingDemandsView mView;
    IAccountBS mBs;

    public FinancingDemandPresenterImpl(IFinancingDemandsView mView) {
        this.mView = mView;
        this.mBs = new AccountBSImpl();
    }

    @Override
    public void request() {
        FinancingDemandsRequest request = new FinancingDemandsRequest();
        request.setUserId(mView.getUserId());
        mBs.requestFinancingDemands(mView.getContext(), request, new MCListenerObj.IObjResListener<FinancingDemandsResponse>() {
            @Override
            public void onSuccess(FinancingDemandsResponse response, String url) {
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

}
