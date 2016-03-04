package com.qtt.jinrong.ui.activity.common;

import android.os.Bundle;
import android.view.View;

import com.qtt.jinrong.ui.widget.SelectPopView;

/**
 * Created by yanxin on 16/3/4.
 */
public class BaseSelectActivity extends BaseActivity {

    protected View mRoot;
    protected SelectPopView mSelectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSelectView = new SelectPopView(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mRoot = findViewById(android.R.id.content);
    }

    protected void show() {
        mSelectView.show(mRoot);
    }

}
