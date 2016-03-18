package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 所属行业
 * Created by yanxin on 16/3/12.
 */
public enum IndustryEnum {

    批发零售(1),
    制造业(2),
    金融保险证券(3),
    住宿餐饮旅游(4),
    商业服务娱乐艺术体育(5),
    计算机互联网(6),
    通讯电子(7),
    建筑房地产(8),
    法律咨询(9),
    卫生教育社会服务(10),
    国家机关公共事业科研社会团体(11),
    生物制药(12),
    广告媒体(13),
    能源(14),
    贸易(15),
    交通运输仓储邮政(16),
    农林牧渔(17),
    其它(18);

    private int code;
    IndustryEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        IndustryEnum[] enums = IndustryEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static IndustryEnum find(Integer code) {
        if(code == null) return null;
        IndustryEnum[] enums = IndustryEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }

}
