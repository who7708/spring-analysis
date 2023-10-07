package com.example.demo.stringtest;

public class StringTest01 {
    public static void main(String[] args) {
        // 而对于非final字段则是在运行期进行赋值处理的。
        String baseStr = "baseStr";
        // 对于final字段，编译期直接进行了常量替换
        final String baseFinalStr = "baseStr";

        String str1 = "baseStr01";
        String str2 = "baseStr" + "01";
        // stringBuilder.append()生成
        String str3 = baseStr + "01";
        String str4 = baseFinalStr + "01";
        String str5 = new String("baseStr01").intern();

        System.out.println(str1 == str2);//#3 true
        System.out.println(str1 == str3);//#4 false
        System.out.println(str1 == str4);//#5 true
        System.out.println(str1 == str5);//#6 true
    }
}