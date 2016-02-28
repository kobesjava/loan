package com.android.finance.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.finance.R;

/**
 * Created by yanxin on 16/2/26.
 */
public class TabIndictor extends FrameLayout implements View.OnClickListener {

    private TextView mText;
    private BaseAdapter adapter;

    public TabIndictor(Context context) {
        super(context);
    }

    public TabIndictor(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.tab_indictor,null);

        mText = (TextView) view.findViewById(R.id.text);

        view.findViewById(R.id.btn1).setOnClickListener(this);
        view.findViewById(R.id.btn2).setOnClickListener(this);
        view.findViewById(R.id.btn3).setOnClickListener(this);

        ((RadioButton)view.findViewById(R.id.btn1)).setChecked(true);

        addView(view);
    }


    @Override
    public void onClick(View v) {
        if(adapter == null) return;

        if(v.getId() == R.id.btn1) {
            mText.setText(adapter.getString(0));
        } else if(v.getId() == R.id.btn2) {
            mText.setText(adapter.getString(1));
        } else if(v.getId() == R.id.btn3) {
            mText.setText(adapter.getString(2));
        }
    }

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
    }

    public interface BaseAdapter {
        String getString(int position);
    }
}
