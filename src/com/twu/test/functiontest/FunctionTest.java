package com.twu.test.functiontest;

import com.twu.domain.HotSearch;
import com.twu.function.CommonFunction;
import com.twu.function.UserFunction;
import org.junit.Test;

import java.util.*;

/**
 *
 */

public class FunctionTest {
    Map<String,HotSearch> hotSearchMap = new HashMap<>();

    @Test
    public void testShowAll(){
        HotSearch hotSearch1 = new HotSearch(1, "11111", 4);
        HotSearch hotSearch2 = new HotSearch(2, "22222", 5);
        HotSearch hotSearch3 = new HotSearch(3, "33333", 12);
        HotSearch hotSearch4 = new HotSearch(4, "44444", 12);
        hotSearchMap.put("11111",hotSearch1);
        hotSearchMap.put("33333",hotSearch2);
        hotSearchMap.put("22222",hotSearch3);
        hotSearchMap.put("44444",hotSearch4);
        UserFunction.showAll(hotSearchMap);
    }

    @Test
    public void test1(){
        String accountType = "1";
        int[] ints = new int[]{1,2,3};
        for (int i : ints){
            System.out.println(i);
        }
        if (!(Arrays.asList(ints).contains(Integer.parseInt(accountType)))){
            System.out.println("输入有误，请重新输入。");
        }
        System.out.println("==================");

        String[] strints = new String[]{"1","2","3"};
        if (!(Arrays.asList(strints).contains(accountType))){
            System.out.println("输入有误，请重新输入。");
        }
    }


}
