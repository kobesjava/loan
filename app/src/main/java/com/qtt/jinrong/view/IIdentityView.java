package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.account.DemandSaveRequest;
import com.qtt.jinrong.bean.account.IdentityModel;
import com.qtt.jinrong.bean.account.IdentitySaveRequest;

/**
 * Created by yanxin on 16/3/8.
 */
public interface IIdentityView extends IView {

    /**
     * 获取信息返回处理
     */
    void onRequest(IdentityModel model);

    /**
     * 获取保存身份信息request
     * @return
     */
    IdentitySaveRequest getSaveRequest();

    /**
     * 提交保存返回处理
     */
    void onSaveSuccess();

}
