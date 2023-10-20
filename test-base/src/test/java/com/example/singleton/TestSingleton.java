package com.example.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 线程安全： 原子性，可见性，重排序
 *
 * 单例实现参考：
 * https://blog.csdn.net/cselmu9/article/details/51366946
 *
 * @author Chris
 * @date 2020/04/21 23:02
 * @since 1.0.0
 */
public class TestSingleton {

    /**
     * 懒汉方式的 DCL 单例 为什么要加 volatile？
     * 参考： https://blog.csdn.net/longgeqiaojie304/article/details/89464026
     */
    private static class Singleton1 {
        // volatile 在此是禁止指令重排的作用, 保证先初始化, 再把对象引用赋值给instance变量
        private static volatile Singleton1 INSTANCE;

        private Singleton1() {
        }

        public static Singleton1 getInstance() {
            if (null == INSTANCE) {
                synchronized (Singleton1.class) {
                    if (null == INSTANCE) {
                        // 加 volatile 主要是禁止指令重排序
                        // new 对象时分为三个步骤
                        // 1. allocate() 分配对象内存地址： memory = allocate();
                        // 2. 对象初始化： instance = (memory);
                        // 3. 设置instance指向刚分配的内存地址，此时instance != null：instance = memory;
                        // 2 和 3 不存在数据依赖关系，而且无论重排前还是重排后程序的执行结果在单线程中并没有改变，因此这种重排优化是允许的
                        // 因此其他线程可能会拿到还没完全初始化的对象进行使用，从而出现问题
                        INSTANCE = new Singleton1();
                    }
                }
            }
            return INSTANCE;
        }
    }

    /**
     * 饿汉式
     */
    private static class Singleton2 {
        private static final Singleton2 instance = new Singleton2();

        private Singleton2() {
        }

        public static Singleton2 getInstance() {
            return instance;
        }
    }

    /**
     * 饿汉式
     */
    private static class Singleton3 {
        private static Singleton3 instance = null;

        private Singleton3() {
        }

        public static Singleton3 getInstance() {
            if (instance == null) {
                // 1. 当多个线程同时执行在此位置时，可以会创建出不同的对象。最终以最后返回的对象为最终实例对象。此时可能会出现问题
                // 2. 解决方法可以在 getInstance() 方法上 或 if 外加 synchronize 同步，但是这种实现方式的运行效率会很低
                instance = new Singleton3();
            }
            return instance;
        }
    }

    /**
     * 静态内部类 与 饿汉模式类似
     * 静态内部类虽保证了单例在多线程并发下的线程安全性，但是在遇到序列化对象时，默认的方式运行得到的结果就是多例的,
     * 解决办法就是在反序列化的过程中使用readResolve()方法
     */
    private static class Singleton4 implements Serializable {
        private static class SingletonHandler {
            public static Singleton4 instance = new Singleton4();
        }

        private Singleton4() {
        }

        private static Singleton4 getInstance() {
            return SingletonHandler.instance;
        }

        //该方法在反序列化时会被调用，该方法不是接口定义的方法，有点儿约定俗成的感觉
        protected Object readResolve() throws ObjectStreamException {
            System.out.println("调用了readResolve方法！");
            return SingletonHandler.instance;
        }
    }

    private static class Singleton5 {
        private Singleton5() {
        }

        private enum Singleton {
            INSTANCE;
            private Singleton5 instance;

            private Singleton() {
                this.instance = new Singleton5();
            }

            public Singleton5 getInstance() {
                return instance;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // System.out.println(Singleton1.getInstance().hashCode());
                    // System.out.println(Singleton2.getInstance().hashCode());
                    // System.out.println(Singleton3.getInstance().hashCode());
                    // System.out.println(Singleton4.getInstance().hashCode());
                    System.out.println(Singleton5.Singleton.INSTANCE.getInstance());
                }
            }).start();
        }

        // Singleton4 singleton = Singleton4.getInstance();
        // File file = new File("MySingleton.txt");
        // try {
        //     FileOutputStream fos = new FileOutputStream(file);
        //     ObjectOutputStream oos = new ObjectOutputStream(fos);
        //     oos.writeObject(singleton);
        //     fos.close();
        //     oos.close();
        //     System.out.println(singleton.hashCode());
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        //
        // try {
        //     FileInputStream fis = new FileInputStream(file);
        //     ObjectInputStream ois = new ObjectInputStream(fis);
        //     Singleton4 rSingleton = (Singleton4) ois.readObject();
        //     fis.close();
        //     ois.close();
        //     System.out.println(rSingleton.hashCode());
        // } catch (ClassNotFoundException | IOException e) {
        //     e.printStackTrace();
        // }
    }
}
