package com.qtt.jinrong.model;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;

/**
 * Created by yanxin on 16/3/7.
 */
public interface ILoginRegBS extends IBS{

    /**
     * 注册
     * @param context
     * @param iRequest
     * @param listener
     */
    void regist(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener);

    /**
     * 密码登录
     * @param context
     * @param iRequest
     * @param listener
     */
    void loginPwd(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener);

    /**
     * 验证码登录
     * @param context
     * @param iRequest
     * @param listener
     */
    void loginCode(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener);

    /**
     * 发送验证码
     * @param context
     * @param iRequest
     * @param listener
     */
    void requestCode(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener);

    /**
     * 忘记密码
     * @param context
     * @param iRequest
     * @param listener
     */
    void forgetPwd(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener);

}
