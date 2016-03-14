package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.credit.CreditModel;
import com.qtt.jinrong.bean.credit.CreditProductRecommendRequest;

import java.util.List;

/**
 * Created by yanxin on 16/3/14.
 */
public interface ICreditApplyResultView extends IView{

    /**
     * 获取request
     */
    CreditProductRecommendRequest getRequest();

    /**
     * 获取推荐的产品返回处理
     * @param loans
     */
    void onRequestRecommend(List<CreditModel> loans);

}
