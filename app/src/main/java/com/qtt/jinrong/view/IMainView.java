package com.qtt.jinrong.view;

import com.qtt.jinrong.bean.app.UpgradeModel;

/**
 * Created by yanxin on 16/2/23.
 */
public interface IMainView extends IView{

    /**
     * 升级
     * @param model
     */
    void upgrade(UpgradeModel model);

}
