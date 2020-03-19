package com.zzx.generic.g01.class_generic;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2020-03-20
 */
public class Generic<T> {
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public static void main(String[] args) {
        Generic<Integer> g = new Generic<>(1000);
        System.out.println(g.getKey());
    }
}
