package org.spring.boot.test.niuke.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 希尔排序
 * 希尔排序的基本思想是：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录"基本有序"时，再对全体记录进行依次直接插入排序。
 *
 * 算法步骤
 * 选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；
 *
 * 按增量序列个数 k，对序列进行 k 趟排序；
 *
 * 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进行直接插入排序。仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/13
 */
public class ShellSortDemo {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        int[] arr = {9, 20, 3, -10, 0, 15, 7};
        shellSort(arr);
    }

    private void shellSort(int[] arr) {
        int length = arr.length;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                int temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
                System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
            }
            // System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        }
    }

}
