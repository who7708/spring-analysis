package org.spring.boot.test.niuke.all;

import org.junit.Test;

/**
 * 算法代码模板
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here
        long begin = System.nanoTime();
        int code = code();
        System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
        System.out.println("code = " + code);
    }

    public int code() {
        return 0;
    }
}
