package com.qtt.jinrong.util;

import android.content.Context;

import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.enums.ProvinceEnum;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yanxin on 16/3/12.
 */
public class DistrictUtil {

    public static List<String> getCities(Context context,ProvinceEnum mEnum) {
        try {
            Class mClass = Class.forName("com.qtt.jinrong.R$array");
            Field filed = mClass.getDeclaredField("city" + mEnum.getCode());
            int arrayId = filed.getInt(null);
            String[] arrayCity = context.getResources().getStringArray(arrayId);
            String[] arrayCity1 = context.getResources().getStringArray(R.array.city1);
            return Arrays.asList(arrayCity);
        }catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("DistrictUtil",e.getMessage());
        }

        return null;
    }

}
