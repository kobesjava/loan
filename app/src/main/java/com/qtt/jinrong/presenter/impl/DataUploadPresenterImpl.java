package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.account.DataRequest;
import com.qtt.jinrong.bean.account.DataResponse;
import com.qtt.jinrong.bean.account.DataUploadRequest;
import com.qtt.jinrong.enums.DataTypeEnum;
import com.qtt.jinrong.model.IAccountBS;
import com.qtt.jinrong.model.impl.AccountBSImpl;
import com.qtt.jinrong.presenter.IDataUploadPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IDataUploadVIew;

import java.util.List;

/**
 * Created by yanxin on 16/4/16.
 */
public class DataUploadPresenterImpl implements IDataUploadPresenter {

    private IDataUploadVIew mView;
    private IAccountBS mBs;

    public DataUploadPresenterImpl(IDataUploadVIew mView) {
        this.mView = mView;
        this.mBs = new AccountBSImpl();
    }

    @Override
    public void request() {
        mView.showLoading();
        DataRequest request = new DataRequest();
        request.setUserId(mView.getUserId());
        mBs.requestData(mView.getContext(),request,new MCListenerObj.IObjResListener<DataResponse>(){
            @Override
            public void onSuccess(DataResponse response, String url) {
                mView.hideLoading();
                mView.onRequest(response);
            }

            @Override
            public void onFail(Exception exception, String url) {
                mView.hideLoading();
                mView.onRequest(null);
            }
        });
    }

    @Override
    public void uploadData() {
        final List<DataUploadRequest> requests = mView.getRequest();
        if(requests == null || requests.size() == 0) return;
        mView.showLoading();
        for(int i=0;i<requests.size();i++) {
            final DataUploadRequest request = requests.get(i);
            mBs.uploadData(mView.getContext(), request, new MCListenerObj.IObjResListener<Response>() {
                @Override
                public void onSuccess(Response response, String url) {
                    requests.remove(request);
                    if(!response.isSuccess()) {
                        DataTypeEnum dataTypeEnum = DataTypeEnum.find(request.getImgType());
                        ToastUtil.showShortToast("上传"+dataTypeEnum.name()+"失败");
                    } else {
                        mView.onUpload(request);
                    }
                    if(requests.size() == 0) {
                        mView.hideLoading();
                    }
                }

                @Override
                public void onFail(Exception exception, String url) {
                    requests.remove(request);
                    if(requests.size() == 0) {
                        mView.hideLoading();
                    }
                }
            });
        }
    }

}
