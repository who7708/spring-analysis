package org.spring.boot.test.niuke.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 快速排序
 * 算法步骤
 * 从数列中挑出一个元素，称为 "基准"（pivot）;
 *
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 *
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/13
 */
public class QuickSortDemo {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");

        int[] arr = {9, 20, 3, -10, 0, 15, 7};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }

    private int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot），即中心点
        int pivot = left + 1;
        for (int i = pivot; i <= right; i++) {
            if (arr[i] < arr[left]) {
                swap(arr, i, pivot);
                pivot++;
            }
        }
        swap(arr, left, pivot - 1);
        return pivot - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        // arr[i] = arr[i] ^ arr[j];
        // arr[j] = arr[i] ^ arr[j];
        // arr[i] = arr[i] ^ arr[j];
    }

}
