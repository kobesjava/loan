package com.qtt.jinrong.ui.widget.radio;

import java.util.List;

/**
 * Created by blue on 15/8/7.
 */
public class BorderRadioUtils {


    /**
     * 快速设置radioGroup
     *
     * @param list
     * @param radio
     * @param count
     */
    public static void radioGroupSetList(final List<String> list, BorderRadioGruop radio, final int count) {
        radio.setAdapter(new BorderRadioAdapter() {

            @Override
            public String getItemData(int position) {
                return list.get(position);
            }

            @Override
            public int getRowMaxCount() {
                return count;
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    /**
     * 快速设置radioGroup 文字大小
     *
     * @param list
     * @param radio
     * @param count
     */
    public static void radioGroupSetList(final List<String> list, BorderRadioGruop radio, final int textSize, final int count) {
        radio.setAdapter(new BorderRadioAdapter(textSize) {

            @Override
            public String getItemData(int position) {
                return list.get(position);
            }

            @Override
            public int getRowMaxCount() {
                return count;
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

}
