package com.qtt.jinrong.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qtt.jinrong.R;

/**
 * Created by yanxin on 16/2/26.
 */
public class TabIndictor extends FrameLayout {

    private RadioGroup radioGroup;
    private TextView mText;
    private BaseAdapter adapter;

    public TabIndictor(Context context) {
        super(context);
    }

    public TabIndictor(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.tab_indictor,null);

        radioGroup = (RadioGroup) view.findViewById(R.id.group);

        mText = (TextView) view.findViewById(R.id.text);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (adapter == null) return;

                if (checkedId == R.id.btn1) {
                    mText.setText(adapter.getString(0));
                } else if (checkedId == R.id.btn2) {
                    mText.setText(adapter.getString(1));
                } else if (checkedId == R.id.btn3) {
                    mText.setText(adapter.getString(2));
                }
            }
        });

        addView(view);
    }

    public void check() {
        radioGroup.check(R.id.btn1);
    }

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
    }

    public interface BaseAdapter {
        String getString(int position);
    }
}
