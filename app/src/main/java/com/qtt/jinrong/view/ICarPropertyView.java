package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.account.CarPropertyModel;
import com.qtt.jinrong.bean.account.CarPropertySaveRequest;

/**
 * Created by yanxin on 16/3/8.
 */
public interface ICarPropertyView extends IView {

    /**
     * 获取信息返回处理
     */
    void onRequest(CarPropertyModel model);

    /**
     * 提交保存返回处理
     */
    void onSaveSuccess();

    CarPropertySaveRequest getSaveRequest();

}
