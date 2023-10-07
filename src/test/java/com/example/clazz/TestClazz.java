package com.example.clazz;

/**
 * .Class和Class.forName是编译时决定, 而.getClass()是运行时决定
 *
 * @author Chris
 * @version 1.0.0
 * @date 2020/06/03
 */
public class TestClazz {
    public static void main(String[] args) throws ClassNotFoundException {
        // 初始化静态及动态代码块和对应的构造方法
        // 初始化顺序如下：
        // 静态代码块： b robot ...
        // 动态代码块： a robot ...
        // 构造方法： robot...
        final Robot robot = new Robot();
        // 返回class
        final Class<? extends Robot> c1 = robot.getClass();
        // 不进行任何操作
        // 由于只是装入内存, 不对类进行初始化, 所以不会执行构造函数, 静态/非静态代码块
        // final Class<Robot> c2 = Robot.class;
        // 进行初始化 static 代码块
        // 只会对类进行静态初始化
        // final Class<?> c3 = Class.forName("com.example.clazz.Robot");
        // if (c1 == c2 && c2 == c3 && c1 == c3) {
        //     System.out.println("three classes are equal");
        // }
        // printClassInfo(c1);
    }

    public static void printClassInfo(Class c) {
        System.out.println(c.getName()); //获得该Class对象的全称名称
        System.out.println(c.getSuperclass()); //getSuperclass()获得该Class对象的直接父类
        System.out.println(c.getInterfaces()); //getInterfaces()获得该Class对象实现的所有接口
        System.out.println(c.isArray()); //isArray()判断该Class对象是否为数组类
        System.out.println(c.getMethods()); //getMethods()取得该Class对象所有共有方法
        System.out.println(c.getClassLoader()); //getClassLoader()取得该Class对象的类装载器
    }
}
