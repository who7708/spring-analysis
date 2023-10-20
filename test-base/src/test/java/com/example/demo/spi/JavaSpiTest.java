package com.example.demo.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;
import org.spring.spi.service.Robot;

import java.util.ServiceLoader;

/**
 * @author Chris
 * @date 2020/04/23
 * @since 1.0.0
 */
public class JavaSpiTest {
    @Test
    public void testJavaSpi() {
        System.out.println("===== testJavaSpi =====");

        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        for (Robot robot : serviceLoader) {
            robot.sayHello();
        }
    }

    @Test
    public void testDubboSpi() {
        System.out.println("===== testDubboSpi =====");

        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
    }
}
