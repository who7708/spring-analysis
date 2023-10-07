package com.example.demo.stringtest;

public class InternTest01 {
    public static void main(String[] args) {
        String str1 = "str01";
        String str2 = new String("str") + new String("01");
        String str3 = str2.intern();
        System.out.println(str2 == str1);//#8 false
        System.out.println(str3 == str1);//#8 true

        String s = new String("abc");
        String s1 = "abc";
        String s2 = new String("abc");

        System.out.println(s == s1.intern());
        System.out.println(s == s2.intern());
        System.out.println(s1 == s2.intern());
    }
}