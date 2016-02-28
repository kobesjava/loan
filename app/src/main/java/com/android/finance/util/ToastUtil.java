package com.android.finance.util;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.finance.R;
import com.android.finance.app.MyApplication;

/**
 * @author yanxin
 */
public class ToastUtil {

    public static Toast toast = null;

    private static Toast getToast(Context context) {
        if (toast == null) {
            toast = new Toast(context.getApplicationContext());
            LayoutInflater inflate = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflate.inflate(R.layout.toast_main, null);
            toast.setView(v);
            setToast(toast);
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        return toast;
    }

    private static void setToast(Toast toast2Set) {
        toast = toast2Set;
    }


    public static void show(Context context, String info) {
        if(TextUtils.isEmpty(info) || context == null) return;
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }

    public static void show(Context context, int info) {
        if(info<=0 || context == null) return;
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
        // Toast toast = new Toast(CtripBaseApplication.getInstance());
        // View view = LayoutInflater.from(CtripBaseApplication.getInstance()).inflate(R.layout.common_toast, null);
        // TextView tv = (TextView)view.findViewById(R.id.toast_message);
        // tv.setText(message);
        // toast.setView(view);
        // toast.setDuration(computeMsgLength(tv, message));
        // toast.setGravity(Gravity.CENTER, 0, 0);
        // toast.show();
    }

    public static void showInCenter(Context context, String message) {

        if (TextUtils.isEmpty(message)) message = "";
        Toast toast = new Toast(context);
        /*View view = LayoutInflater.from(context).inflate(R.layout.common_toast, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_message);
        tv.setText(message);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);*/
        toast.show();
    }

    public static void showInBottom(Context context, String message) {
        if (TextUtils.isEmpty(message)) return;
        Toast toast = new Toast(context);
        /*View view = LayoutInflater.from(context).inflate(R.layout.common_toast, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_message);
        tv.setText(message);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 120);*/
        toast.show();
    }

    public static void showShortToast(Object info){

        String showContent;
        Context context = MyApplication.getInstance();
        if(info instanceof String){
            showContent = (String)info;
        }else{
            showContent = context.getString((Integer) info);
        }
        showToastShort(showContent);
    }

    private static void showToastShort(String infoString) {
        Context context = MyApplication.getInstance();
        if (context == null) {
            return;
        }
        showToastText(infoString, Toast.LENGTH_SHORT);
    }

    private static void showToastText(String infoStr, int TOAST_LENGTH) {
        Context context = MyApplication.getInstance();
        toast = getToast(context);
        TextView textView = (TextView) toast.getView().findViewById(R.id.tv_toast);
        textView.setText(infoStr);
        textView.setTypeface(Typeface.SANS_SERIF);
        toast.setDuration(TOAST_LENGTH);
        toast.show();
    }

}
