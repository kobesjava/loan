package com.qtt.jinrong.presenter;

/**
 * Created by yanxin on 16/3/7.
 */
public interface ILoginRegistPresenter extends IPresenter {

    /*
     * 密码登录
     */
    void loginPwd();

    /**
     * 验证码登录
     */
    void loginCode();

    /**
     * 请求发送验证码
     */
    void requestCode();

    /**
     * 忘记密码
     */
    void forgetPwd();

    /**
     * 注册
     */
    void regist();

}
