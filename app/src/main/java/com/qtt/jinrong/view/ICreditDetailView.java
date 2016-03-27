package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.credit.CreditDetailModel;

/**
 * Created by yanxin on 16/3/14.
 */
public interface ICreditDetailView extends IView {

    /**
     * 获取信用卡产品ID
     * @return
     */
    String getCreditId();

    /**
     * 详情返回处理
     */
    void onRequestDetail(CreditDetailModel model);

    /**
     * 申请返回出来
     */
    void onApply(Response response);

}
