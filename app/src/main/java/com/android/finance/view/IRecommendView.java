package com.android.finance.view;

import com.android.finance.bean.recommend.AdModel;

import java.util.List;

/**
 * Created by yanxin on 16/2/23.
 */
public interface IRecommendView extends IView {

    void onRequestAd(List<AdModel> adModels);

}
