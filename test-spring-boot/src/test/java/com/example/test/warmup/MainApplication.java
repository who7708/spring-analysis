package com.example.test.warmup;

public class MainApplication {

    static {
        long start = System.nanoTime();
        ManualClassLoader.load();
        long end = System.nanoTime();
        System.out.println("Warm Up time : " + (end - start));
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // MainApplication.class.getClassLoader().loadClass("com.example.test.warmup.Dummy");
        long start = System.nanoTime();
        ManualClassLoader.load();
        long end = System.nanoTime();
        System.out.println("Total time taken : " + (end - start));
    }
}
//  -XX:+TieredCompilation：开启分层编译（1.7 Server 模式默认开启）
//
//  -XX:CompileThreshold：设置触发即时编译阈值
//
//  -XX:+PrintCompilation：打印被编译成本地代码的方法名称

// -XX:+TieredCompilation -XX:CompileThreshold=8 -XX:+PrintCompilation