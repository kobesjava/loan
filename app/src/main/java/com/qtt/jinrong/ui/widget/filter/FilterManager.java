package com.qtt.jinrong.ui.widget.filter;

/**
 * Created by yanxin on 16/2/25.
 */
public class FilterManager {

    private FilterSelect mFilterView;

    private BaseFilterAdapter mAdapter;

    public void setAdapter(BaseFilterAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public void setComponents(FilterSelect mFilterView,BaseFilterAdapter mAdapter) {
        this.mFilterView = mFilterView;
        this.mAdapter = mAdapter;
        mFilterView.setAdapter(mAdapter);
    }

    public void setSelect(int position,int index,String defaultStr) {
        mFilterView.setSelect(position, index, defaultStr);
    }

}
