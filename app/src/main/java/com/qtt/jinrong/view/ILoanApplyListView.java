package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.loan.LoanApplyListRequest;
import com.qtt.jinrong.bean.loan.LoanApplyModel;

import java.util.List;

/**
 * Created by yanxin on 16/3/17.
 */
public interface ILoanApplyListView extends IView {

    /**
     * 获取请求request
     * @return
     */
    LoanApplyListRequest getRequest();

    /**
     * 获取列表成功返回处理
     * @param models
     */
    void onRequest(List<LoanApplyModel> models);

    /**
     * 获取列表失败处理
     */
    void onRequestFail();

}
