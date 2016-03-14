package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.Response;

/**
 * Created by yanxin on 16/3/14.
 */
public interface ICreditApplyView extends IView {

    /**
     * 获取信用卡产品ID
     * @return
     */
    String getCreditId();

    /**
     * 申请返回出来
     */
    void onApply(Response response);

}
