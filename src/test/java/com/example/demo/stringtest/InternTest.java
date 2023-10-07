package com.example.demo.stringtest;

public class InternTest {
    public static void main(String[] args) {

        String str2 = new String("str") + new String("01");
        str2.intern();
        String str1 = "str01";
        System.out.println(str2 == str1);//#7 true
    }
}