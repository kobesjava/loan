package com.android.finance.bean.event;

import com.android.finance.enums.LoanType;

/**
 * Created by yanxin on 16/2/24.
 */
public class LoanTypeEvent {

    private LoanType type;

    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }
}
