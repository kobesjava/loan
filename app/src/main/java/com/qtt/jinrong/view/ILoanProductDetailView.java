package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.loan.LoanProductDetail;

/**
 * Created by yanxin on 16/3/8.
 */
public interface ILoanProductDetailView extends IView{

    String getProductId();

    void onRequest(LoanProductDetail detail);

}
