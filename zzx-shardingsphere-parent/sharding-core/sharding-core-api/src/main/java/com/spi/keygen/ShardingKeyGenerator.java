package com.spi.keygen;

import com.spi.TypeBasedSPI;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
public interface ShardingKeyGenerator extends TypeBasedSPI {

    Comparable<?> generateKey();
}
