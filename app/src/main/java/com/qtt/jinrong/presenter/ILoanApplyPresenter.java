package com.qtt.jinrong.presenter;

/**
 * Created by yanxin on 16/3/14.
 */
public interface ILoanApplyPresenter extends IPresenter {

    /**
     * 获取资质审核信息
     */
    void requestVerify();

    /**
     * 申请贷款
     */
    void apply();

}
