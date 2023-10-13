package org.spring.boot.test.niuke.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 合并排序
 * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
 *
 * 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
 *
 * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
 *
 * 重复步骤 3 直到某一指针达到序列尾；
 *
 * 将另一序列剩下的所有元素直接复制到合并序列尾。
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/13
 */
public class MergeSortDemo {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        int[] arr = {9, 20, 3, -10, 0, 15, 7};
        arr = mergeSort(arr);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }

    private int[] mergeSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2.0);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(mergeSort(left), mergeSort(right));
    }

    protected int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }

}
