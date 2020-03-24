package com.api.config.encrypt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
@RequiredArgsConstructor
@Getter
public final class EncryptColumnRuleConfiguration  {

    private final String plainColumn;

    private final String cipherColumn;

    private final String assistedQueryColumn;

    private final String encryptor;
}