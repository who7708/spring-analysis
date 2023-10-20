package org.spring.boot.test.demo;

public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造函数SingletonDemo（）");
    }

    // DCL(Double Check Lock双端检锁机制)
    // 有指令重排序的存在，DCL不一定是线程安全的。所以加入 volatile 可以禁止指令重排
    // 原因在于某一个线程执行在第一次检测，读取到的instance不为null时，instance的引用对象可能没有完成初始化
    // instance = new SingletonDemo(); 可以分库以下3步执行
    // memory = allocate(); // 1. 分配对象的内存空间
    // instance(memory); // 2. 初始化对象
    // instance = memory; // 3. 设置 instance 指向刚分配的内存地址，此时 instance!= null;
    // 2，3不存在数据依赖关系，指令重排是允许的。所以有可能会132顺序，从而导致对象还没初始化完成
    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    // 如果在方法上直接使用 synchronized ，则性能可能会降低，锁太重
    public static synchronized SingletonDemo getInstance1() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    public static void main(String[] args) {
        // 单线程（main线程的操作）
        // System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
        // System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
        // System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
        //
        // System.out.println(",,,,,");
        //
        // 并发多线程后，情况发生了很大的变化
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                // 如果单例是生效的，则构造函数日志只会打印一次
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
