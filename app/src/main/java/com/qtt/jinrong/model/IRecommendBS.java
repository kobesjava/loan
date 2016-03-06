package com.qtt.jinrong.model;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;

/**
 * Created by yanxin on 16/2/23.
 */
public interface IRecommendBS extends IBS {

    /**
     * 请求滚动栏接口
     * @param context
     * @param listener
     */
    void requestAd(Context context, MCListenerObj.IObjResListener listener);

}
