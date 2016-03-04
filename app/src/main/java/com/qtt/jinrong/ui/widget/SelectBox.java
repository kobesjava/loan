package com.qtt.jinrong.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qtt.jinrong.R;


/**
 * Created by yanxin on 2015/12/3.
 */
public class SelectBox extends FrameLayout {

    private RadioGroup  radioGroup;
    private RadioButton leftRadio;
    private RadioButton rightRadio;
    private SelectBoxListener mSelectBoxListener;

    public SelectBox(Context context) {
        super(context);
    }

    public SelectBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SelectBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context,AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.selectbox_layout, null);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SelectBox);

        String leftTitle = a.getString(R.styleable.SelectBox_selectbox_left_title);
        Drawable leftBg;
        if(a.hasValue(R.styleable.SelectBox_selectbox_left_background)) {
            leftBg = a.getDrawable(R.styleable.SelectBox_selectbox_left_background);
        } else {
            leftBg = getResources().getDrawable(R.drawable.selector_radiogroup_left);
        }

        String rightTitle = a.getString(R.styleable.SelectBox_selectbox_right_title);
        Drawable rightBg;
        if(a.hasValue(R.styleable.SelectBox_selectbox_right_background)) {
            rightBg = a.getDrawable(R.styleable.SelectBox_selectbox_right_background);
        } else {
            rightBg = getResources().getDrawable(R.drawable.selector_radiogroup_right);
        }

        a.recycle();

        radioGroup = (RadioGroup)view.findViewById(R.id.radioGroup);
        leftRadio = (RadioButton)view.findViewById(R.id.leftBtn);
        rightRadio = (RadioButton)view.findViewById(R.id.rightBtn);

        leftRadio.setText(leftTitle);
        leftRadio.setBackgroundDrawable(leftBg);

        rightRadio.setText(rightTitle);
        rightRadio.setBackgroundDrawable(rightBg);

        leftRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (mSelectBoxListener != null) mSelectBoxListener.selectLeft();
                }

            }
        });

        rightRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (mSelectBoxListener != null) mSelectBoxListener.selectRight();
                }
            }
        });

        addView(view);
    }

    public void checkLeft() {
        if(!leftRadio.isChecked()) {
            radioGroup.check(R.id.leftBtn);
        }
    }

    public void checkRight() {
        if(!rightRadio.isChecked()) {
            radioGroup.check(R.id.rightBtn);
        }
    }

    public void setSelectBoxListner(SelectBoxListener selectBoxListner) {
        this.mSelectBoxListener = selectBoxListner;
    }

    public interface SelectBoxListener {
        void selectLeft();
        void selectRight();
    }

}
