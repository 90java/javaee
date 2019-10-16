package com.nojava.servlet;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试静态类在web项目中加载
 */
public class StaticClassTest {

    static List<String> a = new ArrayList<>();

    static {
        inita();
    }

    private static void inita() {
        System.out.println("开始");
        a.add("1");
        a.add("1");
        a.add("1");
        a.add("1");
        System.out.println("结束");
    }


}
