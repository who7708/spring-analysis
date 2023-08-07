package org.spring.boot.test.jvm;

import org.junit.Test;

import java.util.Timer;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-13
 */
public class TestOutOfMemory {

    // -ea
    // -Xms100m
    // -Xmx100m
    // -Xmn50m
    // -XX:+UseG1GC
    // -XX:MaxGCPauseMillis=200
    // -XX:+HeapDumpOnOutOfMemoryError
    // -Dfile.encoding=UTF-8
    // 或执行：  jmap：jmap -dump:format=b,file=<filename.hprof> <pid>； 手动生成dump文件
    @Test
    public void testForOOM() throws InterruptedException {
        System.out.println("===== testForOOM =====");
        Timer timer = new Timer();
        timer.schedule(new MyOutOfMemoryTask(), 10000);
        Thread.currentThread().join();
    }

}
