package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.loan.LoanApplyModel;

import java.util.List;

/**
 * Created by yanxin on 16/3/20.
 */
public interface ILoanApplyDetailView extends IView {

    /**
     * 获取产品ID
     * @return
     */
    String getApplyId();

    /**
     * 获取贷款相亲返回处理
     */
    void onRequest(List<LoanApplyModel> model);

}
