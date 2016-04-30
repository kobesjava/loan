package com.qtt.jinrong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.Base64;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

       Integer i = 1;

        List<Integer> list = new ArrayList<>();
        list.add(i);

        Integer j = 1;

        list.remove(j);



        System.out.print("TESTJAVA="+list.size());
    }

    @Test
    public void test1() {
        Map<String,Object> map = new HashMap<>();

        byte[] bb = {1,2,3,4,5,6};
        map.put("aaa", bb);

        String str = JSON.toJSONString(map);

        Map<String,String> map1 = JSON.parseObject(str, Map.class);

       // byte[] cc = JSON.parseObject(map1.get("aaa"), byte[].class);

        byte[] ccc = Base64.decodeFast(map1.get("aaa"));


        //com.alibaba.fastjson.JSONArray jsonArray = JSON.parseArray(String.valueOf(str1.get("aaa")));

        System.out.print("TESTJAVA=" + str);

    }

    @Test
    public void test2() {
        String monthRate = "1.6%～2.3%";
        String rate = monthRate.substring(0,monthRate.indexOf("%"));
        if(rate.contains("～")) {
            rate = rate.substring(0,rate.indexOf("～"));
        } else if(rate.contains("~")) {
            rate = rate.substring(0,rate.indexOf("~"));
        }
        try {
            float ss =  Float.parseFloat(rate)/100f;
            System.out.print("ss="+ss);
        }catch (Exception e) {
            System.out.print("ss="+e.getMessage());
            e.printStackTrace();
        }
    }

}