package com.example.demo.queue;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/11/13
 */
public class TestPriorityQueue {
    @Test
    public void test2() throws Exception {
        System.out.println("===== test2 =====");

        // 初始化小顶堆, 默认就是小顶堆
        Queue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int[] array = {6, 9, 8, 1, 4, 2, 3, 5, 7};
        for (int i : array) {
            // minHeap.add(i);
            // minHeap.offer(i);
            minHeap.offer(i);
        }
        // 弹出元素
        // minHeap.poll()
        // 带检查
        // minHeap.element()
        // 获取堆顶元素
        // minHeap.peek()

        System.out.println(minHeap.peek());
    }

    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        int[] array = {6, 9, 8, 1, 4, 2, 3, 5, 7};
        // int[] array = {4, 5, 1, 9, 8, 0, 3, 2};
        /* 初始化堆 */
        // 初始化小顶堆
        Queue<Integer> minHeap = new PriorityQueue<>();
        // 初始化大顶堆（使用 lambda 表达式修改 Comparator 即可）
        Queue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        /* 元素入堆 */
        maxHeap.offer(1);
        maxHeap.offer(3);
        maxHeap.offer(2);
        maxHeap.offer(5);
        maxHeap.offer(4);

        /* 获取堆顶元素 */
        int peek = maxHeap.peek(); // 5

        /* 堆顶元素出堆 */
        // 出堆元素会形成一个从大到小的序列
        peek = maxHeap.poll(); // 5
        peek = maxHeap.poll(); // 4
        peek = maxHeap.poll(); // 3
        peek = maxHeap.poll(); // 2
        peek = maxHeap.poll(); // 1

        /* 获取堆大小 */
        int size = maxHeap.size();

        /* 判断堆是否为空 */
        boolean isEmpty = maxHeap.isEmpty();

        // /* 输入列表并建堆 */
        // minHeap = new PriorityQueue<>(Arrays.asList(1, 3, 2, 5, 4));

    }
}
