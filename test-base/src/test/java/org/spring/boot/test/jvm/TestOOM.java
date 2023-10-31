package org.spring.boot.test.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-24
 */
public class TestOOM {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, Object> m = new HashMap<>();
        for (int i = 0; i < 10_000; i++) {
            System.out.println("循环 " + (i + 1) + " 次");
            byte[] bytes = new byte[1024 * 1024];
            m.put(i, bytes);
            TimeUnit.MILLISECONDS.sleep(300);
        }
        System.out.println("finish oom..." + m.size());
    }
}

// -ea
// -Xms100m
// -Xmx100m
// -XX:+UseG1GC
// -XX:MaxGCPauseMillis=200
// -XX:+HeapDumpOnOutOfMemoryError
// -XX:+PrintGCDetails
// -Dfile.encoding=UTF-8
// 或执行：  jmap：jmap -dump:format=b,file=<filename.hprof> <pid>； 手动生成dump文件

// -Xms 初始化堆大小
// -Xmx 堆大小
// -Xmn 年轻代大小， 老年代的大小就等于堆大小减去年轻代大小
// java -Xms20m -Xmn10M TestGC
