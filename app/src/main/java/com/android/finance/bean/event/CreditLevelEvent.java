package com.android.finance.bean.event;

import com.android.finance.enums.CreditLevelEnum;

/**
 * Created by yanxin on 16/2/24.
 */
public class CreditLevelEvent {

    private CreditLevelEnum level;

    public CreditLevelEnum getLevel() {
        return level;
    }

    public void setLevel(CreditLevelEnum level) {
        this.level = level;
    }
}
