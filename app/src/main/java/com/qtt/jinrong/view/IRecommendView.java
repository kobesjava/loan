package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.recommend.AdModel;

import java.util.List;

/**
 * Created by yanxin on 16/2/23.
 */
public interface IRecommendView extends IView {

    void onRequestAd(List<AdModel> adModels);

}
