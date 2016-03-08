package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.account.OtherPropertyModel;
import com.qtt.jinrong.bean.account.OtherPropertySaveRequest;

/**
 * Created by yanxin on 16/3/8.
 */
public interface IOtherInfoView extends IView {

    /**
     * 获取信息返回处理
     */
    void onRequest(OtherPropertyModel model);

    /**
     * 提交保存返回处理
     */
    void onSaveSuccess();

    OtherPropertySaveRequest getSaveRequest();

}
