package com.qtt.jinrong.model;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;

/**
 * Created by yanxin on 16/3/17.
 */
public interface IAppBs extends IBS {

    /**
     * 检查版本更新
     * @param context
     * @param iRequest
     * @param listener
     */
    void checkUpgrade(Context context,IRequest iRequest,MCListenerObj.IObjResListener listener);

}
