package com.example.demo;

/**
 * @author Chris
 * @date 2020/04/21 22:39
 * @since 1.0.0
 */
public class TestDisorder {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });

            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            one.start();
            other.start();
            one.join();
            other.join();
            String result = "第" + i + "次 (" + x + "," + y + ")";
            // 如果没有发生乱序执行的情况下： x = 0, y = 1
            // 发生乱序执行时：会出现如，第32441次 (0,0)
            if (x == 0 && y == 0) {
                System.err.println(result);
                break;
            }
        }
    }
}
