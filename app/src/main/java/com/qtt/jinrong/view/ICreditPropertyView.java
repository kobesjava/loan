package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.account.CreditPropertyModel;
import com.qtt.jinrong.bean.account.CreditPropertySaveRequest;

/**
 * Created by yanxin on 16/3/8.
 */
public interface ICreditPropertyView extends IView {

    /**
     * 获取信息返回处理
     */
    void onRequest(CreditPropertyModel model);

    /**
     * 提交保存返回处理
     */
    void onSaveSuccess();

    CreditPropertySaveRequest getSaveRequest();

}
