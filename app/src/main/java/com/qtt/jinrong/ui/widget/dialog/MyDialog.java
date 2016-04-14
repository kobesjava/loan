package com.qtt.jinrong.ui.widget.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qtt.jinrong.R;

/**
 * Created by yanxin on 16/4/14.
 */
public class MyDialog {

    private Dialog dialog;
    private Builder builder;

    public Dialog getDialog() {
        return dialog;
    }

    public void show() {
        dialog.show();
    }

    public void setMessage(CharSequence message) {
        builder.setMessage(message);
    }

    public static class Builder {
        private final MyDialogController.AlertParams P;
        private int mTheme;

        public Builder(Context context) {
            this(context,0);
        }

        public Builder(Context context, int theme) {
            P = new MyDialogController.AlertParams(context);
            this.mTheme = theme;
        }

        public Context getContext() {
            return P.mContext;
        }

        public Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        public Builder setTitle(int titleId) {
            P.mTitle = P.mContext.getText(titleId);
            return this;
        }

        public Builder setTitle(CharSequence title) {
            P.mTitle = title;
            return this;
        }

        public Builder setMessage(int messageId) {
            P.mMessage = P.mContext.getText(messageId);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            P.mMessage = message;
            if(P.mView != null) {
                ((TextView)P.mView.findViewById(R.id.message)).setText(message);
            }
            return this;
        }

        public Builder setPositiveButton(int textId, final View.OnClickListener listener) {
            P.mPositiveButtonText = P.mContext.getText(textId);
            P.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, final View.OnClickListener listener) {
            P.mPositiveButtonText = text;
            P.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(int textId, final View.OnClickListener listener) {
            P.mNegativeButtonText = P.mContext.getText(textId);
            P.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, final View.OnClickListener listener) {
            P.mNegativeButtonText = text;
            P.mNegativeButtonListener = listener;
            return this;
        }

        public Builder showTitle(boolean showTitle) {
            P.showTitle = showTitle;
            return this;
        }

        public Builder showMessage(boolean showMessage) {
            P.showMessage = showMessage;
            return this;
        }

        public Builder showProgress(boolean showProgress) {
            P.showProgress = showProgress;
            return this;
        }

        public Builder setShowButton(boolean showPositionButton,boolean showNegativeButton) {
            P.showPositionButton = showPositionButton;
            P.showNegativeButton = showNegativeButton;
            return this;
        }

       /* public Builder setView(int layoutResId) {
            P.mView = null;
            P.mViewLayoutResId = layoutResId;
            return this;
        }

        public Builder setView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }*/

        public MyDialog create() {
            View view = LayoutInflater.from(P.mContext).inflate(R.layout.dialog_common, null);

            P.mView = view;

            final AlertDialog alertPrompt = new AlertDialog.Builder(P.mContext).create();
            alertPrompt.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertPrompt.setView(view, 0, 0, 0, 0);
            alertPrompt.setCanceledOnTouchOutside(P.mCancelable);
            alertPrompt.setCancelable(P.mCancelable);
            alertPrompt.show();
            alertPrompt.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            TextView alert_title = (TextView) view.findViewById(R.id.title);
            if(P.showTitle) {
                alert_title.setText(P.mTitle);
            } else {
                alert_title.setVisibility(View.GONE);
                view.findViewById(R.id.titleLine).setVisibility(View.GONE);
            }

            TextView alert_message = (TextView) view.findViewById(R.id.message);
            if(P.showMessage) {
                alert_message.setText(P.mMessage);
            } else {
                alert_message.setVisibility(View.GONE);
                view.findViewById(R.id.msgLine).setVisibility(View.GONE);
            }

            TextView btn_confirm = (TextView) view.findViewById(R.id.btn_confirm);
            if(P.showPositionButton) {
                btn_confirm.setText(P.mPositiveButtonText);
                if(P.mPositiveButtonListener != null) {
                    btn_confirm.setOnClickListener(P.mPositiveButtonListener);
                }
            } else {
                btn_confirm.setVisibility(View.GONE);
                view.findViewById(R.id.line).setVisibility(View.GONE);
            }

            TextView btn_cancel = (TextView) view.findViewById(R.id.btn_cancel);
            if(P.showNegativeButton) {
                btn_cancel.setText(P.mNegativeButtonText);
                if(P.mNegativeButtonListener != null) {
                    btn_cancel.setOnClickListener(P.mNegativeButtonListener);
                }
            } else {
                btn_cancel.setVisibility(View.GONE);
                view.findViewById(R.id.line).setVisibility(View.GONE);
            }

            View progress = view.findViewById(R.id.progress);
            if(P.showProgress) {
                progress.setVisibility(View.VISIBLE);
            } else {
                progress.setVisibility(View.GONE);
            }

            MyDialog myDialog = new MyDialog();
            myDialog.dialog = alertPrompt;
            myDialog.builder = this;

            return myDialog;
        }

        public MyDialog show() {
            MyDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}
