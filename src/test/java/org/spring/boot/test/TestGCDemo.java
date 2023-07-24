package org.spring.boot.test;

/**
 * @author Chris
 * @date 2018.09.29
 */
public class TestGCDemo {
    public static void main(String[] args) {
        // allocate 4M space
        byte[] b = new byte[4 * 1024 * 1024];
        System.out.println("first allocate");
        // allocate 4M space
        b = new byte[4 * 1024 * 1024];
        System.out.println("second allocate");
    }
}
// java -Xms20m -Xmx20m -Xmn10M -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps TestGCDemo