package com.example.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. 检查死锁：
 * a. jps -l 查看 java 线程
 * b. jstack -l pid 查看对应应用的堆栈信息
 * Found one Java-level deadlock:
 * =============================
 * "mythread2":
 * waiting for ownable synchronizer 0x000000076d5ddd30, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
 * which is held by "mythread1"
 * "mythread1":
 * waiting for ownable synchronizer 0x000000076d5ddd60, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
 * which is held by "mythread2"
 *
 * 2. 检查 cpu 100%
 * a. top 查看 cpu 100% 进程
 * b. pidstat -p pid -u -t
 * -p：指定进程号
 * -u：默认的参数，显示各个进程的cpu使用统计
 * -t：显示选择任务的线程的统计信息外的额外信息 如 线程ID（TID）
 * 将 tid 转换为 16 进程号： printf %x 4605
 * 4605 -> 11fd
 * c. jstack -l pid | less
 * 查找 tid 为 11fd 的线程堆栈信息，如下，可以看到对应的是哪块代码出了问题。对应解决相应代码即可
 *
 * "Thread-0" #9 prio=5 os_prio=0 tid=0x00007f25640ee800 nid=0x11fd runnable [0x00007f2550ef2000]
 * java.lang.Thread.State: RUNNABLE
 * at TestDeadLock$1.run(TestDeadLock.java:24)
 * at java.lang.Thread.run(Thread.java:748)
 *
 * @author Chris
 * @version 1.0.0
 * @date 2020/04/20
 */
public class TestDeadLock {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Object o = new Object();
                    if (o == null) {
                        System.out.println("object null");
                    }
                }
            }
        }).start();
        deathLock();
    }

    public static void deathLock() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    lock1.lock();
                    TimeUnit.SECONDS.sleep(1);
                    lock2.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock1.unlock();
                    lock2.unlock();
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    lock2.lock();
                    TimeUnit.SECONDS.sleep(1);
                    lock1.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.setName("mythread1");
        t2.setName("mythread2");
        t1.start();
        t2.start();
    }
}
