/**
 * Copyright (c) 2017, 2020, www.ddky.com. All rights reserved.
 */
package com.zzx.sentinel.client.util;

import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author zhumz
 * @date 12/21/2020
 * @description 降级结果工具类
 */
public class FallbackResultUtil {

    private static final Logger logger= LoggerFactory.getLogger(FallbackResultUtil.class);

    public static Result getEmptyResult(Class<?> returnType, Invocation invocation) {
        if (returnType == null) {
            return AsyncRpcResult.newDefaultAsyncResult(invocation);
        }
        if (returnType.isArray()) {
            return AsyncRpcResult.newDefaultAsyncResult(Array.newInstance(returnType, 0), invocation);
        } else if (List.class.isAssignableFrom(returnType)) {
            return AsyncRpcResult.newDefaultAsyncResult(new ArrayList<>(), invocation);
        } else if (Set.class.isAssignableFrom(returnType)) {
            return AsyncRpcResult.newDefaultAsyncResult(new HashSet<>(), invocation);
        } else if (Map.class.isAssignableFrom(returnType)) {
            return AsyncRpcResult.newDefaultAsyncResult(new HashMap<>(), invocation);
        } else {
            Object o = null;
            try {
                o = returnType.newInstance();
            } catch (InstantiationException e) {
                logger.error("getEmptyResult exception {}", e);
            } catch (IllegalAccessException e) {
                logger.error("getEmptyResult exception {}", e);
            }
            return AsyncRpcResult.newDefaultAsyncResult(o, invocation);
        }
    }


}
