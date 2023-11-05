package org.spring.boot.test.mist;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/*
 * 薄雾算法
 *
 * 1      2                                                     48         56       64
 * +------+-----------------------------------------------------+----------+----------+
 * retain | increas                                             | salt     | sequence |
 * +------+-----------------------------------------------------+----------+----------+
 * 0      | 0000000000 0000000000 0000000000 0000000000 0000000 | 00000000 | 00000000 |
 * +------+-----------------------------------------------------+------------+--------+
 *
 * 0. 最高位，占 1 位，保持为 0，使得值永远为正数；
 * 1. 自增数，占 47 位，自增数在高位能保证结果值呈递增态势，遂低位可以为所欲为；
 * 2. 随机因子一，占 8 位，上限数值 255，使结果值不可预测；
 * 3. 随机因子二，占 8 位，上限数值 255，使结果值不可预测；
 *
 * 编号上限为百万亿级，上限值计算为 140737488355327 即 int64(1 << 47 - 1)，假设每天取值 10 亿，能使用 385+ 年
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/11/02
 */
public class Mist {
    // 随机因子二进制位数
    private static final int saltBit = 8;

    // 随机因子移位数
    private static final int saltShift = 8;

    // 自增数移位数
    private static final int increaseShift = saltBit + saltShift;

    private static final AtomicLong increase = new AtomicLong(1L);

    // 生成的是伪随机数
    private static final Random RANDOM = new Random();

    private long generate() {
        long i = increase.incrementAndGet();
        // 获取随机因子数值 ｜ 使用真随机函数提高性能
        long saltA = nextLong(0, 255);
        long saltB = nextLong(0, 255);
        // 通过位运算实现自动占位
        long mist = (i << increaseShift) | saltA << saltShift | saltB;
        return mist;
    }

    private long nextLong(final long startInclusive, final long endExclusive) {
        if (startInclusive == endExclusive) {
            return startInclusive;
        }
        return startInclusive + nextLong(endExclusive - startInclusive);
    }

    private long nextLong(final long n) {
        // Extracted from o.a.c.rng.core.BaseProvider.nextLong(long)
        long bits;
        long val;
        do {
            bits = RANDOM.nextLong() >>> 1;
            val = bits % n;
        } while (bits - val + (n - 1) < 0);

        return val;
    }

    public static void main(String[] args) {
        Mist mist = new Mist();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                long generate = mist.generate();
                System.out.println(generate);
            }).start();
        }
        
    }
}
