package org.spring.boot.test.niuke.all;

public class HanoiTower {
    public static void main(String[] args) {
        move(4, "A", "B", "C");
    }

    /**
     * 移动函数
     *
     * @param n    盘子的数量
     * @param from 起始柱子
     * @param aux  辅助柱子
     * @param to   目标柱子
     */
    public static void move(int n, String from, String aux, String to) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + from + " to " + to);
        } else {
            move(n - 1, from, to, aux);
            System.out.println("Move disk " + n + " from " + from + " to " + to);
            move(n - 1, aux, from, to);
        }
    }
}