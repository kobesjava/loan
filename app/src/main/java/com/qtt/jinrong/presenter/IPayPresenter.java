package com.qtt.jinrong.presenter;

/**
 * Created by yanxin on 16/4/13.
 */
public interface IPayPresenter extends IPresenter{

    /**
     * 获取签名
     */
    void requestSign();

    /**
     * 支付
     */
    void pay();

    /**
     * 获取支付结果
     */
    void requestPayResult();

}
