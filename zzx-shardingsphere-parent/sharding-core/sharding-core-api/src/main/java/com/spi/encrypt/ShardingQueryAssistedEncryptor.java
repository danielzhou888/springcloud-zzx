package com.spi.encrypt;

/**
 * Sharding query assisted encryptor.
 *
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
public interface ShardingQueryAssistedEncryptor extends ShardingEncryptor {

    /**
     * Query assisted encrypt
     * @param plainText
     * @return cipherText
     */
    String queryAssistedEncrypt(String plainText);
}
