package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author Chris
 * @date 2020/05/14
 * @since 1.0.0
 */
public class TestCloneable {
    private static final MapperFactory MAPPER_FACTORY;

    static {
        // 初始化 factory
        MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();
        // 注册字段映射关系
        MAPPER_FACTORY.classMap(B.class, A.class)
                .field("date", "date")
                .register();

    }

    private static MapperFacade getMapperFacade() {
        return MAPPER_FACTORY.getMapperFacade();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        C c = new C();
        c.setCpu("intel");
        B b = new B();
        b.setAddress("shanghai");
        A a = new A();
        a.setName("Chris");
        a.setB(b);
        b.setC(c);

        System.out.println(a);
        A aClone = (A) a.clone();
        // 浅拷贝是指拷贝对象时仅仅拷贝对象本身（包括对象中的基本变量），而不拷贝对象包含的引用指向的对象。深拷
        // 贝不仅拷贝对象本身，而且拷贝对象包含的引用指向的所有对象
        // 结果依次为：false， true， true
        System.out.println(aClone.equals(a)); // false
        System.out.println(aClone.getB().equals(b)); // true
        System.out.println(aClone.getB().getC().equals(c)); // true
        System.out.println(aClone);

        // 使用 orika 拷贝对象，深拷贝
        A aClone2 = getMapperFacade().map(a, A.class);
        // 结果依次为：false， false， false
        System.out.println(aClone2.equals(a)); // false
        System.out.println(aClone2.getB().equals(b)); // true
        System.out.println(aClone2.getB().getC().equals(c)); // true
        System.out.println(aClone2);
    }

}

@Setter
@Getter
class A implements Cloneable {
    private String name;
    private B b;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

@Setter
@Getter
class B implements Cloneable {
    private String address;
    private C c;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

@Setter
@Getter
class C implements Cloneable {
    private String cpu;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
