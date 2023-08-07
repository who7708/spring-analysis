package org.spring.boot.test.base;

import org.junit.Test;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-07
 */
public class TestSwitch {
    @Test
    public void test1() {
        System.out.println("===== test1 =====");
        // byte short int char String 都支持
        char a = 'A';
        switch (a) {
            case 'A':
                a += a;
                System.out.println("sdsd" + a);
                break;
            case 'B':
                System.out.println("sss" + a);
                break;
            default:
                break;

        }
    }
}
