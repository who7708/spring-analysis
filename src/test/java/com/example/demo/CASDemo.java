package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(6);

        boolean firstCAS = atomicInteger.compareAndSet(6, 2019);

        System.out.println(firstCAS + "\t" + atomicInteger.get());

        boolean secondCAS = atomicInteger.compareAndSet(6, 2000);

        System.out.println(secondCAS + "\t" + atomicInteger.get());

    }

}
// cas 操作：
// 1. 调用 AtomicInteger.compareAndSet(expect, update) 方法
// 2. 在上述方法中再调用 unsafe.compareAndSwapInt(this, valueOffset, expect, update)，此方法是 native 的方法
// 其中： this，是当前 AtomicInteger 对象 obj ；valueOffset, 是当前对象 value 属性在对象 obj 的偏移位置；expect，期望内存值；update，希望更新成的值
// 3. 在调用 unsafe.cpp 的中 Unsafe_CompareAndSwapInt 的方法，在方法中，解析过程:
// a. oop p = JNIHandles::resolve(obj); 解析 obj 对象在内存中的位移
// b. jlong* addr = (jlong*)(index_oop_from_field_offset_long(p(), offset)); 解析 valueOffset 的位移，如; obj 是 100，offset 是 1，则 add 为 101
// c. 然后调用 Atomic::cmpxchg(x, addr, e) 方法并返回 e，(x 为 update 值，addr 为 b 的地址偏移量，e 为 expect 值)
// d. cmpxchg 中，不同操作系统使用不同的汇编指令完成操作，如下为 linux_x86 实现：
// ```
// UNSAFE_ENTRY(jboolean, Unsafe_CompareAndSwapInt(JNIEnv *env, jobject unsafe, jobject obj, jlong offset, jint e, jint x))
//   UnsafeWrapper("Unsafe_CompareAndSwapInt");
//   oop p = JNIHandles::resolve(obj);
//   jint* addr = (jint *) index_oop_from_field_offset_long(p, offset);
//   return (jint)(Atomic::cmpxchg(x, addr, e)) == e;
// UNSAFE_END

// inline jint     Atomic::cmpxchg    (jint     exchange_value, volatile jint*     dest, jint     compare_value) {
//   int mp = os::is_MP(); // 是否为多处理器
//   __asm__ volatile (LOCK_IF_MP(%4) "cmpxchgl %1,(%3)"
//                     : "=a" (exchange_value)
//                     : "r" (exchange_value), "a" (compare_value), "r" (dest), "r" (mp)
//                     : "cc", "memory");
//   return exchange_value;
// }
// ```