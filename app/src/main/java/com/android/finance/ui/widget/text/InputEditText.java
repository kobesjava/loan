package com.android.finance.ui.widget.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.android.finance.R;

/**
 * Created by yanxin on 16/2/24.
 */
public class InputEditText extends FrameLayout {

    private EditText mEditText;
    private View btnClear;

    public InputEditText(Context context) {
        super(context);
    }

    public InputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.input_edittext, null);
        mEditText = (EditText) view.findViewById(R.id.editText);
        btnClear = view.findViewById(R.id.btnClear);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InputEditText);

        String hint = a.getString(R.styleable.InputEditText_input_hint);
        mEditText.setHint(hint);

        boolean isPwd = a.getBoolean(R.styleable.InputEditText_input_pwd,false);
        if(isPwd) mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());

        boolean isNumber = a.getBoolean(R.styleable.InputEditText_input_number,false);
        if(isNumber) mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

        int limit = a.getInt(R.styleable.InputEditText_input_limit, 0);
        if(limit > 0) mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(limit)});

        a.recycle();

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s.toString())) {
                    if(btnClear.getVisibility() == View.VISIBLE) btnClear.setVisibility(View.GONE);
                } else {
                    if(btnClear.getVisibility() == View.GONE) btnClear.setVisibility(View.VISIBLE);
                }
            }
        });

        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
                btnClear.setVisibility(View.GONE);
            }
        });

        addView(view);
    }

    public String getString() {
        return mEditText.getText().toString();
    }

    public void setInputType(int inputType) {
        mEditText.setInputType(inputType);
    }

}
