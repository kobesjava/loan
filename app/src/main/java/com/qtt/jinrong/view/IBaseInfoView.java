package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.account.BaseInfoModel;
import com.qtt.jinrong.bean.account.BaseInfoResponse;
import com.qtt.jinrong.bean.account.BaseInfoSaveRequest;

/**
 * Created by yanxin on 16/3/8.
 */
public interface IBaseInfoView extends IView {

    /**
     * 获取信息返回处理
     */
    void onRequest(BaseInfoModel model);

    /**
     * 提交保存返回处理
     */
    void onSaveSuccess();

    BaseInfoSaveRequest getSaveRequest();

}
