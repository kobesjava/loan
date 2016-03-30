package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;

import com.qtt.jinrong.R;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 资料上传
 * Created by yanxin on 16/3/30.
 */
@EActivity(R.layout.activity_data_upload)
public class DataUploadActivity extends BaseActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void initView() {
        mTitleBar.setTitle("资料信息");
        mTitleBar.setActivity(this);
    }

}
