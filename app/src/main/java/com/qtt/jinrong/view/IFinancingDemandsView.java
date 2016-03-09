package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.account.FinancingDemandsModel;

/**
 * Created by yanxin on 16/3/8.
 */
public interface IFinancingDemandsView extends IView{

    /**
     * 获取所有状态返回处理
     */
    void onRequest(FinancingDemandsModel financingDemandsModel);

}
