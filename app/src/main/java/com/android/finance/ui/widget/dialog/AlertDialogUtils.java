package com.android.finance.ui.widget.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.finance.R;

/**
 * @author yanxin
 */
public class AlertDialogUtils {

    public interface ImplAlertDialog {

        public void Confirm(Object object);

    }

    /**
     * 取消的点击回调事件
     */
    public interface OnCancelClickListener {
        void onCancelClickListner();
    }


    /**
     * 确认取消 Dialog
     *
     * @param context
     * @param title
     * @param confirm
     * @param cancel
     * @param impl
     */
    public static void showdDoublelickPrompt(Context context, CharSequence title, String confirm, String cancel, final ImplAlertDialog impl) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_double_click_prompt, null);

        final AlertDialog alertPrompt = new AlertDialog.Builder(context).create();
        alertPrompt.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertPrompt.setView(view, 0, 0, 0, 0);
        alertPrompt.setCanceledOnTouchOutside(false);
        alertPrompt.setCancelable(false);
        alertPrompt.show();
        alertPrompt.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView alert_title = (TextView) view.findViewById(R.id.dialog_alert_title);
        alert_title.setText(title);

        TextView btn_confirm = (TextView) view.findViewById(R.id.btn_confirm);
        btn_confirm.setText(confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertPrompt != null && alertPrompt.isShowing()) {
                    impl.Confirm("confirm");
                    alertPrompt.dismiss();
                }
            }
        });

        TextView btn_cancel = (TextView) view.findViewById(R.id.btn_cancel);
        btn_cancel.setText(cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertPrompt != null && alertPrompt.isShowing()) {
                    alertPrompt.dismiss();
                }
            }
        });
    }

    /**
     * 确认取消 Dialog
     *
     * @param context
     * @param title
     * @param confirm
     * @param cancel
     * @param impl
     */
    public static void showdDoublelickPrompt(Context context, CharSequence title, String confirm, String cancel, final ImplAlertDialog impl, final OnCancelClickListener cancelListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_double_click_prompt, null);

        final AlertDialog alertPrompt = new AlertDialog.Builder(context).setView(view).create();
        alertPrompt.setCanceledOnTouchOutside(false);
        alertPrompt.setCancelable(false);
        alertPrompt.show();
        alertPrompt.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView alert_title = (TextView) view.findViewById(R.id.dialog_alert_title);
        alert_title.setText(title);

        TextView btn_confirm = (TextView) view.findViewById(R.id.btn_confirm);
        btn_confirm.setText(cancel);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertPrompt != null && alertPrompt.isShowing()) {
                    alertPrompt.dismiss();
                    cancelListener.onCancelClickListner();
                }
            }
        });

        TextView btn_cancel = (TextView) view.findViewById(R.id.btn_cancel);
        btn_cancel.setText(confirm);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertPrompt != null && alertPrompt.isShowing()) {
                    impl.Confirm("confirm");
                    alertPrompt.dismiss();
                }
            }
        });
    }

    /**
     * 确认 Dialog
     *
     * @param context
     * @param title
     * @param confirm
     */
    public static Dialog showClickColseDialogPrompt(Context context, CharSequence title, String confirm, final ImplAlertDialog impl) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_prompt, null);
        TextView text_title = (TextView) view.findViewById(R.id.text_title);
        text_title.setText(title);
        TextView prompt_click = (TextView) view.findViewById(R.id.prompt_click);
        prompt_click.setText(confirm);
        final AlertDialog prompt = new AlertDialog.Builder(context).setView(view).create();
        prompt.setCanceledOnTouchOutside(false);
        prompt.setCancelable(false);
        prompt.show();
        prompt.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        prompt_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prompt != null && prompt.isShowing()) {
                    prompt.dismiss();
                    if (impl != null) {
                        impl.Confirm("confirm");
                    }
                }
            }
        });

        return prompt;
    }

    /**
     * 确认 Dialog
     *
     * @param context
     * @param title
     */
    public static Dialog showPrompt(Context context, CharSequence title, CharSequence msg) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_prompt_btn1, null);
        TextView text_title = (TextView) view.findViewById(R.id.title);
        text_title.setText(title);
        TextView text_msg = (TextView) view.findViewById(R.id.msg);
        text_msg.setText(msg);

        final AlertDialog prompt = new AlertDialog.Builder(context).setView(view).create();
        view.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prompt != null && prompt.isShowing()) {
                    prompt.dismiss();
                }
            }
        });
        prompt.setCanceledOnTouchOutside(false);
        prompt.setCancelable(false);
        prompt.show();
        prompt.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        return prompt;
    }


    public static void showListViewDialog(Context context, CharSequence title, BaseAdapter adapter, String confirm, String cancel, final ImplAlertDialog impl, final OnCancelClickListener onCancelClickListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_listview, null);

        final AlertDialog alertPrompt = new AlertDialog.Builder(context).setView(view).create();


        alertPrompt.setCanceledOnTouchOutside(false);
        alertPrompt.setCancelable(false);
        alertPrompt.show();


        alertPrompt.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        //设置标题
        TextView alert_title = (TextView) view.findViewById(R.id.dialog_alert_title);
        alert_title.setText(title);

        //设置Adapter
        ListView listView = (ListView) view.findViewById(R.id.lv_content);
        listView.setAdapter(adapter);

        //设置确定
        TextView btn_confirm = (TextView) view.findViewById(R.id.btn_confirm);
        btn_confirm.setText(confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertPrompt != null && alertPrompt.isShowing()) {
                    alertPrompt.dismiss();
                    impl.Confirm("");
                }
            }
        });

        //设置取消
        TextView btn_cancel = (TextView) view.findViewById(R.id.btn_cancel);
        btn_cancel.setText(cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertPrompt != null && alertPrompt.isShowing()) {
                    alertPrompt.dismiss();
                }
                if (onCancelClickListener != null)
                {
                    onCancelClickListener.onCancelClickListner();
                }
            }
        });
    }

    public static Dialog showDialog(Context context, CharSequence title, CharSequence msg, CharSequence clickMsg,DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(clickMsg, clickListener);
        AlertDialog mDialog = builder.create();
        mDialog.setCancelable(false);
        mDialog.show();
        return mDialog;
    }


    /**
     * 网络请求加载 等待
     *
     * @param context
     * @return
     */
    public static Dialog showLoading(Context context) {
        Dialog mDialog = new Dialog(context, R.style.MyDialog);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setContentView(R.layout.common_loading);
        mDialog.show();
        return mDialog;
    }

    private static Dialog mLoadingDialog;

    public static void showLoad(Context context) {
        if (mLoadingDialog == null) {
            mLoadingDialog = showLoading(context);
        }
    }

    public static void dismissLoading() {
        if (mLoadingDialog != null) {
            if (mLoadingDialog.isShowing())
                mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

}
