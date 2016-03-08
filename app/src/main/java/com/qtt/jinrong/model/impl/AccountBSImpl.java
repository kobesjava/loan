package com.qtt.jinrong.model.impl;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.account.BaseInfoResponse;
import com.qtt.jinrong.bean.account.CarPropertyResponse;
import com.qtt.jinrong.bean.account.CreditPropertyResponse;
import com.qtt.jinrong.bean.account.DemandsResponse;
import com.qtt.jinrong.bean.account.FinancingDemandsRequest;
import com.qtt.jinrong.bean.account.HousePropertyResponse;
import com.qtt.jinrong.bean.account.OtherPropertyRequest;
import com.qtt.jinrong.bean.account.OtherPropertyResponse;
import com.qtt.jinrong.bean.account.OtherPropertySaveRequest;
import com.qtt.jinrong.http.action.AccountReqsAction;
import com.qtt.jinrong.model.IAccountBS;

/**
 * Created by yanxin on 16/3/8.
 */
public class AccountBSImpl implements IAccountBS {

    @Override
    public void requestFinancingDemands(Context context, IRequest request, MCListenerObj.IObjResListener listener) {
        AccountReqsAction.requestFinancingDemands(context,request,listener);
    }

    @Override
    public void requestBaseInfo(Context context, IRequest request, MCListenerObj.IObjResListener<BaseInfoResponse> listener) {
        AccountReqsAction.requestBase(context,request,listener);
    }

    @Override
    public void saveBaseInfo(Context context, IRequest request, MCListenerObj.IObjResListener<Response> listener) {
        AccountReqsAction.saveBase(context,request,listener);
    }

    @Override
    public void requestOtherProperty(Context context, IRequest request, MCListenerObj.IObjResListener<OtherPropertyResponse> listener) {
        AccountReqsAction.requestOtherProperty(context,request,listener);
    }

    @Override
    public void saveOtherProperty(Context context, IRequest request, MCListenerObj.IObjResListener<Response> listener) {
        AccountReqsAction.saveOtherProperty(context,request,listener);
    }

    @Override
    public void requestHouseProperty(Context context, IRequest request, MCListenerObj.IObjResListener<HousePropertyResponse> listener) {
        AccountReqsAction.requestHouseProperty(context,request,listener);
    }

    @Override
    public void saveHouseProperty(Context context, IRequest request, MCListenerObj.IObjResListener<Response> listener) {
        AccountReqsAction.saveHouseProperty(context,request,listener);
    }

    @Override
    public void requestCarProperty(Context context, IRequest request, MCListenerObj.IObjResListener<CarPropertyResponse> listener) {
        AccountReqsAction.requestCarProperty(context,request,listener);
    }

    @Override
    public void saveCarProperty(Context context, IRequest request, MCListenerObj.IObjResListener<Response> listener) {
        AccountReqsAction.saveCarProperty(context,request,listener);
    }

    @Override
    public void requestCreditProperty(Context context, IRequest request, MCListenerObj.IObjResListener<CreditPropertyResponse> listener) {
        AccountReqsAction.requestCreditProperty(context,request,listener);
    }

    @Override
    public void saveCreditProperty(Context context, IRequest request, MCListenerObj.IObjResListener<Response> listener) {
        AccountReqsAction.saveCreditProperty(context,request,listener);
    }

    @Override
    public void requestDemands(Context context, IRequest request, MCListenerObj.IObjResListener<DemandsResponse> listener) {
        AccountReqsAction.requestDemands(context,request,listener);
    }

    @Override
    public void saveDemands(Context context, IRequest request, MCListenerObj.IObjResListener<Response> listener) {
        AccountReqsAction.saveDemands(context,request,listener);
    }
}
