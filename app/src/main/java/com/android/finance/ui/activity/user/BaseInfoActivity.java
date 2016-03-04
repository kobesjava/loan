package com.android.finance.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.android.finance.R;
import com.android.finance.enums.MarriageEnum;
import com.android.finance.enums.ProvinceEnum;
import com.android.finance.ui.activity.common.BaseSelectActivity;
import com.android.finance.ui.widget.CommonTitleBar;
import com.android.finance.ui.widget.SelectPopView;
import com.android.finance.util.ToastUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_base_info)
public class BaseInfoActivity extends BaseSelectActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.province)
    TextView mProvinceText;

    @ViewById(R.id.marriage)
    TextView mMarriageText;

    private List<String> provinces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void initView() {
        mTitleBar.setTitle(getString(R.string.user_base_info_title));
        mTitleBar.setActivity(this);
        mTitleBar.setRightViewVisible(View.VISIBLE, getString(R.string.save));
        mTitleBar.setTitleBarListener(new CommonTitleBar.TitleBarListener() {
            @Override
            public void leftOnClick() {

            }

            @Override
            public void rightOnClick() {
                ToastUtil.showShortToast("保存");
            }
        });
    }

    @Click(R.id.province)
    void clickProvince() {
        if(provinces == null) provinces = ProvinceEnum.getValues();
        mSelectView.setData(provinces);
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mProvinceText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.city)
    void clickCity() {
        /*if(TextUtils.isEmpty(mProvince)) {
            ToastUtil.showShortToast("请先选择户籍!");
            return;
        }*/
        mSelectView.setData(ProvinceEnum.getValues());
    }

    @Click(R.id.marriage)
    void clickMarriage() {
        mSelectView.setData(MarriageEnum.getTitles());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mMarriageText.setText(val);
            }
        });
        show();
    }

}
