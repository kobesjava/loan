package com.qtt.jinrong.model.impl;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.http.action.AppReqsAction;
import com.qtt.jinrong.model.IAppBs;

/**
 * Created by yanxin on 16/3/17.
 */
public class AppBSImpl implements IAppBs {

    @Override
    public void checkUpgrade(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener) {
        AppReqsAction.checkUpgrade(context,iRequest,listener);
    }
}
