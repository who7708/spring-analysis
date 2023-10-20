package org.spring.spi.service;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author Chris
 * @date 2020/04/23 14:19
 * @since 1.0.0
 */
@SPI
public interface Robot {
    void sayHello();
}
