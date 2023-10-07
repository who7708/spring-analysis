package org.spring.spi.service;

/**
 * @author Chris
 * @date 2020/06/12
 * @since 1.0.0
 */
public interface Crypto {

    String encrypt(String data);

    String decrypt(String data);
}
