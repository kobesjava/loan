package com.android.finance.model.impl;

import android.content.Context;

import com.android.finance.http.action.RecommendAction;
import com.android.finance.model.IRecommendBS;
import com.finance.framework.http.MCListenerObj;

/**
 * Created by yanxin on 16/2/23.
 */
public class RecommendBSImpl implements IRecommendBS {

    @Override
    public void requestAd(Context context, MCListenerObj.IObjResListener listener) {
        RecommendAction.requestAd(context,listener);
    }

}
