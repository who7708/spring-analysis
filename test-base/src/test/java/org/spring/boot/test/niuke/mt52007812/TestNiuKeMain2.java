package org.spring.boot.test.niuke.mt52007812;

import java.util.Arrays;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 32
// 246 512 363 219 330 289 742 718 226 951 743 504 178 542 879 491 963 627 295 653 837 471 424 898 110 907 478 187 46 805 814 919
// 结果
// 3629

// 64
// 576 740 867 400 660 144 808 349 252 152 251 883 997 941 671 6 167 590 317 221 567 661 425 613 811 143 540 685 110 189 968 865 1 914 329 780 677 387 250 192 501 868 148 670 63 822 60 879 603 668 374 714 411 647 305 760 104 519 871 88 856 759 740 158
// 8036
public class TestNiuKeMain2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long leastTimes = 0; //最终结果
        int n = 0; //数组个数
        n = sc.nextInt();
        long[] arr = new long[n];
        int t = 0;
        //输入该数组
        while (t < n) {
            arr[t++] = sc.nextInt();
        }
        //求数组之和
        long sum = 0;
        for (long j : arr) {
            sum += j;
        }
        long avg = sum / arr.length;//平均数
        //平均数为整数
        if (sum % arr.length == 0) {
            for (long l : arr) {
                leastTimes += Math.abs(l - avg);
            }
            System.out.println(leastTimes / 2);
            return;
        }
        //平均数除不清
        sum = 0;
        Arrays.sort(arr);
        long[] newArr = new long[arr.length - 1]; //去掉垃圾数之后的数组
        long leastTimes2 = 0;
        //取最小数为垃圾数
        for (int i = 0; i < arr.length - 1; i++) {
            newArr[i] = arr[i + 1];
            sum += arr[i + 1];
        }
        avg = sum / newArr.length;//新数组的平均数
        //新数组平均数为整数
        if (sum % (newArr.length) == 0) {
            //各个数据到平均数的距离之和除以2
            for (long j : newArr) {
                leastTimes += Math.abs(avg - j);
            }
            leastTimes /= 2;
        } else {
            long plus = 0;
            long minus = 0;
            //众数为avg
            for (long value : newArr) {
                //加操作数次数
                if (avg > value) {
                    plus += avg - value;
                }
                //减操作数次数
                else {
                    minus += value - avg;
                }
            }
            leastTimes = Math.max(plus, minus);

            plus = 0;
            minus = 0;
            //众数为avg+1
            avg += 1;
            for (long l : newArr) {
                //加操作数次数
                if (avg > l) {
                    plus += avg - l;
                }
                //减操作数次数
                else {
                    minus += l - avg;
                }
            }
            leastTimes = Math.min(leastTimes, Math.max(plus, minus));
        }
        sum = 0;
        //取最大数为垃圾数
        for (int i = 0; i < arr.length - 1; i++) {
            newArr[i] = arr[i];
            sum += arr[i];
        }
        avg = sum / newArr.length;//新数组的平均数
        //新数组平均数为整数
        if (sum % (newArr.length) == 0) {
            //各个数据到平均数的距离之和除以2
            for (long j : newArr) {
                leastTimes2 += Math.abs(avg - j);
            }
            leastTimes2 /= 2;
        } else {
            long plus = 0;
            long minus = 0;
            //众数为avg
            for (long l : newArr) {
                //加操作数次数
                if (avg > l) {
                    plus += avg - l;
                }
                //减操作数次数
                else {
                    minus += l - avg;
                }
            }
            leastTimes2 = Math.max(plus, minus);

            plus = 0;
            minus = 0;
            //众数为avg+1
            avg += 1;
            for (long l : newArr) {
                //加操作数次数
                if (avg > l) {
                    plus += avg - l;
                }
                //减操作数次数
                else {
                    minus += l - avg;
                }
            }
            leastTimes2 = Math.min(leastTimes2, Math.max(plus, minus));
        }

        System.out.println(Math.min(leastTimes, leastTimes2));
    }
}