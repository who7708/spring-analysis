package org.spring.boot.test.niuke.mt157;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 1.
// k链表翻转。给出一个链表和一个数k，比如链表1→2→3→4→5→6，k=2，则翻转后2→1→4→3→6→5，若k=3,翻转后3→2→1→6→5→4，若k=4，翻转后4→3→2→1→5→6，用程序实现。
public class TestNiuKe157Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 链表切分子链的元素个数
        int count = in.nextInt();

        List<Integer> list = new LinkedList<>();
        for (int i = 1; i < 7; i++) {
            list.add(i);
        }

        List<List<Integer>> childrenList = subList(list, count);
        List<Integer> res = new LinkedList<>();
        for (List<Integer> subList : childrenList) {
            if (subList.size() == count) {
                // 反转后放入链表
                for (int i = subList.size() - 1; i >= 0; i--) {
                    res.add(subList.get(i));
                }
            } else {
                // 不需要反转的全部放入链表
                res.addAll(subList);
            }
        }
        System.out.println(res);
    }

    private static List<List<Integer>> subList(List<Integer> list, int groupSize) {
        List<List<Integer>> res = new ArrayList<>();
        // 总元素个数
        int size = list.size();
        int childListCont = (int) Math.ceil(size * 1.0D / groupSize);
        for (int i = 0; i < childListCont; i++) {
            // 开始位置
            int fromIndex = i * groupSize;
            // 结束位置
            int toIndex = Math.min((i + 1) * groupSize, size);
            List<Integer> subList = list.subList(fromIndex, toIndex);
            res.add(subList);
        }
        return res;
    }
}