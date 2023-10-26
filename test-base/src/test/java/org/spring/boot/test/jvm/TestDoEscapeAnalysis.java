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
    // -Xmx64m -Xms64m -Xmn32m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
    //  -XX:+PrintEscapeAnalysis
    // 发生 MinorGC 后，在栈上分配的对象会被回收，即逃逸分析发生作用
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
