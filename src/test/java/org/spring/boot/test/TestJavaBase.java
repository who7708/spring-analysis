package org.spring.boot.test;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-17
 */
public class TestJavaBase {
    @Test
    public void test1() {
        System.out.println("===== test1 =====");
        Integer i1 = 127;
        Integer i2 = 127;
        int ii3 = i1;
        // 输出 true
        System.out.println(i1 == i2);
        Float i11 = 333f;
        Float i22 = 333f;
        // 输出 false
        System.out.println(i11 == i22);
        Double i3 = 1.2;
        Double i4 = 1.2;
        // 输出 false
        System.out.println(i3 == i4);
    }

    @Test
    public void should_Throw_NullPointerException() {
        // 此处会产生 NPE， longValue
        long id = getNum();
    }

    public Long getNum() {
        return null;
    }

    @Test
    public void test2() {
        System.out.println("===== test2 =====");
        Integer i = null;
        Boolean flag = false;
        System.out.println(flag ? 0 : i);
    }

    @Test
    public void test3() throws Exception {
        System.out.println("===== test3 =====");
        String s = "Hydra";
        System.out.println(s.hashCode());
        s = "Trunks";
        System.out.println(s.hashCode());

        // 修改原String对象的值且不改变其引用地址
        String a = "Hydra";
        System.out.println(a + ":  " + a.hashCode());
        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);
        field.set(a, new char[]{'T', 'r', 'u', 'n', 'k', 's'});
        System.out.println(a + ": " + a.hashCode());

    }

    @Test
    public void test4() {
        System.out.println("===== test4 =====");
        String a = "Hydra";
        System.out.println(a + ":  " + a.hashCode());
    }

}
