package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.credit.CreditApplyDetailModel;

import java.util.List;

/**
 * Created by yanxin on 16/3/20.
 */
public interface ICreditApplyDetailView extends IView {

    /**
     * 获取申请ID
     * @return
     */
    String getApplyId();

    /**
     * 获取贷款相亲返回处理
     */
    void onRequest(List<CreditApplyDetailModel> model);

}
