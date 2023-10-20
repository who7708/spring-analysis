package com.example.clazz;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/06/03
 */
public class Robot {

    private static final int a = 100;

    public Robot() {
        System.out.println("robot...");
    }

    {
        System.out.println("a robot ...");
    }

    static {
        System.out.println("b robot ...");
    }

    public Robot(int age) {
        this.age = age;
    }

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
