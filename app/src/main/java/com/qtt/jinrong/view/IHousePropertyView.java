package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.account.HousePropertyModel;
import com.qtt.jinrong.bean.account.HousePropertySaveRequest;

/**
 * Created by yanxin on 16/3/8.
 */
public interface IHousePropertyView extends IView {

    /**
     * 获取信息返回处理
     */
    void onRequest(HousePropertyModel model);

    /**
     * 提交保存返回处理
     */
    void onSaveSuccess();

    HousePropertySaveRequest getSaveRequest();

}
