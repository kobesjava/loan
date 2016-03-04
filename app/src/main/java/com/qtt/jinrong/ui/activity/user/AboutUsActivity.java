package com.qtt.jinrong.ui.activity.user;

import android.content.Intent;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.config.ConfigH5;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.activity.web.WebViewActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.framework.config.AppConfig;
import com.qtt.framework.util.GeneratedClassUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/2/24.
 */
@EActivity(R.layout.activity_about_us)
public class AboutUsActivity extends BaseActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @ViewById(R.id.version)
    TextView mVersionText;

    @AfterViews
    public void initView() {
        mCommonTitleBar.setTitle(getString(R.string.about_us_title));
        mCommonTitleBar.setActivity(this);

        mVersionText.setText(AppConfig.versionName);
    }

    @Click(R.id.introduce)
    void clickBtnIntroduce() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(WebViewActivity.class));
        intent.putExtra(WebViewActivity.PARAM_TITLE,getString(R.string.about_us_introduce));
        intent.putExtra(WebViewActivity.PARAM_URL, ConfigH5.getCompanyIntroduce());
        startActivity(intent);
        finish();
    }

}
