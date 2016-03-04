package com.qtt.jinrong.bean.event;

import com.qtt.jinrong.enums.CreditLevelEnum;
import com.qtt.jinrong.enums.LoanTypeEnum;

/**
 * Created by yanxin on 16/2/23.
 */
public class TabEvent {

    private int tabIndex;

    private LoanTypeEnum loanTypeEnum;

    private CreditLevelEnum creditLevelEnum;

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public LoanTypeEnum getLoanTypeEnum() {
        return loanTypeEnum;
    }

    public void setLoanTypeEnum(LoanTypeEnum loanTypeEnum) {
        this.loanTypeEnum = loanTypeEnum;
    }

    public CreditLevelEnum getCreditLevelEnum() {
        return creditLevelEnum;
    }

    public void setCreditLevelEnum(CreditLevelEnum creditLevelEnum) {
        this.creditLevelEnum = creditLevelEnum;
    }
}
