package com.qtt.jinrong.http.action;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.user.UserInfoResponse;
import com.qtt.jinrong.http.Api;

import static com.qtt.jinrong.http.action.IWBaseReqsAction.getPostReq7HeardInfo;

/**
 * Created by yanxin on 16/3/7.
 */
public class UserReqsAction {

    /**
     * 注册
     * @param context
     * @param request
     * @param listener
     */
    public static void regist(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener) {
        getPostReq7HeardInfo(context, Api.USER_REGIST,request.getParams(),listener,Response.class);
    }

    /**
     * 验证码登录
     * @param context
     * @param request
     * @param listener
     */
    public static void loginCode(Context context,IRequest request,MCListenerObj.IObjResListener<UserInfoResponse> listener) {
        getPostReq7HeardInfo(context, Api.USER_LOGIN_CODE,request.getParams(),listener,UserInfoResponse.class);
    }

    /**
     * 密码登录
     * @param context
     * @param request
     * @param listener
     */
    public static void loginPwd(Context context,IRequest request,MCListenerObj.IObjResListener<UserInfoResponse> listener) {
        getPostReq7HeardInfo(context, Api.USER_LOGIN_PWD,request.getParams(),listener,UserInfoResponse.class);
    }

    /**
     * 请求发送验证码
     * @param context
     * @param request
     * @param listener
     */
    public static void requestCode(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener) {
        getPostReq7HeardInfo(context, Api.USER_CODE,request.getParams(),listener,Response.class);
    }

    public static void resetPwd(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener) {
        getPostReq7HeardInfo(context, Api.USER_RESET_PWD,request.getParams(),listener,Response.class);
    }
}
