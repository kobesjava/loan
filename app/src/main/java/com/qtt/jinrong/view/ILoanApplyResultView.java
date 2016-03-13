package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.loan.LoanModel;
import com.qtt.jinrong.bean.loan.LoanProductRecommendRequest;

import java.util.List;

/**
 * Created by yanxin on 16/3/14.
 */
public interface ILoanApplyResultView extends IView{

    /**
     * 获取request
     */
    LoanProductRecommendRequest getRequest();

    /**
     * 获取推荐的产品返回处理
     * @param loans
     */
    void onRequestRecommend(List<LoanModel> loans);

}
