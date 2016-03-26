package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.credit.CreditListRequest;
import com.qtt.jinrong.bean.credit.CreditModel;

import java.util.List;

/**
 * Created by yanxin on 16/3/7.
 */
public interface ICreditListView extends IView {

    /**
     * 获取request数据
     * @return
     */
    CreditListRequest getRequest();

    /**
     * 获取列表数据返回处理
     */
    void onRequest(List<CreditModel> list);

}
