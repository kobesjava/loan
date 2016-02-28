package com.android.finance.presenter;

/**
 * Created by yanxin on 2015/12/16.
 */
public interface IWelcomePresenter extends IPresenter {

    /**
     * 初始化全局的值
     */
    void initSystem();

    /**
     * 处理push
     */
    void doPush();

}
