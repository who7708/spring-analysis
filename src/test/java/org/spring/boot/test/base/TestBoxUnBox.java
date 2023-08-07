package org.spring.boot.test.base;

import org.junit.Test;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-07
 */
public class TestBoxUnBox {
    @Test
    public void test1() {
        int a = 0;
        // 自动装箱操作
        // INVOKESTATIC java/lang/Integer.valueOf (I)Ljava/lang/Integer;
        Integer b = a;
        // 自动解箱操作
        // INVOKEVIRTUAL java/lang/Integer.intValue ()I
        int c = b;
    }
}
