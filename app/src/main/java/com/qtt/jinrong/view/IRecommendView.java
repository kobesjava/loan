package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.loan.LoanModel;
import com.qtt.jinrong.bean.recommend.AdModel;

import java.util.List;

/**
 * Created by yanxin on 16/2/23.
 */
public interface IRecommendView extends IView {

    /**
     * 获取滚动栏处理
     * @param adModels
     */
    void onRequestAd(List<AdModel> adModels);

    /**
     * 获取推荐贷款处理
     * @param loanModels
     */
    void onRequestLoan(List<LoanModel> loanModels);

}
