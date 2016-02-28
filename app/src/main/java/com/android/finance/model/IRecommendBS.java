package com.android.finance.model;

import android.content.Context;

import com.finance.framework.http.MCListenerObj;

/**
 * Created by yanxin on 16/2/23.
 */
public interface IRecommendBS extends IBS {

    void requestAd(Context context, MCListenerObj.IObjResListener listener);

}
