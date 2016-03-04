package com.qtt.jinrong.model;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;

/**
 * Created by yanxin on 16/2/23.
 */
public interface IRecommendBS extends IBS {

    void requestAd(Context context, MCListenerObj.IObjResListener listener);

}
