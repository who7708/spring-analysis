package org.spring.spi.service.impl;

import org.spring.spi.service.Crypto;

/**
 * @author Chris
 * @date 2020/06/12
 * @since 1.0.0
 */
public class CryptoRSAImpl implements Crypto {
    private String key;

    public CryptoRSAImpl(String key) {
        this.key = key;
    }

    @Override
    public String encrypt(String data) {
        return null;
    }

    @Override
    public String decrypt(String data) {
        return null;
    }
}
