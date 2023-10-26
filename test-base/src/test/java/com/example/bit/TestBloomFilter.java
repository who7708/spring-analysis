package com.example.bit;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/26
 */
public class TestBloomFilter {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        // 返回true表示元素有极大的概率存在。当返回false那么表示元素一定不存在
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                5_000_000,
                0.01);
        IntStream.range(0, 100_000).forEach(filter::put);
        System.out.println("filter.mightContain(1) = " + filter.mightContain(1));
        System.out.println("filter.mightContain(2) = " + filter.mightContain(2));
        System.out.println("filter.mightContain(3) = " + filter.mightContain(3));
        System.out.println("filter.mightContain(100) = " + filter.mightContain(100));
        System.out.println("filter.mightContain(1000010) = " + filter.mightContain(1000010));

    }

    @Test
    public void test2() throws Exception {
        System.out.println("===== test1 =====");
        // 返回true表示元素有极大的概率存在。当返回false那么表示元素一定不存在
        BloomFilter<String> filter = BloomFilter.create(
                Funnels.stringFunnel(StandardCharsets.UTF_8),
                500,
                0.01);
        filter.put("1");
        filter.put("2");
        filter.put("3");
        System.out.println("filter.mightContain(1) = " + filter.mightContain("1"));
        System.out.println("filter.mightContain(2) = " + filter.mightContain("2"));
        System.out.println("filter.mightContain(3) = " + filter.mightContain("3"));
        System.out.println("filter.mightContain(100) = " + filter.mightContain("100"));

    }
}
