package com.android.finance.ui.widget.radio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.android.finance.util.ScreenUtil;
import com.finance.framework.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blue on 15/7/28.
 * 边框的RadioGroup
 * <p/>
 * 使用此类：需要设置
 * setmRowCount setmFrameStrs 这两个参数必须设置
 */
public class BorderRadioGruop extends LinearLayout {
    //每行显示几个
    private int mRowCount;
    private int mTextSize;
    private int mLeftStyle;
    private int mTextStyle;
    private int mChildHight;
    private int mRightStyle;
    private int mShapeStyle;
    private int mMiddleStyle;
    private float mSpacingTop;
    private List<String> mFrameStrs;
    public static int mChildId = 8000100;
    //点击选择的id
    private int mCheckedId = -1;
    private String mCheckedStr; //选中控件的内容
    private int mCheckedPosition; //选中控件的位置
    private List<BorderRadioButton> mViews;
    private BorderRadioButton mCheckedView; //选中的控件
    private BorderRadioAdapter mBorderRadioAdapter;
    private CheckedStateTracker mCheckedStateTracker;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    private boolean isEnabled = true; //设置时候可以编辑状态


    public BorderRadioGruop(Context context) {
        super(context);
        init();
    }

    public BorderRadioGruop(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BorderRadioGruop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    public BorderRadioGruop(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        mFrameStrs = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    /**
     * 设置Adapter
     *
     * @param adapter
     */
    public void setAdapter(BorderRadioAdapter adapter) {
        mCheckedStateTracker = new CheckedStateTracker();
        mBorderRadioAdapter = adapter;
        notifyDataSetChanged();
    }

    private void notifyDataSetChanged() {
        mViews.clear();
        clearCheck();
        removeAllViews();
        updateAdapterData();
        createContentView();
    }

    /**
     * 获取当前选择的View
     *
     * @return
     */
    public BorderRadioButton getCheckedView() {
        return mCheckedView;
    }

    /**
     * 刷新数据源
     */
    private void updateAdapterData() {
        mRowCount = mBorderRadioAdapter.getRowMaxCount();
        mTextSize = mBorderRadioAdapter.getTextSize();
        mTextStyle = mBorderRadioAdapter.getTextStyle();
        mLeftStyle = mBorderRadioAdapter.getLeftStyle();
        mRightStyle = mBorderRadioAdapter.getRightStyle();
        mShapeStyle = mBorderRadioAdapter.getShapeStyle();
        mChildHight = mBorderRadioAdapter.getChildHight();
        mMiddleStyle = mBorderRadioAdapter.getMiddleStyle();
        mSpacingTop = mBorderRadioAdapter.getSpacingTop();
    }

    /**
     * 根据字符串 创建内容View
     */
    private void createContentView() {
        //正式代码
        int frameStrsCount = mBorderRadioAdapter.getCount(); //3
        if (frameStrsCount == 0) return;
        //计算行数
        int rowCount = (int) Math.ceil(frameStrsCount / (mRowCount * 1.0));
        //设置比重总数
        setWeightSum(mRowCount);

        //单行时候执行
        if (rowCount == 1) {
            setOrientation(HORIZONTAL);
            for (int rowIndex = 0; rowIndex < frameStrsCount; rowIndex++) {
                String str = mBorderRadioAdapter.getItemData(rowIndex);
                addView(createChildView(str, rowIndex, frameStrsCount, rowIndex));
            }
        } else { //多行时候执行
            setOrientation(VERTICAL);
            //生成对应的布局层，
            for (int i = 0; i < rowCount; i++) {
                //如果只有一行的时候就不使用嵌套布局
                LinearLayout linearLayout = new LinearLayout(getContext());
                LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                //只有第二行以及以后才会增加行距
                if (i > 0) {
                    layoutParams.setMargins(0, ScreenUtil.dipToPx(getContext(), mSpacingTop), 0, 0);
                }
                linearLayout.setWeightSum(mRowCount);
                linearLayout.setLayoutParams(layoutParams);
                addView(linearLayout);
                //这里要的室 当前行显示的数量
                int currentRowCount = frameStrsCount - ((i + 1) * mRowCount) > 0 ? mRowCount : frameStrsCount - i * mRowCount;
                //这里主要判断最后一个 特殊情况 最后一个
                for (int rowIndex = 0; rowIndex < currentRowCount; rowIndex++) {
                    int position = (i * mRowCount) + rowIndex;
                    String str = mBorderRadioAdapter.getItemData(position);
                    //如果字符串不为空 创建字符串
                    linearLayout.addView(createChildView(str, rowIndex, currentRowCount, position));
                }
            }
        }
    }

    private LayoutParams getChildLayoutParams() {
        LayoutParams childLayoutParams = new LayoutParams(0, ScreenUtil.dipToPx(getContext(), mChildHight));
        childLayoutParams.weight = 1;
        return childLayoutParams;
    }


    /**
     * 创建子类ViewGroup
     *
     * @param str
     * @return
     */
    private View createChildView(String str, int rowPosition, int rowCount, int position) {
        BorderRadioButton borderRadioButton = new BorderRadioButton(getContext());
        borderRadioButton.setLayoutParams(getChildLayoutParams());
        borderRadioButton.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        borderRadioButton.setOnCheckedChangeListener(mCheckedStateTracker);
        borderRadioButton.setText(str);
        borderRadioButton.setTextColor(getResources().getColorStateList(mTextStyle));
        borderRadioButton.setTextSize(mTextSize);
        borderRadioButton.setId(mChildId++);
        borderRadioButton.setChecked(false);
        borderRadioButton.setTag(position);


        borderRadioButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**
         * 设置样式
         */
        int resourceId = -1;

        //判断一行的个数 是否为1个
        if (rowCount == 1) {
            resourceId = mShapeStyle;
            //判断一行的个数是否为2个
        } else if (rowCount == 2) {
            if (rowPosition == 0) {
                resourceId = mLeftStyle;
            } else {
                resourceId = mRightStyle;
                setMarginsF0d5(borderRadioButton);
            }
            //判断如果一行的个数 等于大于三
        } else if (rowCount >= 3) {
            //判断是否是最左边的
            if (rowPosition == 0) {
                resourceId = mLeftStyle;
            } else if (rowPosition != rowCount) { //判断是否是中间的
                resourceId = mMiddleStyle;
                setMarginsF0d5(borderRadioButton);
            } else { //判断是否是最右边的
                resourceId = mRightStyle;
            }
        }

        if (resourceId != -1) {
            borderRadioButton.setBackgroundResource(resourceId);
        }

        mViews.add(borderRadioButton);
        return borderRadioButton;
    }

    /**
     * 设置margins为-0.5 并且weight 为1
     */
    private void setMarginsF0d5(View view) {
        LayoutParams layoutParams = new LayoutParams(0, ScreenUtil.dipToPx(getContext(), mChildHight));
        layoutParams.weight = 1;
//        layoutParams.setMargins(DensityUtil.dipToPx(getContext(), (float) -0.5), 0, 0, 0);
        view.setLayoutParams(layoutParams);
    }


    public void clearCheck() {
        check(-1);
    }

    /**
     * 获取选中控件的ID
     *
     * @return
     */
    public int getCheckedRadioButtonId() {
        return mCheckedId;
    }

    /**
     * 设置正在选中空间的选中状态，如果想要清除控件所有的选中状态 只需要 设置 id == -1就可以了。
     *
     * @param id the unique id of the radio button to select in this group
     * @see #getCheckedRadioButtonId()
     * @see #clearCheck()
     */
    public void check(int id) {
        // don't even bother

        //如果再次设置选中空间则直接返回
        if (id != -1 && (id == mCheckedId)) {
            return;
        }

        //取消选中按钮
        if (mCheckedId != -1) {
            setCheckedStateForView(mCheckedId, false);
        }

        //设置选中控件状态
        if (id != -1) {
            setCheckedStateForView(id, true);
        }

        if (id == -1) {
            mCheckedId = -1;
            mCheckedStr = null;
            mCheckedView = null;
            mCheckedPosition = -1;
        }

        //通知监视者
        if (id != -1)
            setCheckedId(id);
    }

    /**
     * 设置对应的position
     *
     * @param position
     */
    public void checkPosition(int position) {
        if (position >= 0 && mViews.size() > position) {
            check(mViews.get(position).getId());
        } else {
            LogUtil.e("BorderRadioGroup", "选中下标越界，请重新选择");
        }
    }

    public int getCheckedId() {
        return mCheckedId;
    }

    public int getCheckedPosition() {
        return mCheckedPosition;
    }


    private void setCheckedId(int id) {
        mCheckedId = id;
        mCheckedView = (BorderRadioButton) findViewById(id);

        if (mCheckedView != null) {
            mCheckedStr = mCheckedView.getText().toString();
            mCheckedPosition = (int) mCheckedView.getTag();
        } else {
            mCheckedStr = "";
            mCheckedPosition = 0;
        }
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(this, mCheckedId, mCheckedStr, mCheckedPosition);
        }
    }


    /**
     * 设置选中状态，
     *
     * @param viewId
     * @param checked
     */
    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView != null && checkedView instanceof BorderRadioButton) {
            ((BorderRadioButton) checkedView).setChecked(checked);
        }
    }

    /**
     * <p>Register a callback to be invoked when the checked radio button
     * changes in this group.</p>
     *
     * @param listener the callback to call on checked state change
     */
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    private class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (mCheckedId != -1) {
                setCheckedStateForView(mCheckedId, false);
            }

            int id = buttonView.getId();
            //如果再次设置选中空间则直接返回
            if (id != -1 && (id == mCheckedId)) {
                return;
            }
            //取消选中按钮
            if (mCheckedId != -1) {
                setCheckedStateForView(mCheckedId, false);
            }
            //设置选中控件状态
            if (id != -1) {
                setCheckedStateForView(id, true);
            }

            setCheckedId(id);
        }
    }

    /**
     * <p>Interface definition for a callback to be invoked when the checked
     * radio button changed in this group.</p>
     */
    public interface OnCheckedChangeListener {
        /**
         * 当选择改变时候会进行回调
         *
         * @param group
         * @param checkedId       当前选中的id
         * @param checkedStr      当前选中控件的文字
         * @param checkedPosition 当前选中控件的position
         */
        public void onCheckedChanged(BorderRadioGruop group, int checkedId, String checkedStr, int checkedPosition);
    }


    /**
     * 设置时候可以编辑状态
     *
     * @param b
     */
    public void setIsEnabled(boolean b) {
        for (BorderRadioButton button : mViews) {
            button.setIsToggle(b);
        }
    }


}
