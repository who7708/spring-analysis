package com.example.demo.reflect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 运行时动态的获取信息以及调用对象的方法的功能称为 java 的反向机制。
 * 对于任意一个类，都能够获取这个类的所有的属性和方法。对于任意一个对象都能够调用它的任意一个属性和方法
 *
 * Java 反射效率低主要原因是：
 *
 * Method#invoke 方法会对参数做封装和解封操作,因为封装和解封，产生了额外的不必要的内存浪费，当调用次数达到一定量的时候，还会导致 GC。
 * 需要检查方法可见性
 * 需要校验参数
 * 反射方法难以内联
 * JIT 无法优化, 因为反射涉及到动态加载的类型，所以无法进行优化。
 */
public class ReflectTest {

    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        Class clazz = Class.forName(Apple.class.getName());
        Constructor<Fruit> constructor1 = clazz.getConstructor();
        Constructor<Fruit> constructor2 = clazz.getConstructor(String.class);
        Fruit fruit1 = constructor1.newInstance();
        Fruit fruit2 = constructor2.newInstance("Apple");

        Field[] declaredFields = clazz.getDeclaredFields();
        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();

        // 返回所有方法，包括父类及自己定义的
        Method[] methods = clazz.getMethods();
        // 仅返回自己定义的方法
        Method[] declaredMethods = clazz.getDeclaredMethods();

        Method refMethod = clazz.getMethod("refMethod");
        Object invoke = refMethod.invoke(fruit1);
        System.out.println(invoke);

        // 关于 getXXX() 与 getDeclaredXXX() 区别。可查看对应方法，在检查权限时传入的 Member.PUBLIC 还是 Member.DECLARED
        // PUBLIC 会包括所有的 public 方法，包括父类的方法，
        // 而 DECLARED 会包括所有自己定义的方法，public，protected，private 都在此，但是不包括父类的方法
        System.out.println("end....");
    }

    @Test
    public void test2() throws Exception {
        System.out.println("===== test2 =====");
        // 获取Person 类的Class 对象
        System.out.println("获取Person 类的Class 对象");
        Class clazz = Class.forName("com.example.demo.reflect.Person");

        System.out.println("获取Person 类的所有方法信息");
        // 获取Person 类的所有方法信息（包含继承的方法）
        Method[] method = clazz.getDeclaredMethods();
        for (Method m : method) {
            System.out.println(m.toString());
        }
        System.out.println("获取Person 类的所有成员属性信息");
        // 获取Person 类的所有成员属性信息
        Field[] field = clazz.getDeclaredFields();
        for (Field f : field) {
            System.out.println(f.toString());
        }
        // 获取Person 类的所有构造方法信息
        System.out.println("获取Person 类的所有构造方法信息");
        Constructor[] constructor = clazz.getDeclaredConstructors();
        for (Constructor c : constructor) {
            System.out.println(c.toString());
        }
    }

    @Test
    public void test3() throws Exception {
        System.out.println("===== test3 =====");
        //获取Person 类的Class 对象
        Class clazz = Class.forName("com.example.demo.reflect.Person");
        //使用.newInstane 方法创建对象
        Person p = (Person) clazz.newInstance();
        //获取构造方法并创建对象
        Constructor c = clazz.getDeclaredConstructor(String.class, String.class, int.class);
        //创建对象并设置属性
        Person p1 = (Person) c.newInstance("李四", "男", 20);
        System.out.println(p1);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private String name;
    private String sex;
    private int age;
}

class Fruit {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fruit() {
        System.out.println("无参构造器 Run...........");
    }

    public Fruit(String type) {
        System.out.println("有参构造器 Run..........." + type);
    }

    public void refMethod() {
        System.out.println("反射调用方法 ..................");
    }
}

class Apple extends Fruit {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Apple() {
        super();
    }

    public Apple(String type) {
        super(type);
    }
}

// java 反射：
// 获取反射的方法：
//    getMethod/getDeclaredMthod
//    checkMemberAccess
//    getMethod0()