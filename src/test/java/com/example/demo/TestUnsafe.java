package com.example.demo;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/04/12
 */
public class TestUnsafe {
    private int i = 0;
    private static TestUnsafe t = new TestUnsafe();
    //
    // private static final Unsafe unsafe = Unsafe.getUnsafe();
    // private static final long valueOffset;
    //
    // static {
    //     try {
    //         valueOffset = unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("i"));
    //     } catch (Exception ex) {
    //         throw new Error(ex);
    //     }
    // }

    @Test
    public void test1() throws IllegalAccessException, NoSuchFieldException {
        System.out.println("===== test1 ====");

        // 8个基本数据类型
        // Byte.SIZE 1B
        // Character.SIZE 2B
        // Short.SIZE 2B
        // Integer.SIZE; 4B
        // Long.SIZE 8B
        // Float.SIZE 4B
        // Double.SIZE; 8B
        // Boolean.szi

        // Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        Field unsafeField = null;

        for (Field field : Unsafe.class.getDeclaredFields()) {
            System.out.println(field.getName());
            if ("theUnsafe".equalsIgnoreCase(field.getName())) {
                unsafeField = field;
                break;
            }
        }
        if (null == unsafeField) {
            return;
        }

        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        long valueOffset = unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("i"));

        boolean success = unsafe.compareAndSwapInt(t, valueOffset, 1, 1);
        System.out.println(success);
        System.out.println(t.i);
    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void test2() {
        System.out.println("===== test2 ====");
        System.out.println(getNum());
    }

    public int getNum() {
        try {
            int a = 1 / 0;
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }

}
