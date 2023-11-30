package org.spring.boot.test.niuke.all;

import org.junit.Test;

import java.util.Arrays;

/**
 * NC140 排序
 *
 * <a
 * href="https://www.nowcoder.com/practice/2baf799ea0594abd974d37139de27896?tpId=196&ru=%2Fexam%2Foj&difficulty=&judgeStatus=&tags=&title=%E6%8E%92%E5%BA%8F&sourceUrl=&gioEnter=menu">...</a>
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC40 {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here
        int[] arr = {5, 2, 3, 1, 4};
        // int[] arr = {5,1,6,2,5};
        long begin = System.nanoTime();
        int[] mySort = MySort(arr);
        System.out.printf("(System.nanoTime() - begin) = %d ns \n", (System.nanoTime() - begin));
        System.out.println("MySort = " + Arrays.toString(mySort));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 将给定数组排序
     *
     * @param arr int整型一维数组 待排序的数组
     * @return int整型一维数组
     */
    public int[] MySort(int[] arr) {
        // write code here
        // Arrays.sort(arr);
        insertSort(arr);
        return arr;
    }

    public void insertSort(int[] arr) {
        for (int i = 0, j = i; i < arr.length - 1; j = ++i) {
            int ai = arr[i + 1];
            while (ai < arr[j]) {
                arr[j + 1] = arr[j];
                if (j-- == 0) {
                    break;
                }
            }
            arr[j + 1] = ai;
        }
    }
}
