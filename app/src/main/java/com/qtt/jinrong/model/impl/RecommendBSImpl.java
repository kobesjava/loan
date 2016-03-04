package com.qtt.jinrong.model.impl;

import android.content.Context;

import com.qtt.jinrong.http.action.RecommendAction;
import com.qtt.jinrong.model.IRecommendBS;
import com.qtt.framework.http.MCListenerObj;

/**
 * Created by yanxin on 16/2/23.
 */
public class RecommendBSImpl implements IRecommendBS {

    @Override
    public void requestAd(Context context, MCListenerObj.IObjResListener listener) {
        RecommendAction.requestAd(context,listener);
    }

}
