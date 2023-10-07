package org.spring.spi.service.impl;

import org.spring.spi.service.Robot;

/**
 * @author Chris
 * @date 2020/04/23 14:20
 * @since 1.0.0
 */
public class OptimusPrime implements Robot {
    @Override
    public void sayHello() {
        System.out.println("Hello, I am Optimus Prime.");
    }
}
