package com.example.demo.reference;

/**
 * @author Chris
 * @date 2020/05/13
 * @since 1.0.0
 */
public class M {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }
}
