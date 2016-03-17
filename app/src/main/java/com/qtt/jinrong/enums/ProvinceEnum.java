package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 省
 * Created by yanxin on 16/3/4.
 */
public enum ProvinceEnum {

    北京市(1),
    天津市(2),
    河北省(3),
    山西省(4),
    内蒙古自治区(5),
    辽宁省(6),
    吉林省(7),
    黑龙江省(8),
    上市市(9),
    江苏省(10),
    浙江省(11),
    安徽省(12),
    福建省(13),
    江西省(14),
    山东省(15),
    河南省(16),
    湖北省(17),
    湖南省(18),
    广东省(19),
    海南省(20),
    广西壮族自治区(21),
    甘肃省(22),
    陕西省(23),
    新疆维吾尔自治区(24),
    青海省(25),
    宁夏回族自治区(26),
    重庆市(27),
    四川省(28),
    贵州省(29),
    云南省(30),
    西藏自治区(31),
    台湾省(32),
    澳门特别行政区(33),
    香港特别行政区(34);

    private int code;
    ProvinceEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static List<String> getValues() {
        ProvinceEnum[] enums = ProvinceEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static ProvinceEnum find(Integer code) {
        if(code == null) return null;
        ProvinceEnum[] enums = ProvinceEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }
}
