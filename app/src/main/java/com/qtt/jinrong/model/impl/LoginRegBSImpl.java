package com.qtt.jinrong.model.impl;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.http.action.UserReqsAction;
import com.qtt.jinrong.model.ILoginRegBS;

/**
 * Created by yanxin on 16/3/7.
 */
public class LoginRegBSImpl implements ILoginRegBS {

    @Override
    public void regist(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener) {
        UserReqsAction.regist(context,iRequest,listener);
    }

    @Override
    public void loginPwd(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener) {
        UserReqsAction.loginPwd(context, iRequest, listener);
    }

    @Override
    public void loginCode(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener) {
        UserReqsAction.loginCode(context, iRequest, listener);
    }

    @Override
    public void requestCode(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener) {
        UserReqsAction.requestCode(context, iRequest, listener);
    }

    @Override
    public void forgetPwd(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener) {
        UserReqsAction.resetPwd(context,iRequest,listener);
    }
}
