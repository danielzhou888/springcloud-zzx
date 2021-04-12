package com.zzx.shardingsphere.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author zhouzhixiang
 * @Date 2020-03-25
 */
@RequiredArgsConstructor
@Getter
public final class DatabaseAccessConfiguration {

    private final String url;

    private final String username;

    private final String password;
}
