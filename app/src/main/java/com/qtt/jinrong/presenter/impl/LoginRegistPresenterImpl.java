package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.user.UserCodeRequest;
import com.qtt.jinrong.bean.user.UserInfoResponse;
import com.qtt.jinrong.bean.user.UserLoginCodeRequest;
import com.qtt.jinrong.bean.user.UserLoginPwdRequest;
import com.qtt.jinrong.bean.user.UserRegistRequest;
import com.qtt.jinrong.bean.user.UserResetPwdRequest;
import com.qtt.jinrong.http.action.UserReqsAction;
import com.qtt.jinrong.model.ILoginRegBS;
import com.qtt.jinrong.model.impl.LoginRegBSImpl;
import com.qtt.jinrong.presenter.ILoginRegistPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IForgetPwdView;
import com.qtt.jinrong.view.ILoginView;
import com.qtt.jinrong.view.IRegistView;

/**
 * Created by yanxin on 16/3/7.
 */
public class LoginRegistPresenterImpl implements ILoginRegistPresenter {

    ILoginView mView;
    IRegistView mRegView;
    IForgetPwdView mForgetView;
    ILoginRegBS mBs;

    public LoginRegistPresenterImpl(ILoginView mView) {
        this.mView = mView;
        this.mBs = new LoginRegBSImpl();
    }

    public LoginRegistPresenterImpl(IRegistView mView) {
        this.mRegView = mView;
        this.mBs = new LoginRegBSImpl();
    }

    public LoginRegistPresenterImpl(IForgetPwdView mView) {
        this.mForgetView = mView;
        this.mBs = new LoginRegBSImpl();
    }

    @Override
    public void loginPwd() {
        mView.showLoading();
        UserLoginPwdRequest request = new UserLoginPwdRequest();
        request.setCell(mView.getPhone());
        request.setPwd(mView.getPwd());
        mBs.loginPwd(mView.getContext(), request, new MCListenerObj.IObjResListener<UserInfoResponse>() {
            @Override
            public void onSuccess(UserInfoResponse response, String url) {
                mView.hideLoading();
                if(response.isSuccess()) {
                    mView.onLogin(response.getData());
                } else {
                    ToastUtil.showShortToast(response.getMessage());
                }
            }

            @Override
            public void onFail(Exception exception, String url) {
                mView.hideLoading();
            }
        });
    }

    @Override
    public void loginCode() {
        mView.showLoading();
        UserLoginCodeRequest request = new UserLoginCodeRequest();
        request.setCell(mView.getPhone());
        request.setVerifyCode(mView.getCode());
        mBs.loginCode(mView.getContext(), request, new MCListenerObj.IObjResListener<UserInfoResponse>() {
            @Override
            public void onSuccess(UserInfoResponse response, String url) {
                mView.hideLoading();
                if (response.isSuccess()) {
                    mView.onLogin(response.getData());
                } else {
                    ToastUtil.showShortToast(response.getMessage());
                }
            }

            @Override
            public void onFail(Exception exception, String url) {
                mView.hideLoading();
            }
        });
    }

    @Override
    public void requestCode() {
        UserCodeRequest request = new UserCodeRequest();
        if(mRegView != null) {
            request.setCell(mRegView.getPhone());
            mBs.requestCode(mRegView.getContext(), request, new MCListenerObj.IObjResListener<Response>() {
                @Override
                public void onSuccess(Response response, String url) {
                    if(!response.isSuccess()) {
                        mRegView.onRequestCode(false);
                        ToastUtil.showShortToast(response.getMessage());
                    }
                }

                @Override
                public void onFail(Exception exception, String url) {
                    mRegView.onRequestCode(false);
                }
            });
        } else if(mView != null) {
            request.setCell(mView.getPhone());
            mBs.requestCode(mView.getContext(), request, new MCListenerObj.IObjResListener<Response>() {
                @Override
                public void onSuccess(Response response, String url) {
                    if(!response.isSuccess()) {
                        mView.onRequestCode(false);
                        ToastUtil.showShortToast(response.getMessage());
                    }
                }

                @Override
                public void onFail(Exception exception, String url) {
                    mView.onRequestCode(false);
                }
            });
        } else if(mForgetView != null) {
            request.setCell(mForgetView.getPhone());
            mBs.requestCode(mForgetView.getContext(), request, new MCListenerObj.IObjResListener<Response>() {
                @Override
                public void onSuccess(Response response, String url) {
                    if(!response.isSuccess()) {
                        mForgetView.onRequestCode(false);
                        ToastUtil.showShortToast(response.getMessage());
                    }
                }

                @Override
                public void onFail(Exception exception, String url) {
                    mForgetView.onRequestCode(false);
                }
            });
        }
    }

    @Override
    public void forgetPwd() {
        mForgetView.showLoading();
        UserResetPwdRequest request = new UserResetPwdRequest();
        request.setCell(mForgetView.getPhone());
        request.setVerifyCode(mForgetView.getCode());
        request.setPwd(mForgetView.getPwd());
        mBs.forgetPwd(mForgetView.getContext(), request, new MCListenerObj.IObjResListener<Response>() {
            @Override
            public void onSuccess(Response response, String url) {
                mForgetView.hideLoading();
                if(!response.isSuccess()) {
                    ToastUtil.showShortToast(response.getMessage());
                } else {
                    mForgetView.onResetPwdSucess();
                }
            }

            @Override
            public void onFail(Exception exception, String url) {
                mForgetView.hideLoading();
            }
        });
    }

    @Override
    public void regist() {
        final UserRegistRequest request = new UserRegistRequest();
        request.setCell(mRegView.getPhone());
        request.setPwd(mRegView.getPwd());
        request.setVerifyCode(mRegView.getCode());
        request.setGender(mRegView.getGender());
        request.setNickName(mRegView.getNickname());
        UserReqsAction.regist(mRegView.getContext(), request, new MCListenerObj.IObjResListener<Response>() {
            @Override
            public void onSuccess(Response response, String url) {
                mRegView.hideLoading();
                if(response.isSuccess()) {
                    mRegView.onRegist();
                } else {
                    ToastUtil.showShortToast(response.getMessage());
                }
            }

            @Override
            public void onFail(Exception exception, String url) {
                mRegView.hideLoading();
            }
        });
    }
}
