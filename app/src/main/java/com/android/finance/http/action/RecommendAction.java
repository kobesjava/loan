package com.android.finance.http.action;

import android.content.Context;

import com.android.finance.bean.recommend.AdResponse;
import com.android.finance.http.Api;
import com.finance.framework.http.MCListenerObj;

import java.util.HashMap;

import static com.android.finance.http.action.IWBaseReqsAction.getPostReq7HeardInfo;

/**
 * Created by yanxin on 16/2/23.
 */
public class RecommendAction {

    public static void requestAd(Context context,MCListenerObj.IObjResListener<AdResponse> listener) {
        getPostReq7HeardInfo(context, Api.AD,new HashMap<String, Object>(),listener,AdResponse.class);
    }

}
