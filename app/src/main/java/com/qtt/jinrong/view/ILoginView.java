package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.user.UserInfo;

/**
 * Created by yanxin on 16/3/7.
 */
public interface ILoginView extends IView {

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

    void onLogin(UserInfo userInfo);

    void onRequestCode(boolean success);

}
