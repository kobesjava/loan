package com.qtt.jinrong.view;

/**
 * Created by yanxin on 16/3/13.
 */
public interface IRegistView extends IView {

    /** 获取手机号码*/
    String getPhone();

    /** 发送验证码返回处理*/
    void onRequestCode(boolean success);

}
