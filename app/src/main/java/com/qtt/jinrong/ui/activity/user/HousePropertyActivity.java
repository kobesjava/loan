package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.HousePropertyModel;
import com.qtt.jinrong.bean.account.HousePropertySaveRequest;
import com.qtt.jinrong.enums.HousePropertyAssessedEnum;
import com.qtt.jinrong.enums.HousePropertySpaceEnum;
import com.qtt.jinrong.enums.HouseSpareEnum;
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
                request.setDistrict(mEnum.getCode());
                hpPositionText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.hpSpare)
    void clickHpPosition() {
        mSelectView.setData(HouseSpareEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HouseSpareEnum mEnum = HouseSpareEnum.values()[position];
                request.setSpare(mEnum.getCode());
                hpSpareText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.hpSpace)
    void clickHpSpace() {
        mSelectView.setData(HousePropertySpaceEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HousePropertySpaceEnum mEnum = HousePropertySpaceEnum.values()[position];
                request.setArea(mEnum.getCode());
                hpSpaceText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.hpPriceAssessed)
    void clickHpPriceAssessed() {
        mSelectView.setData(HousePropertyAssessedEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HousePropertyAssessedEnum mEnum = HousePropertyAssessedEnum.values()[position];
                request.setEvaluation(mEnum.getCode());
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
                request.setMortgage(mEnum.getCode());
                hpSituationText.setText(val);
            }
        });
        show();
    }


    /***  IHousePropertyView  ***/
    @Override
    public void onRequest(HousePropertyModel model) {
        if(model.getHouse() != null) {
            HousePropertyEnum hpEnum = HousePropertyEnum.find(model.getHouse());
            if(hpEnum == null) return;
            request.setHouse(hpEnum.getCode());
            mHousePropertyText.setText(hpEnum.getTitle());
            if (hpEnum.equals(HousePropertyEnum.无房产)) {
                hpMore.setVisibility(View.GONE);
                return;
            }
            hpMore.setVisibility(View.VISIBLE);
            if(model.getDistrict() != null) {
                HousePropertyPositionEnum mEnum = HousePropertyPositionEnum.find(model.getDistrict());
                if(mEnum != null) hpPositionText.setText(mEnum.name());
            }
            if(!TextUtils.isEmpty(model.getAddr())) hpaddressEdit.setText(model.getAddr());
            if(model.getSpare() != null) {
                HouseSpareEnum mEnum = HouseSpareEnum.find(model.getSpare());
                if(mEnum != null) hpSpareText.setText(mEnum.name());
            }
            if(model.getArea() != null) {
                HousePropertySpaceEnum mEnum = HousePropertySpaceEnum.find(model.getArea());
                if(mEnum != null) hpSpaceText.setText(mEnum.name());
            }
            if(model.getTotalPrice() != null) hpTotalPriceBuyEdit.setText(String.valueOf(model.getTotalPrice()));
            if(model.getCurrPrice() != null) hpPriceNowEdit.setText(String.valueOf(model.getCurrPrice()));
            if(model.getEvaluation() != null) {
                HousePropertyAssessedEnum mEnum = HousePropertyAssessedEnum.find(model.getEvaluation());
                if(mEnum != null) hpPriceAssessedText.setText(mEnum.getTitle());
            }
            if(model.getMortgage() != null) {
                HousePropertySituationEnum mEnum = HousePropertySituationEnum.find(model.getMortgage());
                if(mEnum != null) hpSituationText.setText(mEnum.getTitle());
            }
        }
    }

    @Override
    public void onSaveSuccess() {
        finish();
    }

    @Override
    public HousePropertySaveRequest getSaveRequest() {
        String addr = hpaddressEdit.getString();
        if(!TextUtils.isEmpty(addr)) {
            request.setAddr(addr);
        }
        String priceBuy = hpTotalPriceBuyEdit.getString();
        if(!TextUtils.isEmpty(priceBuy)) {
            request.setTotalPrice(Float.valueOf(priceBuy));
        }
        String priceNow = hpPriceNowEdit.getString();
        if(!TextUtils.isEmpty(priceNow)) {
            request.setCurrPrice(Integer.valueOf(priceNow));
        }
        return request;
    }
    /***  IHousePropertyView  ***/
}
