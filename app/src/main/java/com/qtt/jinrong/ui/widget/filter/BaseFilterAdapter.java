package com.qtt.jinrong.ui.widget.filter;

import android.view.View;

/**
 * Created by yanxin on 16/2/25.
 */
public abstract class BaseFilterAdapter {

    /**
     * 设置选中的
     * @param position
     * @param index
     */
    protected abstract void setSelect(int position,int index);

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
    protected abstract View getView(int position,int index,View view,int selected);

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

    /**
     * 具体显示第几个筛选选项
     * @param position 第几个筛选类型
     * @return
     */
    protected abstract boolean isVisible(int position);

    /**
     * 在最底部添加view
     * @param posion 第几个筛选类型
     * @return
     */
    protected abstract View getFootView(int posion);

    /**
     * 在头部添加view
     * @param posion 第几个筛选类型
     * @return
     */
    protected abstract View getHeadView(int posion);

}
