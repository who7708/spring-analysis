package org.spring.spi.service.impl;

import org.spring.spi.service.Robot;

/**
 * @author Chris
 * @date 2020/04/23
 * @since 1.0.0
 */
public class Bumblebee implements Robot {
    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
