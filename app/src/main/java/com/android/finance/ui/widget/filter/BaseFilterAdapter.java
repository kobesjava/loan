package com.android.finance.ui.widget.filter;

import android.view.View;

/**
 * Created by yanxin on 16/2/25.
 */
public abstract class BaseFilterAdapter {

    /**
     * 重置
     * @param position 第几个筛选类型
     */
    protected abstract void reset(int position);

    /**
     * 获取总数
     * @param position 第几个筛选类型
     * @return
     */
    protected abstract int getCount(int position);

    /**
     * 获取内容对应的是第几个
     * @param position 第几个筛选类型
     * @param cont     筛选项显示内容
     * @return
     */
    protected abstract int getIndex(int position,String cont);

    /**
     * 获取筛选项 item view
     * @param position 第几个筛选类型
     * @param index    第几个筛选项
     * @param view     缓存的view
     * @param selected 是否之前被选中了
     * @return
     */
    protected abstract View getView(int position,int index,View view,boolean selected);

    /**
     * 获取筛项文字显示
     * @param position 第几个筛选类型
     * @param index    第几个筛选项
     * @return
     */
    protected abstract String getString(int position,int index);

    /**
     * 默认选中的筛选项位置
     * @param position 第几个筛选类型
     * @return
     */
    protected abstract int getDefaultIndex(int position);

    /**
     * 获取选中的个数
     * @param position 第几个筛选类型
     * @return
     */
    protected abstract int getSelected(int position);

}
