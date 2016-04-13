package com.qtt.jinrong.ui.activity.pay;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.R;
import com.qtt.jinrong.app.MyApplication;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.activity.common.MainActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/4/13.
 */
@EActivity(R.layout.activity_pay_result)
public class PayResultActivity extends BaseActivity {

    public static final String INTENT_RESULT = "INTENT_RESULT";

    @ViewById(R.id.titleBar)
    CommonTitleBar titleBar;

    @ViewById(R.id.icon)
    ImageView icon;
    @ViewById(R.id.result)
    TextView  resultTxt;

    @AfterViews
    void initView() {
        titleBar.setTitle("支付结果");
        titleBar.setLeftBtnVisible(View.GONE);

        boolean result = mIntent.getBooleanExtra(INTENT_RESULT,false);
        if(result) {
            icon.setBackgroundResource(R.drawable.buyresult_succeed);
            resultTxt.setText("支付成功");
        } else {
            icon.setBackgroundResource(R.drawable.buyresult_failed);
            resultTxt.setText("支付失败");
        }
    }

    @Override
    protected boolean isEnableBackKey() {
        return true;
    }

    @Click(R.id.btnFinish)
    void clickFinish() {
        Intent intent = new Intent(MyApplication.getInstance(), GeneratedClassUtils.get(MainActivity.class));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
