package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.user.UserInfo;

/**
 * Created by yanxin on 16/3/7.
 */
public interface IRegistView extends IView {

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
     * 获取性别
     * @return
     */
    int getGender();

    /**
     * 获取昵称
     * @return
     */
    String getNickname();

    /**
     * 注册返回
     */
    void onRegist();

    /**
     * 发送验证码结果
     * @param success
     */
    void onRequestCode(boolean success);

}
