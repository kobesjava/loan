package com.qtt.jinrong.ui.widget.text;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

import com.qtt.jinrong.R;

/**
 * Created by yanxin on 16/2/27.
 */
public class InputPwdEdit extends FrameLayout {

    private EditText mEditText;
    private ToggleButton mPwdShow;

    public InputPwdEdit(Context context) {
        super(context);
    }

    public InputPwdEdit(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.input_pwd, null);
        mEditText = (EditText) view.findViewById(R.id.editText);
        mPwdShow = (ToggleButton) view.findViewById(R.id.btnShowPwd);

        mPwdShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                else mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        addView(view);
    }

    public String getString() {
        return mEditText.getText().toString();
    }

}
