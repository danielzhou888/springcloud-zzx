package com.spi.encrypt;

import com.spi.TypeBasedSPI;

/**
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
public interface ShardingEncryptor extends TypeBasedSPI {

    void init();

    String encrypt(Object plainText);

    Object decrypt(String cipherText);
}
