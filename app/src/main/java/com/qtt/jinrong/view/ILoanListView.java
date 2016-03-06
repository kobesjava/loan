package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.loan.LoanListRequest;
import com.qtt.jinrong.bean.loan.LoanModel;

import java.util.List;

/**
 * Created by yanxin on 16/3/7.
 */
public interface ILoanListView extends IView {

    /**
     * 获取request数据
     * @return
     */
    LoanListRequest getRequest();

    /**
     * 获取列表数据返回处理
     */
    void onRequest(List<LoanModel> list);

}
