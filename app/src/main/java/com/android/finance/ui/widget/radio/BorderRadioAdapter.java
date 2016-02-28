package com.android.finance.ui.widget.radio;

import com.android.finance.R;

import java.util.List;

/**
 * Created by blue on 15/7/29.
 */
public abstract class BorderRadioAdapter {

    //默认行间距
    private final int mDefalutSpacingTop = 20;
    private final int mDefalutShapeStyle = R.drawable.selector_frameradio;
    private final int mDefalutLeftStyle = R.drawable.selector_frameradio_left;
    private final int mDefalutMiddleStyle = R.drawable.selector_frameradio_middle;
    private final int mDefalutRightStyle = R.drawable.selector_frameradio_right;
    private final int mDefalutTextStyle = R.color.selector_frameradio_text;
    //子按钮高度
    private final int mDefalutChildHight = 40;
    //文字大小
    private int mDefalutTextSize = 14;


    public BorderRadioAdapter() {

    }

    public BorderRadioAdapter(int textSize) {
        mDefalutTextSize = textSize;
    }

    /**
     * 获取数据集中的一条
     *
     * @param position
     * @return
     */
    public abstract String getItemData(int position);

    /**
     * 设置每一行显示几个*
     */
    public abstract int getRowMaxCount();

    /**
     * 数据总数
     */
    public abstract int getCount();

    /**
     * 设置显示的数据
     *
     * @return
     */
    public List<String> getData() {
        return null;
    }

    /**
     * 获取默认行间距
     *
     * @return
     */
    public int getSpacingTop() {
        return mDefalutSpacingTop;
    }

    public int getChildHight() {
        return mDefalutChildHight;
    }

    /**
     * *设置样式 开始**
     */
    public int getShapeStyle() {
        return mDefalutShapeStyle;
    }

    public int getLeftStyle() {
        return mDefalutLeftStyle;
    }

    public int getMiddleStyle() {
        return mDefalutMiddleStyle;
    }

    public int getRightStyle() {
        return mDefalutRightStyle;
    }

    public int getTextStyle() {
        return mDefalutTextStyle;
    }

    public int getTextSize() {
        return mDefalutTextSize;
    }
    /**
     * 设置样式结束
     */


}
