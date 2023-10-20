package org.spring.boot.test.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-10
 */
public class TestHeap {

    byte[] a = new byte[100 * 1024]; // 100 KB

    // java -Xms20m -Xmx20m -Xmn10M \
    // -XX:SurvivorRatio=2 \
    // -XX:+PrintGCDetails \
    // -XX:+PrintGCTimeStamps \
    // -Xloggc:./logs/gc.log \
    // -XX:+HeapDumpOnOutOfMemoryError \
    // -XX:HeapDumpPath=./logs/java.hprof \
    // -Djava.rmi.server.hostname=192.168.1.5 \
    // -Dcom.sun.management.jmxremote=true \
    // -Dcom.sun.management.jmxremote.port=33306 \
    // -Dcom.sun.management.jmxremote.authenticate=false \
    // -Dcom.sun.management.jmxremote.ssl=false \
    // TestHeap
    public static void main(String[] args) throws Exception {
        List<TestHeap> test = new ArrayList<>();
        while (true) {
            test.add(new TestHeap());
            TimeUnit.MILLISECONDS.sleep(10000);
        }

    }
}
