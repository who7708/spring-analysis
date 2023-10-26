package com.example.bit;

import java.util.BitSet;

public class MyBloomFilter {

    // 默认大小
    private static final int DEFAULT_SIZE = Integer.MAX_VALUE;

    // 最小的大小
    private static final int MIN_SIZE = 1000;

    // 大小为默认大小
    private int SIZE = DEFAULT_SIZE;

    // hash函数的种子因子
    private static final int[] HASH_SEEDS = new int[]{3, 5, 7, 11, 13, 17, 19, 23, 29, 31};

    // 位数组，0/1,表示特征
    private BitSet bitSet = null;

    // hash函数
    private final HashFunction[] hashFunctions = new HashFunction[HASH_SEEDS.length];

    // 无参数初始化
    public MyBloomFilter() {
        // 按照默认大小
        init();
    }

    // 带参数初始化
    public MyBloomFilter(int size) {
        // 大小初始化小于最小的大小
        if (size >= MIN_SIZE) {
            SIZE = size;
        }
        init();
    }

    private void init() {
        // 初始化位大小
        bitSet = new BitSet(SIZE);
        // 初始化hash函数
        for (int i = 0; i < HASH_SEEDS.length; i++) {
            hashFunctions[i] = new HashFunction(SIZE, HASH_SEEDS[i]);
        }
    }

    // 添加元素，相当于把元素的特征添加到位数组
    public void add(Object value) {
        for (HashFunction f : hashFunctions) {
            // 将hash计算出来的位置为true
            bitSet.set(f.hash(value), true);
        }
    }

    // 判断元素的特征是否存在于位数组
    public boolean contains(Object value) {
        for (HashFunction f : hashFunctions) {
            boolean result = bitSet.get(f.hash(value));
            // hash函数只要有一个计算出为false，则直接返回
            if (!result) {
                return false;
            }
        }
        return true;
    }

    // hash函数
    public static class HashFunction {
        // 位数组大小
        private final int size;

        // hash种子
        private final int seed;

        public HashFunction(int size, int seed) {
            this.size = size;
            this.seed = seed;
        }

        // hash函数
        public int hash(Object value) {
            if (value == null) {
                return 0;
            } else {
                // hash值
                int hash1 = value.hashCode();
                // 高位的hash值
                int hash2 = hash1 >>> 16;
                // 合并hash值(相当于把高低位的特征结合)
                int combine = hash1 ^ hash2;
                // 相乘再取余
                return Math.abs(combine * seed) % size;
            }
        }

    }

    public static void main(String[] args) {
        Integer num1 = new Integer(12321);
        Integer num2 = new Integer(12345);
        MyBloomFilter myBloomFilter = new MyBloomFilter();
        // 一定不在集合中
        System.out.println(myBloomFilter.contains(num1));
        System.out.println(myBloomFilter.contains(num2));

        myBloomFilter.add(num1);
        myBloomFilter.add(num2);

        // 可能在集合中
        System.out.println(myBloomFilter.contains(num1));
        System.out.println(myBloomFilter.contains(num2));

    }
}