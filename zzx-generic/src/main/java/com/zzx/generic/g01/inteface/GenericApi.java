package com.zzx.generic.g01.inteface;

/**
 * @author zhouzhixiang
 * @company 
 * @Date 2020-03-20
 */
public interface GenericApi<T> {

    T next();

    T get();

    void add(T t);

    void delete(T t);
}
