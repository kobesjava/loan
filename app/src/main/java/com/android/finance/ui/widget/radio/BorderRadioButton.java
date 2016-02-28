package com.android.finance.ui.widget.radio;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/**
 * Created by blue on 15/7/28.
 */
public class BorderRadioButton extends CompoundButton {
    private boolean isToggle = true;

    public BorderRadioButton(Context context) {
        super(context);
    }

    public BorderRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BorderRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BorderRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void toggle() {
        if (!isToggle) return;

        if (!isChecked()) {
            super.toggle();
        }
    }

    public void setIsToggle(boolean b) {
        isToggle = b;
    }
}
