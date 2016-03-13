package com.qtt.jinrong.util;

import android.content.Context;

import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.enums.ProvinceEnum;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yanxin on 16/3/12.
 */
public class DistrictUtil {

    public static List<String> getCities(Context context,ProvinceEnum mEnum) {
        if(mEnum == null) return null;
        try {
            Class mClass = Class.forName("com.qtt.jinrong.R$array");
            Field filed = mClass.getDeclaredField("city" + mEnum.getCode());
            int arrayId = filed.getInt(null);
            String[] arrayCity = context.getResources().getStringArray(arrayId);
            return Arrays.asList(arrayCity);
        }catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("DistrictUtil",e.getMessage());
        }

        return null;
    }

    public static String getCity(Context context,ProvinceEnum mEnum,Integer cityId) {
        if(cityId == null || cityId == 0) return null;
        List<String> cities = getCities(context,mEnum);
        if(cities == null || cities.size() == 0) return null;
        if(cityId > cities.size()) return null;
        return cities.get(cityId-1);
    }

}
