package com.qtt.jinrong.ui.activity.user;

import android.content.Intent;

import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.pay.PayIntent;
import com.qtt.jinrong.enums.MemberEnum;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.activity.pay.PayActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 会员
 * Created by yanxin on 16/4/12.
 */
@EActivity(R.layout.activity_vip)
public class VipActivity extends BaseActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar titleBar;

    @AfterViews
    void initView() {
        titleBar.setTitle("开通会员");
        titleBar.setActivity(this);
    }

    @Click(R.id.btnBronze)
    void clickbtnBronze() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(PayActivity.class));
        PayIntent model = new PayIntent();
        model.setAmount(MemberEnum.铜牌.getAmount());
        model.setTitle(MemberEnum.铜牌.getLevel());
        intent.putExtra(PayActivity.INTENT_PAY_MODEL,model);
        startActivity(intent);
    }

    @Click(R.id.btnSilver)
    void clickbtnSilver() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(PayActivity.class));
        PayIntent model = new PayIntent();
        model.setAmount(MemberEnum.银牌.getAmount());
        model.setTitle(MemberEnum.银牌.getLevel());
        intent.putExtra(PayActivity.INTENT_PAY_MODEL,model);
        startActivity(intent);
    }

    @Click(R.id.btnGold)
    void clickbtnGold() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(PayActivity.class));
        PayIntent model = new PayIntent();
        model.setAmount(MemberEnum.金牌.getAmount());
        model.setTitle(MemberEnum.金牌.getLevel());
        intent.putExtra(PayActivity.INTENT_PAY_MODEL,model);
        startActivity(intent);
    }

}
