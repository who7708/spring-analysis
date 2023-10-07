package com.example.demo.stringtest;

public class StringTest {
    public static void main(String[] args) {
        String str1 = "string";
        String str2 = new String("string");
        String str3 = str2.intern();

        System.out.println(str1 == str2);//#1 false
        System.out.println(str1 == str3);//#2 true
        stringEqualsTest();
    }

    private static void stringEqualsTest() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2); // false
        System.out.println(s1 == s5); // true
        System.out.println(s1 == s6); // false
        System.out.println(s1 == s6.intern()); // true
        System.out.println(s1 == s2.intern()); // true
    }
}