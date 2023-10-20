package org.spring.boot.test.jvm;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-07
 */
public class TestUnsafe {
    private int count = 0;

    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        Unsafe unsafe = reflectGetUnsafe();
        assert unsafe != null;
        System.out.println(unsafe.addressSize());
        // unsafe.defineClass()
        // unsafe.getLoadAverage()

        System.out.printf("更新前：%d\n", this.count);
        // 获取count域的Field
        Class<TestUnsafe> unsafeTestClass = TestUnsafe.class;
        Field fieldCount = unsafeTestClass.getDeclaredField("count");
        fieldCount.setAccessible(true);

        // 计算count的内存偏移量
        long countOffset = (int) unsafe.objectFieldOffset(fieldCount);
        System.out.println(countOffset);

        // 原子性的更新指定偏移量的值（将count的值修改为3）
        unsafe.compareAndSwapInt(this, countOffset, count, 3);

        // 获取指定偏移量的int值
        System.out.printf("unsafe获取：%d\n", unsafe.getInt(this, countOffset));
        System.out.printf("更新后：%d\n", this.count);

    }

    // private static final Unsafe unsafe = Unsafe.getUnsafe();
    //
    // private static final long valueOffset;
    //
    // static {
    //     try {
    //         valueOffset = unsafe.objectFieldOffset
    //                 (AtomicReference.class.getDeclaredField("value"));
    //     } catch (Exception ex) {
    //         throw new Error(ex);
    //     }
    // }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

// 内存操作
// //分配内存, 相当于C++的malloc函数
// public native long allocateMemory(long bytes);
// //扩充内存
// public native long reallocateMemory(long address, long bytes);
// //释放内存
// public native void freeMemory(long address);
// //在给定的内存块中设置值
// public native void setMemory(Object o, long offset, long bytes, byte value);
// //内存拷贝
// public native void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes);
// //获取给定地址值，忽略修饰限定符的访问限制。与此类似操作还有: getInt，getDouble，getLong，getChar等
// public native Object getObject(Object o, long offset);
// //为给定地址设置值，忽略修饰限定符的访问限制，与此类似操作还有: putInt,putDouble，putLong，putChar等
// public native void putObject(Object o, long offset, Object x);
// //获取给定地址的byte类型的值（当且仅当该内存地址为allocateMemory分配时，此方法结果为确定的）
// public native byte getByte(long address);
// //为给定地址设置byte类型的值（当且仅当该内存地址为allocateMemory分配时，此方法结果才是确定的）
// public native void putByte(long address, byte x);

// CAS
// /**
// 	*  CAS
//   * @param o         包含要修改field的对象
//   * @param offset    对象中某field的偏移量
//   * @param expected  期望值
//   * @param update    更新值
//   * @return          true | false
//   */
// public final native boolean compareAndSwapObject(Object o, long offset,  Object expected, Object update);
//
// public final native boolean compareAndSwapInt(Object o, long offset, int expected,int update);
//
// public final native boolean compareAndSwapLong(Object o, long offset, long expected, long update);

// 线程调度
// 这部分，包括线程挂起、恢复、锁机制等方法。
// //取消阻塞线程
// public native void unpark(Object thread);
// //阻塞线程
// public native void park(boolean isAbsolute, long time);
// //获得对象锁（可重入锁）
// @Deprecated
// public native void monitorEnter(Object o);
// //释放对象锁
// @Deprecated
// public native void monitorExit(Object o);
// //尝试获取对象锁
// @Deprecated
// public native boolean tryMonitorEnter(Object o);

// 内存屏障
// 在Java 8中引入，用于定义内存屏障（也称内存栅栏，内存栅障，屏障指令等，是一类同步屏障指令，是CPU或编译器在对内存随机访问的操作中的一个同步点，使得此点之前的所有读写操作都执行后才可以开始执行此点之后的操作），避免代码重排序。
// //内存屏障，禁止load操作重排序。屏障前的load操作不能被重排序到屏障后，屏障后的load操作不能被重排序到屏障前
// public native void loadFence();
// //内存屏障，禁止store操作重排序。屏障前的store操作不能被重排序到屏障后，屏障后的store操作不能被重排序到屏障前
// public native void storeFence();
// //内存屏障，禁止load、store操作重排序
// public native void fullFence();