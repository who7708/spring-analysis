package org.spring.boot.test.niuke.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 *
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 *
 * 针对所有的元素重复以上的步骤，除了最后一个。
 *
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/13
 */
public class BubbleSortDemo {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        int[] arr = {9, 20, 3, -10, 0, 15, 7};
        bubbleSort(arr);
        // System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }

    // 冒泡排序
    public void bubbleSort(int[] arr) {
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        if (arr == null || arr.length <= 1) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;
            for (int j = 0; j < arr.length - i; j++) {
                // 降序
                // if (arr[i] < arr[j]) {
                // 升序
                if (arr[j] > arr[j + 1]) {
                    // swap(arr[i], arr[j]);
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                    flag = false;
                }
            }

            if (flag) {
                break;
            }
            System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        }
    }

}
