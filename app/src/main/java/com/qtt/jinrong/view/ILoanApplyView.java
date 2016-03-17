package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.loan.LoanApplyRequest;

/**
 * Created by yanxin on 16/3/14.
 */
public interface ILoanApplyView extends IView {

    /**
     * 获取请求request
     * @return
     */
    LoanApplyRequest getReauest();

    /**
     * 申请返回出来
     */
    void onApply(Response response);

    /**
     * 获取资质审核信息返回处理
     */
    void onRequestVerify();
}
