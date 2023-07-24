
/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-24
 */
public class TestTenuringThreshold {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM参数：
     * -verbose:gc
     * -Xms20M
     * -Xmx20M
     * -Xmn10M
     * -XX:+PrintGCDetails
     * -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=1
     * -XX:+PrintTenuringDistribution
     * -XX:+UseSerialGC
     */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        //内存 A
        allocation1 = new byte[_1MB / 4]; // 什么时候进入老年代决定于XX:MaxTenuringThreshold设置
        //内存 B
        allocation2 = new byte[4 * _1MB];
        //内存 C
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        //内存 D
        allocation3 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        testTenuringThreshold();
    }

}

// -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution -XX:+UseSerialGC