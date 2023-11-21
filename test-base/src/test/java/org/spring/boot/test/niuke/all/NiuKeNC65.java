package org.spring.boot.test.niuke.all;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * NC65 斐波那契数列
 * <a href="https://www.nowcoder.com/practice/c6c7742f5ba7442aada113136ddea0c3">...</a>
 * 0,1,1,2,3,5,8,13...
 * f(n) = f(n-1) + f(n-2)
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/11/21
 */
public class NiuKeNC65 {

    @Test
    public void fibonacciByRecursionTest() throws Exception {
        System.out.println("===== test1 =====");
        {
            // 20 很快算出，但是超过就非常慢
            long begin = System.nanoTime();
            long fibonacciByRecursion = fibonacciByRecursion(20);
            System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
            System.out.println("fibonacciByRecursion = " + fibonacciByRecursion);
        }

        {
            // 有缓存版本，快很多
            long begin = System.nanoTime();
            long fibonacciByRecursion = fibonacciByRecursion(20, new HashMap<>());
            System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
            System.out.println("fibonacciByRecursion = " + fibonacciByRecursion);
        }
    }

    @Test
    public void fibonacciByDpTest() throws Exception {
        System.out.println("===== test1 =====");
        long begin = System.nanoTime();
        // long fibonacciByDp = fibonacciByDp(200);
        long fibonacciByDp = fibonacciByDp(4);
        System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
        System.out.println("fibonacciByDp = " + fibonacciByDp);
    }

    // 递归方式实现
    public long fibonacciByRecursion(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return fibonacciByRecursion(n - 1) + fibonacciByRecursion(n - 2);
    }

    // 递归方式实现
    public long fibonacciByRecursion(int n, Map<Integer, Long> cacheMap) {
        if (n == 1 || n == 2) {
            return n;
        }

        if (cacheMap.containsKey(n)) {
            return cacheMap.get(n);
        }

        long res = fibonacciByRecursion(n - 1, cacheMap) + fibonacciByRecursion(n - 2, cacheMap);
        cacheMap.put(n, res);
        return res;
    }

    // 动态规划方式实现
    public long fibonacciByDp(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        long p1 = 1;
        long p2 = 1;
        long r = 0;
        for (int i = 2; i < n; i++) {
            r = p1 + p2;
            p1 = p2;
            p2 = r;
        }
        return r;
    }
}
