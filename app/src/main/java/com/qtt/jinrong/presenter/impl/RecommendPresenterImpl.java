package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.recommend.AdResponse;
import com.qtt.jinrong.model.IRecommendBS;
import com.qtt.jinrong.model.impl.RecommendBSImpl;
import com.qtt.jinrong.presenter.IRecommendPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IRecommendView;

/**
 * Created by yanxin on 16/2/23.
 */
public class RecommendPresenterImpl implements IRecommendPresenter {

    private IRecommendView mIRecommendView;
    private IRecommendBS mIRecommendBS;

    public RecommendPresenterImpl(IRecommendView iRecommendView) {
        this.mIRecommendView = iRecommendView;
        this.mIRecommendBS = new RecommendBSImpl();
    }

    @Override
    public void requestAd() {
        mIRecommendBS.requestAd(mIRecommendView.getContext(), new MCListenerObj.IObjResListener<AdResponse>() {
            @Override
            public void onSuccess(AdResponse response, String url) {
                if(!response.isSuccess()) {
                    ToastUtil.showShortToast(response.getMessage());
                }
                mIRecommendView.onRequestAd(response.getData());
            }

            @Override
            public void onFail(Exception exception, String url) {
                mIRecommendView.onRequestAd(null);
            }
        });
    }
}
