package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.account.DemandSaveRequest;

/**
 * Created by yanxin on 16/3/8.
 */
public interface IIdentityView extends IView {

    /**
     * 获取信息返回处理
     */
    void onRequest();

    /**
     * 提交保存返回处理
     */
    void onSaveSuccess();

    DemandSaveRequest getSaveRequest();

}
