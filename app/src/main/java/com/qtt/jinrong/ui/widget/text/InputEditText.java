package com.qtt.jinrong.ui.widget.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.qtt.jinrong.R;

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

        boolean isNumberDecimal = a.getBoolean(R.styleable.InputEditText_input_numberDecimal,false);
        if(isNumberDecimal) mEditText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);

        int limit = a.getInt(R.styleable.InputEditText_input_limit, 0);
        if(limit > 0) mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(limit)});

        boolean isNotBg = a.getBoolean(R.styleable.InputEditText_input_background_null, false);
        if(isNotBg) {
            mEditText.setBackgroundDrawable(null);
        }

        int grivaty = a.getInt(R.styleable.InputEditText_input_gravity, 0);
        if(grivaty > 0) {
            //// TODO: 16/3/3
            //1 LEFT,2 RIGHT
            //11 LEFT/CENTER_VERTICAL 21 RIGHT/CENTER_VERTICAL 12 LEFT/CENTER_HORIZONTAL 22 RIGHT/CENTER_HORIZONTAL
            if(grivaty == 21) {
                mEditText.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) btnClear.getLayoutParams();
                params = new RelativeLayout.LayoutParams(params.width,params.height);
                params.addRule(RelativeLayout.CENTER_VERTICAL);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                btnClear.setLayoutParams(params);
            }
        }

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
                    if(btnClear.getVisibility() == View.VISIBLE) {
                        btnClear.setVisibility(View.GONE);
                        mEditText.setPadding(mEditText.getPaddingLeft(), mEditText.getPaddingTop(), 0, mEditText.getPaddingBottom());
                    }
                } else {
                    if(btnClear.getVisibility() == View.GONE) {
                        btnClear.setVisibility(View.VISIBLE);
                        mEditText.setPadding(mEditText.getPaddingLeft(),mEditText.getPaddingTop(),getResources().getDimensionPixelOffset(R.dimen.margin_step_30),mEditText.getPaddingBottom());
                    }

                }
            }
        });

        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
                btnClear.setVisibility(View.GONE);
                mEditText.setPadding(mEditText.getPaddingLeft(),mEditText.getPaddingTop(),0,mEditText.getPaddingBottom());
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
