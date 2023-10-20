package com.example.demo;

import java.util.Arrays;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/06/01
 */
public class TestNumber {
    // 输出最大数字
    public static void main(String[] args) throws Exception {
        int i = 128923412;
        final String s = Integer.toString(i);
        final char[] chars = s.toCharArray();
        final BubbleSort bubbleSort = new BubbleSort();
        final char[] sort = bubbleSort.sort(chars);
        System.out.println(sort);
    }

    static class BubbleSort implements IArraySort {

        @Override
        public char[] sort(char[] sourceArray) throws Exception {
            // 对 arr 进行拷贝，不改变参数内容
            char[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

            for (int i = 1; i < arr.length; i++) {
                // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
                boolean flag = true;
                for (char j = 0; j < arr.length - i; j++) {
                    // 倒序
                    if (arr[j] < arr[j + 1]) {
                        char tmp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = tmp;
                        flag = false;
                    }
                }

                if (flag) {
                    break;
                }
            }
            return arr;
        }
    }

    interface IArraySort {
        char[] sort(char[] sourceArray) throws Exception;
    }
}
