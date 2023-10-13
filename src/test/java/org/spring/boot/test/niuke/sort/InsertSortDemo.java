package org.spring.boot.test.niuke.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 插入排序
 * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
 *
 * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/13
 */
public class InsertSortDemo {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        int[] arr = {9, 20, 3, -10, 0, 15, 7};
        insertSort(arr);
        // insertSort2(arr);
    }

    private void insertSort(int[] arr) {
        // System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    arr[i] = arr[i] ^ arr[j];
                    arr[j] = arr[i] ^ arr[j];
                    arr[i] = arr[i] ^ arr[j];
                    // int temp = arr[i];
                    // arr[i] = arr[j];
                    // arr[j] = temp;
                }
            }
            System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        }
    }

    private void insertSort2(int[] arr) {
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {
            // 记录要插入的数据
            int tmp = arr[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
            System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        }
    }
}
