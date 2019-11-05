package com.nojava.bean;

public class Bean01 {

    private String name;

    private int age;

    public Bean01() {
        System.out.println("Bean01构造器");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
