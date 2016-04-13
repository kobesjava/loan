package com.qtt.framework.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.qtt.framework.util.DialogBuilder;

/**
 * Created by Jer on 2015/9/17.
 * 把支付做成静态的支付工具
 */
public class PayPresenter {

    public interface ICompletePayListener {
        void paySuccess();

        void payFail();
    }

    private static AlertDialog progressDialog;

    public static void showResultDialog(final Activity mActivity, String msgInfo) {
        final AlertDialog dialog = DialogBuilder.showSimpleDialog(msgInfo, mActivity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //if (mActivity instanceof WXPayEntryActivity) {
                //    mActivity.finish();
                //}
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //if (mActivity instanceof WXPayEntryActivity) {
                //   mActivity.finish();
                //}
            }
        });
    }

    public static void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
