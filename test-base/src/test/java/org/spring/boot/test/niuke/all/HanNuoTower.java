package org.spring.boot.test.niuke.all;

public class HanNuoTower {
    public void tower(int n, char a, char b, char c)//n个塔从s经过m最终全部移动到e
    {
        if (n == 1) {
            move(a, c);
        } else {
            tower(n - 1, a, c, b);
            move(a, c);
            tower(n - 1, b, a, c);
        }
    }

    public void move(char s, char e) {
        System.out.println("move " + s + " to " + e);
    }

    public static void main(String[] args) {
        HanNuoTower hnt = new HanNuoTower();
        hnt.tower(4, 'A', 'B', 'C');
    }

}
