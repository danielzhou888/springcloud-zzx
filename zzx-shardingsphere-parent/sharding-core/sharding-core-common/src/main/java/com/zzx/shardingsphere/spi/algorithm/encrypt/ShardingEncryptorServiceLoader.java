/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zzx.shardingsphere.spi.algorithm.encrypt;

import com.spi.encrypt.ShardingEncryptor;
import com.zzx.shardingsphere.spi.NewInstanceServiceLoader;
import com.zzx.shardingsphere.spi.algorithm.TypeBasedSPIServiceLoader;

/**
 * Sharding encryptor service loader.
 * 
 * @author panjuan
 */
public final class ShardingEncryptorServiceLoader extends TypeBasedSPIServiceLoader<ShardingEncryptor> {
    
    static {
        NewInstanceServiceLoader.register(ShardingEncryptor.class);
    }
    
    public ShardingEncryptorServiceLoader() {
        super(ShardingEncryptor.class);
    }
}

