package com.qtt.jinrong.ui.widget.filter;

import java.util.logging.Filter;

/**
 * Created by yanxin on 16/3/7.
 */
public abstract class AbstractSelect implements FilterSelect {

    protected SelectLisenter mSelectLisenter;

    @Override
    public abstract void setAdapter(BaseFilterAdapter baseFilterSelectAdapter);

    @Override
    public abstract void setSelect(int position, int index, String defaultStr);

    public void setSelectLisenter(SelectLisenter mSelectLisenter) {
        this.mSelectLisenter = mSelectLisenter;
    }

    public interface SelectLisenter {
        void onSelect(int position);
    }

}
