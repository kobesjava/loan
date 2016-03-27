package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.credit.CreditApplyListRequest;
import com.qtt.jinrong.bean.credit.CreditApplyModel;

import java.util.List;

/**
 * Created by yanxin on 16/3/17.
 */
public interface ICreditApplyListView extends IView {

    /**
     * 获取请求request
     * @return
     */
    CreditApplyListRequest getRequest();

    /**
     * 获取列表成功返回处理
     * @param models
     */
    void onRequest(List<CreditApplyModel> models);

    /**
     * 获取列表失败处理
     */
    void onRequestFail();

}
