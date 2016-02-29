package com.android.finance.ui.widget.filter;

/**
 * Created by yanxin on 16/2/29.
 */
public interface FilterSelect {

    /**
     * 数据适配器
     * @param baseFilterSelectAdapter
     */
    void setAdapter(BaseFilterAdapter baseFilterSelectAdapter);

    /**
     * 设置选中项
     * @param position   第几个选项卡
     * @param index      选项卡下的第几个选项
     * @param defaultStr 默认填写在筛选项的值
     */
    void setSelect(int position,int index,String defaultStr);

    /**
     * 设置选中项
     * @param position 第几个选项卡
     * @param index    选项卡下的第几个选项
     * @param subIndex 子选项
     */
    void setSelect(int position,int index,int subIndex);

    /**
     * 重置内容
     * @param position
     */
    void reset(int position);

}
