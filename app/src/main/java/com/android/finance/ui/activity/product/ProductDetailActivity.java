package com.android.finance.ui.activity.product;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.finance.R;
import com.android.finance.ui.activity.common.BaseActivity;
import com.android.finance.ui.widget.CommonTitleBar;
import com.android.finance.ui.widget.ImgText;
import com.android.finance.ui.widget.TabIndictor;
import com.android.finance.ui.widget.dialog.AlertDialogUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/2/25.
 */
@EActivity(R.layout.activity_product_detail)
public class ProductDetailActivity extends BaseActivity {

    /** 金额*/
    public final static String INTENT_TOTAL = "INTENT_TOTAL";
    /** 期限*/
    public final static String INTENT_TERM = "INTENT_TERM";


    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.img)
    ImageView mProductIcon;

    @ViewById(R.id.name)
    TextView mProductName;

    @ViewById(R.id.interestRate)
    TextView mInterestRate;

    @ViewById(R.id.imgText)
    ImgText mImgText;

    @ViewById(R.id.amount)
    EditText mAmount;

    @ViewById(R.id.term)
    EditText mTerm;

    @ViewById(R.id.loanTip)
    TextView mLoanTip;

    @ViewById(R.id.interestTotal)
    TextView mInterestTotal;

    @ViewById(R.id.interestMonth)
    TextView mInterestMonth;

    @ViewById(R.id.time)
    TextView mTime;

    @ViewById(R.id.material)
    TabIndictor mTabIndictor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @AfterViews
    public void initView() {
        mTitleBar.setTitle(getString(R.string.product_detail_title));
        mTitleBar.setActivity(this);

        mAmount.setText(mIntent.getIntExtra(INTENT_TOTAL,10)+"");
        mTerm.setText(mIntent.getIntExtra(INTENT_TERM,12)+"");

        mTabIndictor.setAdapter(new TabIndictor.BaseAdapter() {
            @Override
            public String getString(int position) {
                return "";
            }
        });
    }

    @Click(R.id.interestTotalView)
    void clickInsterestTotal() {
        String s = "总利息=利息+服务费\n参考月利率 : 0.78%\n参考服务费率 : 0";
        if(mDialog == null) {
            mDialog = AlertDialogUtils.showPrompt(this, "总利息说明", s);
        }
        mDialog.show();
    }

    @Click(R.id.btnSubmit)
    void clickBtnSubmit() {

    }

}
