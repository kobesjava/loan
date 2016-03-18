package com.qtt.jinrong.presenter;

/**
 * Created by yanxin on 16/3/18.
 */
public interface IIdentityPresenter extends IPresenter{

    /**
     * 获取身份信息
     */
    void request();

    /**
     * 保存身份信息
     */
    void save();

}
