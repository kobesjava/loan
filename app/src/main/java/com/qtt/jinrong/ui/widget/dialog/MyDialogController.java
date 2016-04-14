package com.qtt.jinrong.ui.widget.dialog;

import android.content.Context;
import android.view.View;

/**
 * Created by yanxin on 16/4/14.
 */
public class MyDialogController {

    public static class AlertParams {

        public Context mContext;

        public boolean showTitle;
        public CharSequence mTitle;

        public boolean showMessage;
        public CharSequence mMessage;

        public boolean showPositionButton;
        public CharSequence mPositiveButtonText;
        public View.OnClickListener mPositiveButtonListener;

        public boolean showNegativeButton;
        public CharSequence mNegativeButtonText;
        public View.OnClickListener mNegativeButtonListener;

        public boolean showProgress;

        public boolean mCancelable;

        public View mView;
        public int mViewLayoutResId;

        public AlertParams(Context mContext) {
            this.mContext = mContext;
        }
    }

}
