package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.HousePropertyModel;
import com.qtt.jinrong.bean.account.HousePropertySaveRequest;
import com.qtt.jinrong.enums.HosuePropertyAssessedEnum;
import com.qtt.jinrong.enums.HosuePropertySpaceEnum;
import com.qtt.jinrong.enums.HosueSpareEnum;
import com.qtt.jinrong.enums.HousePropertyEnum;
import com.qtt.jinrong.enums.HousePropertyPositionEnum;
import com.qtt.jinrong.enums.HousePropertySituationEnum;
import com.qtt.jinrong.presenter.IHousePropertyPresenter;
import com.qtt.jinrong.presenter.impl.HousePropertyPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseSelectActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectPopView;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.view.IHousePropertyView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 房产信息(我的融资需求书)
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_house_property)
public class HousePropertyActivity extends BaseSelectActivity implements IHousePropertyView {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.property)
    TextView mHousePropertyText;
    @ViewById(R.id.hpMore)
    View hpMore;
    @ViewById(R.id.hpPosition)
    TextView hpPositionText;
    @ViewById(R.id.hpaddress)
    InputEditText hpaddressEdit;
    @ViewById(R.id.hpSpare)
    TextView hpSpareText;
    @ViewById(R.id.hpSpace)
    TextView hpSpaceText;
    @ViewById(R.id.hpTotalPriceBuy)
    InputEditText hpTotalPriceBuyEdit;
    @ViewById(R.id.hpPriceNow)
    InputEditText hpPriceNowEdit;
    @ViewById(R.id.hpPriceAssessed)
    TextView hpPriceAssessedText;
    @ViewById(R.id.hpSituation)
    TextView hpSituationText;


    HousePropertySaveRequest request;
    IHousePropertyPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        request = new HousePropertySaveRequest();
        request.setUserId(getUserId());
        mPresenter = new HousePropertyPresenterImpl(this);
    }

    @AfterViews
    void initView() {
        mTitleBar.setTitle(getString(R.string.user_house_title));
        mTitleBar.setActivity(this);
        mTitleBar.setRightViewVisible(View.VISIBLE,getString(R.string.save));
        mTitleBar.setTitleBarListener(new CommonTitleBar.TitleBarListener() {
            @Override
            public void leftOnClick() {

            }

            @Override
            public void rightOnClick() {
                mPresenter.save();
            }
        });
        mPresenter.request();
    }

    @Click(R.id.property)
    void clickProperty() {
        mSelectView.setData(HousePropertyEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HousePropertyEnum mEnum = HousePropertyEnum.values()[position];
                if (mEnum.equals(HousePropertyEnum.无房产)) hpMore.setVisibility(View.GONE);
                else hpMore.setVisibility(View.VISIBLE);
                request.setHouse(mEnum.getCode());
                mHousePropertyText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.hpPosition)
    void clickHousePropertyPosition() {
        mSelectView.setData(HousePropertyPositionEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HousePropertyPositionEnum mEnum = HousePropertyPositionEnum.values()[position];
                hpPositionText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.hpSpare)
    void clickHpPosition() {
        mSelectView.setData(HosueSpareEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HosueSpareEnum mEnum = HosueSpareEnum.values()[position];
                hpSpareText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.hpSpace)
    void clickHpSpace() {
        mSelectView.setData(HosuePropertySpaceEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HosuePropertySpaceEnum mEnum = HosuePropertySpaceEnum.values()[position];
                hpSpaceText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.hpPriceAssessed)
    void clickHpPriceAssessed() {
        mSelectView.setData(HosuePropertyAssessedEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HosuePropertyAssessedEnum mEnum = HosuePropertyAssessedEnum.values()[position];
                hpPriceAssessedText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.hpSituation)
    void clickHousePropertySituaion() {
        mSelectView.setData(HousePropertySituationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HousePropertySituationEnum mEnum = HousePropertySituationEnum.values()[position];
                hpSituationText.setText(val);
            }
        });
        show();
    }


    /***  IHousePropertyView  ***/
    @Override
    public void onRequest(HousePropertyModel model) {
        if(model.getHouse() != null) {
            HousePropertyEnum mEnum = HousePropertyEnum.find(model.getHouse());
            if(mEnum == null) return;
            request.setHouse(mEnum.getCode());
            mHousePropertyText.setText(mEnum.getTitle());
            if (mEnum.equals(HousePropertyEnum.无房产)) {
                hpMore.setVisibility(View.GONE);
                return;
            }
            hpMore.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSaveSuccess() {
        finish();
    }

    @Override
    public HousePropertySaveRequest getSaveRequest() {
        return request;
    }
    /***  IHousePropertyView  ***/
}
