package org.spring.boot.test.niuke.mt52007812;

import java.util.Scanner;

// 4.
// 美团商家的订单发起时，订单编号最开始从 1 开始，后续每发起一个订单，订单编号便在上一订单编号的基础上 +1。为了防止订单号过大，
// 商家还可以设置一个编号上限 m，当订单编号超过m时，将又从 1 开始编号。
// 小美想知道，当订单编号上限为 m时，第 x个订单编号是多少？将有 q次询问。
// 示例
// 4
// 2 3
// 5 17
// 8 2
// 4 4
public class TestNiuKeMain4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        if (1 > q || q > 50000) {
            System.out.println("输入询问次数有误");
            return;
        }

        for (int i = 0; i < q; i++) {
            int m = in.nextInt();
            int x = in.nextInt();
            int a = x % m;
            // System.out.println(m + "," + x + " = " + (a == 0 ? m : a));
            System.out.println(a == 0 ? m : a);
            // if (m == x) {
            //     System.out.println(x);
            // } else {
            //     System.out.println(x % m);
            // }
        }
    }
}