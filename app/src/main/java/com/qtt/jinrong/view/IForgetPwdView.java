package com.qtt.jinrong.view;

/**
 * Created by yanxin on 16/3/7.
 */
public interface IForgetPwdView extends IView {

    /**
     * 获取手机号码
     * @return
     */
    String getPhone();

    /**
     * 获取验证码
     * @return
     */
    String getCode();

    /**
     * 获取密码
     * @return
     */
    String getPwd();

    /**
     * 发送验证码结果
     * @param success
     */
    void onRequestCode(boolean success);

    /**
     * 重设密码成功
     */
    void onResetPwdSucess();

}
