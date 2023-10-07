package com.example.demo.reference;

import java.lang.ref.WeakReference;

/**
 * @author Chris
 * @date 2020/05/13
 * @since 1.0.0
 */
public class TestWeakReference {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());

        System.gc();

        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }
}
