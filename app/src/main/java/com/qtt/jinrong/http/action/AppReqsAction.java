package com.qtt.jinrong.http.action;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.bean.app.CheckUpgradeResponse;
import com.qtt.jinrong.http.Api;

import static com.qtt.jinrong.http.action.IWBaseReqsAction.getPostReq7HeardInfo;

/**
 * Created by yanxin on 16/3/17.
 */
public class AppReqsAction {

    /**
     * 检查版本更新
     * @param context
     * @param request
     * @param listener
     */
    public static void checkUpgrade(Context context,IRequest request,MCListenerObj.IObjResListener<CheckUpgradeResponse> listener) {
        getPostReq7HeardInfo(context, Api.URL_APP_UPGRADE,request.getParams(),listener,CheckUpgradeResponse.class);
    }

}
