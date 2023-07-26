package org.spring.boot.test;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-26
 */
public class TestStackOverflowErrorError {

    private int stackLength = 0;

    public void stackLeak() {
        stackLength++;
        // 递归调用，导致 sof
        stackLeak();
    }

    // -verbose:gc -Xss128k -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\dump
    public static void main(String[] args) {
        TestStackOverflowErrorError sof = new TestStackOverflowErrorError();
        try {
            sof.stackLeak();
        } catch (Exception e) {
            System.out.println(sof.stackLength);
            e.printStackTrace();
        }
    }
}
