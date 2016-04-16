package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.account.DataResponse;
import com.qtt.jinrong.bean.account.DataUploadRequest;

import java.util.List;

/**
 * Created by yanxin on 16/3/31.
 */
public interface IDataUploadVIew extends IView{

    /**
     * 获取资料上传request
     * @return
     */
    List<DataUploadRequest> getRequest();

    /**
     * 获取资料信息返回处理
     */
    void onRequest(DataResponse response);

    /**
     * 资料上传成功返回处理
     */
    void onUpload(DataUploadRequest request);

}
