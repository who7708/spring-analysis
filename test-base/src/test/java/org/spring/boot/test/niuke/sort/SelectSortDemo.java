package org.spring.boot.test.niuke.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 选择排序
 *
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 *
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 *
 * 重复第二步，直到所有元素均排序完毕。
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/13
 */
public class SelectSortDemo {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");

        int[] arr = {9, 20, 3, -10, 0, 15, 7};
        selectSortDemo(arr);
    }

    private void selectSortDemo(int[] arr) {
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            // 每轮需要比较的次数 N-i
            for (int j = i; j < arr.length; j++) {
                // 每次循环找到最小值的索引
                if (arr[minIdx] > arr[j]) {
                    minIdx = j;
                }
            }
            // 如果最小值不是当前索引,则进行交换
            if (i != minIdx) {
                arr[i] = arr[i] ^ arr[minIdx];
                arr[minIdx] = arr[i] ^ arr[minIdx];
                arr[i] = arr[i] ^ arr[minIdx];
            }
            System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        }
    }
}
