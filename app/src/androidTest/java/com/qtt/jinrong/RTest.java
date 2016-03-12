package com.qtt.jinrong;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.ui.activity.common.WelcomeActivity_;

import java.lang.reflect.Field;

/**
 * Created by yanxin on 16/3/12.
 */
public class RTest extends ActivityInstrumentationTestCase2 {

    Context context;

    public RTest() {
        super(WelcomeActivity_.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        context = getInstrumentation().getTargetContext();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        context = null;
    }

    public void testR() throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        Class mClass = Class.forName("com.qtt.jinrong.R$array");
        Field filed = mClass.getDeclaredField("credit_bank");
        Object obj = filed.get(null);
        LogUtil.d("FANSHE","obj="+obj);
    }

}
