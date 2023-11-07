package com.example.copy;

import org.junit.Test;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/11/07
 */
public class TestObjCopy {

    @Test
    public void test1() {
        // 原始对象
        Student stud = new Student("张三", "御剑飞行");
        System.out.println("原始对象: " + stud.getName() + " - " + stud.getSubj().getName());

        // 拷贝对象
        Student clonedStud = (Student) stud.clone();
        System.out.println("拷贝对象: " + clonedStud.getName() + " - " + clonedStud.getSubj().getName());

        // 原始对象和拷贝对象是否一样：
        System.out.println("原始对象和拷贝对象是否一样: " + (stud == clonedStud));
        // 原始对象和拷贝对象的name属性是否一样
        System.out.println("原始对象和拷贝对象的name属性是否一样: " + (stud.getName() == clonedStud.getName()));
        // 原始对象和拷贝对象的subj属性是否一样
        System.out.println("原始对象和拷贝对象的subj属性是否一样: " + (stud.getSubj() == clonedStud.getSubj()));

        stud.setName("张三逗比");
        stud.getSubj().setName("御剑飞行绝技");
        System.out.println("更新后的原始对象: " + stud.getName() + " - " + stud.getSubj().getName());
        System.out.println("更新原始对象后的克隆对象: " + clonedStud.getName() + " - " + clonedStud.getSubj().getName());
    }

}
