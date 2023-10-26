package org.spring.boot.test.jvm;

import lombok.Data;

/**
 * 测试逃逸分析
 * <a href="https://juejin.cn/post/6996918514082643975">...</a>
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/26
 */
public class TestDoEscapeAnalysis {
    // -Xmx2G -Xms2G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
    //  -XX:+PrintEscapeAnalysis
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000000; i++) {
            alloc();
        }

        Thread.sleep(100000);
    }

    private static void alloc() {
        User user = new User();
    }

    @Data
    private static class User {
        private String name;

        private int age;

        private int sex;

    }
}
