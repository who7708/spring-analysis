package com.example.demo.algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * java 常用算法
 * 1. 二分查找
 * 2. 冒泡排序
 * 3. 插入排序
 * 4. 快速排序
 * 5. 希尔排序
 * 6. 归并排序
 * 7. 桶排序
 * 8. 基数排序
 * 9. 剪枝排序
 * 10. 最短路径算法
 * 11. 最大子数组算法
 * 12. 最长公共子序算法
 * 13. 最小生成树算法
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-30
 */
public class TestAlgorithm {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        int[] arr = new int[]{3, 4, 2, 1, 5, 62, 345, 76, 34};
        System.out.println("原始数组：" + Arrays.toString(arr));
        // bubbleSort(arr, arr.length);
        // insertSort(arr);
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后数组：" + Arrays.toString(arr));
        int index = biSearch(arr, 76);
        System.out.println("二分查找元素在下标为 " + index + " 位置");
    }

    // 又叫折半查找，要求待查找的序列有序。每次取中间位置的值与待查关键字比较，如果中间位置
    // 的值比待查关键字大，则在前半部分循环这个查找的过程，如果中间位置的值比待查关键字小，
    // 则在后半部分循环这个查找的过程。直到查找到了为止，否则序列中没有待查的关键字。
    public static int biSearch(int[] array, int a) {
        int lo = 0;
        int hi = array.length - 1;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) / 2;//中间位置
            if (array[mid] == a) {
                // return mid + 1;
                return mid;
            } else if (array[mid] < a) { //向右查找
                lo = mid + 1;
            } else { //向左查找
                hi = mid - 1;
            }
        }
        return -1;
    }

    // （1）比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。
    // （2）这样对数组的第0 个数据到N-1 个数据进行一次遍历后，最大的一个数据就“沉”到数组第 N-1 个位置。
    // （3）N=N-1，如果N 不为0 就重复前面二步，否则排序完成。
    public static void bubbleSort(int[] a, int n) {
        int i, j;
        for (i = 0; i < n; i++) {//表示n 次排序过程。
            for (j = 1; j < n - i; j++) {
                if (a[j - 1] > a[j]) {//前面的数字大于后面的数字就交换
                    //交换a[j-1]和a[j]
                    int temp;
                    temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    // 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应的位置并插入。
    // 插入排序非常类似于整扑克牌。在开始摸牌时，左手是空的，牌面朝下放在桌上。接着，一次从
    // 桌上摸起一张牌，并将它插入到左手一把牌中的正确位置上。为了找到这张牌的正确位置，要将
    // 它与手中已有的牌从右到左地进行比较。无论什么时候，左手中的牌都是排好序的。
    // 如果输入数组已经是排好序的话，插入排序出现最佳情况，其运行时间是输入规模的一个线性函
    // 数。如果输入数组是逆序排列的，将出现最坏情况。平均情况与最坏情况一样，其时间代价是(n2)。
    public static void insertSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            //插入的数
            int insertVal = arr[i];
            //被插入的位置(准备和前一个数比较)
            int index = i - 1;
            //如果插入的数比被插入的数小
            while (index >= 0 && insertVal < arr[index]) {
                //将把arr[index] 向后移动
                arr[index + 1] = arr[index];
                //让index 向前移动
                index--;
            }
            //把插入的数放入合适位置
            arr[index + 1] = insertVal;
        }
    }

    // 快速排序的原理：选择一个关键值作为基准值。比基准值小的都在左边序列（一般是无序的），
    // 比基准值大的都在右边（一般是无序的）。一般选择序列的第一个元素。
    // 一次循环：从后往前比较，用基准值和最后一个值比较，如果比基准值小的交换位置，如果没有
    // 继续比较下一个，直到找到第一个比基准值小的值才交换。找到这个值之后，又从前往后开始比
    // 较，如果有比基准值大的，交换位置，如果没有继续比较下一个，直到找到第一个比基准值大的
    // 值才交换。直到从前往后的比较索引>从后往前比较的索引，结束第一次循环，此时，对于基准值
    // 来说，左右两边就是有序的了。
    public static void quickSort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];
        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key)
            //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
            {
                end--;
            }
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && a[start] <= key)
            //如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
            {
                start++;
            }
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的
            // 值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) {
            quickSort(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        }
        if (end < high) {
            quickSort(a, end + 1, high);//右边序列。从关键值索引+1 到最后一个
        }
    }
}
