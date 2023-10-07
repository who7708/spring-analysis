package org.spring.boot.test.demo;

/**
 * class1.isAssignableFrom(class2)
 * 判定此 class1 对象所表示的类或接口与指定的 class2 参数所表示的类或接口是否相同
 * 或是否是其超类或超接口。如果是则返回 true；否则返回 false。
 * 如果该 Class 表示一个基本类型，且指定的 Class 参数正是该 Class 对象，则该方法返回 true；否则返回 false。
 *
 * 1.class2是不是class1的子类或者子接口
 *
 * 2.Object是所有类的父类
 *
 * 如果是A.isAssignableFrom(B)
 *
 * 确定一个类(B)是不是继承来自于另一个父类(A)，一个接口(A)是不是实现了另外一个接口(B)，或者两个类相同。主要，这里比较的维度不是实例对象，而是类本身，因为这个方法本身就是Class类的方法，判断的肯定是和类信息相关的。
 *
 * 也就是判断当前的Class对象所表示的类，是不是参数中传递的Class对象所表示的类的父类，超接口，或者是相同的类型。是则返回true，否则返回false。
 */
public class TestAssignableFrom {
    public static void main(String[] args) {

        // C => B => A
        A a = new A();
        B b = new B();
        C c = new C();
        A ba = new B();
        System.out.println("1-------------");
        // true
        System.out.println(A.class.isAssignableFrom(a.getClass()));
        // true
        System.out.println(B.class.isAssignableFrom(b.getClass()));
        // true
        System.out.println(A.class.isAssignableFrom(b.getClass()));
        // true
        System.out.println(A.class.isAssignableFrom(c.getClass()));
        // false
        System.out.println(B.class.isAssignableFrom(a.getClass()));
        // true
        System.out.println(A.class.isAssignableFrom(ba.getClass()));
        // true
        System.out.println(B.class.isAssignableFrom(ba.getClass()));
        System.out.println("2-------------");
        // true
        System.out.println(a.getClass().isAssignableFrom(A.class));
        // true
        System.out.println(b.getClass().isAssignableFrom(B.class));
        // true
        System.out.println(a.getClass().isAssignableFrom(B.class));
        // true
        System.out.println(a.getClass().isAssignableFrom(C.class));
        // false
        System.out.println(b.getClass().isAssignableFrom(A.class));
        // true
        System.out.println(ba.getClass().isAssignableFrom(A.class));
        // true
        System.out.println(ba.getClass().isAssignableFrom(B.class));
        System.out.println("3-------------");
        // true
        System.out.println(Object.class.isAssignableFrom(b.getClass()));
        // true
        System.out.println(Object.class.isAssignableFrom("abc".getClass()));
        System.out.println("4-------------");
        // false
        System.out.println("a".getClass().isAssignableFrom(Object.class));
        // false
        System.out.println("abc".getClass().isAssignableFrom(Object.class));
    }
}

class A {
}

class B extends A {
}

class C extends B {
}
