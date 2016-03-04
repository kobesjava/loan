package com.qtt.jinrong.bean.event;

import com.qtt.jinrong.enums.LoanTypeEnum;

/**
 * Created by yanxin on 16/2/24.
 */
public class LoanTypeEvent {

    private LoanTypeEnum type;

    public LoanTypeEnum getType() {
        return type;
    }

    public void setType(LoanTypeEnum type) {
        this.type = type;
    }
}
