package com.example.demo;

import org.junit.Test;

public class OuterClass {
    private void outerMethod() {
        System.out.println("It's Method of OuterClass");
    }

    public static void main(String[] args) {

        OuterClass t = new OuterClass();
        OuterClass.InnerClass in = t.new InnerClass();
        in.innerMethod();

        // InnerClass innerClass = new OuterClass().new InnerClass();
    }

    @Test
    public void test1() {
        System.out.println("===== test1 =====");

        OuterClass t = new OuterClass();
        OuterClass.InnerClass in = t.new InnerClass();
        in.innerMethod();

        // InnerClass innerClass = new InnerClass();
    }

    class InnerClass {
        public void innerMethod() {
            OuterClass.this.outerMethod();// 内部类成员方法与外部类成员方法同名时，使用this调用外部类的方法
            outerMethod();// 内部类没有同名方法时执行外部类的方法
        }

        private void outerMethod() {
            System.out.println("It's Method of Innerclass");
        }
    }
}