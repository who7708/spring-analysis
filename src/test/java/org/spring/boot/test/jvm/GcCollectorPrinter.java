package org.spring.boot.test.jvm;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GcCollectorPrinter {
    public static void main(String[] args) {
        List<GarbageCollectorMXBean> beans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean bean : beans) {
            System.out.println(bean.getName());
        }
    }
}
// UseG1GC
// UseConcMarkSweepGC || UseParNewGC
// UseParallelGC || UseParallelOldGC 两个是相同的结果
// UseSerialGC
// java -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError GcCollectorPrinter
// java -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+UseParNewGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError GcCollectorPrinter
// java -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError GcCollectorPrinter
// java -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+UseParallelOldGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError GcCollectorPrinter
// java -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError GcCollectorPrinter
// java -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError GcCollectorPrinter
