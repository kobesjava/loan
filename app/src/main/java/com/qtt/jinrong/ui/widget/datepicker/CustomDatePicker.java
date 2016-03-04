package com.qtt.jinrong.ui.widget.datepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;


import com.qtt.jinrong.R;
import com.qtt.framework.util.LogUtil;

import java.lang.reflect.Field;

/**
 * A subclass of {@link DatePicker} that uses
 * reflection to allow for customization of the default blue
 * dividers.
 *
 * @author jjobes
 */
public class CustomDatePicker extends DatePicker {
    private static final String TAG = "CustomDatePicker";
    private NumberPicker dayPicker = null;

    public CustomDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        Class<?> idClass = null;
        Class<?> numberPickerClass = null;
        Field selectionDividerField = null;
        Field monthField = null;
        Field dayField = null;
        Field yearField = null;

        NumberPicker dayNumberPicker = null;
        NumberPicker yearNumberPicker = null;
        NumberPicker monthNumberPicker = null;


        try {
            // Create an instance of the id class
            idClass = Class.forName("com.android.internal.R$id");

            // Get the fields tha t store the resource IDs for the month, day and year NumberPickers
            monthField = idClass.getField("month");
            dayField = idClass.getField("day");
            yearField = idClass.getField("year");

            // Use the resource IDs to get references to the month, day and year NumberPickers
            monthNumberPicker = (NumberPicker) findViewById(monthField.getInt(null));
            dayNumberPicker = (NumberPicker) findViewById(dayField.getInt(null));
            yearNumberPicker = (NumberPicker) findViewById(yearField.getInt(null));

            dayPicker = dayNumberPicker;

            numberPickerClass = Class.forName("android.widget.NumberPicker");

            // Set the value of the mSelectionDivider field in the month, day and year NumberPickers
            // to refer to our custom drawables
            selectionDividerField = numberPickerClass.getDeclaredField("mSelectionDivider");
            selectionDividerField.setAccessible(true);
            selectionDividerField.set(monthNumberPicker, getResources().getDrawable(R.drawable.selection_divider));
            selectionDividerField.set(dayNumberPicker, getResources().getDrawable(R.drawable.selection_divider));
            selectionDividerField.set(yearNumberPicker, getResources().getDrawable(R.drawable.selection_divider));
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "ClassNotFoundException in CustomDatePicker", e);
        } catch (NoSuchFieldException e) {
            Log.e(TAG, "NoSuchFieldException in CustomDatePicker", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "IllegalAccessException in CustomDatePicker", e);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "IllegalArgumentException in CustomDatePicker", e);
        }
    }

    public void hideDay() {
        dayPicker.setVisibility(View.GONE);
    }

    public void setMonth() {
        try {
            Class<?> idClass = null;
            Field field = null;
           /*  = Class.forName("android.widget.DatePicker");
             = idClass.getField("mDelegate");
            field.setAccessible(true);
           Object obj =  field.get(this);

             idClass = Class.forName("android.widget.DatePicker$DatePickerSpinnerDelegate");
            field = idClass.getDeclaredField("mShortMonths");
            field.setAccessible(true);
            field.set(obj,new String[]{"2","3","4","6"});*/


            idClass = Class.forName("android.widget.DatePicker");
            field = idClass.getDeclaredField("mShortMonths");
            field.setAccessible(true);
            field.set(this, new String[]{"2", "3", "4", "6"});

        } catch (Exception e) {
            LogUtil.d(TAG, "异常" + e);
        }
    }

    public void setMax() {
        dayPicker.setMaxValue(4);
    }


}
