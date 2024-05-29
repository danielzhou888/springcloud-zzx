package org.zzx.nk.lambda.lambda_premier_01;

import java.util.function.Supplier;

/**
 * 案例6：使用 Supplier 延迟初始化
 * 问题：使用 Supplier 接口实现延迟初始化。
 */
public class LambdaAdvanced6 {
    public static void main(String[] args) {
        Supplier<String> lazyValue = () -> "Computed Value";
        System.out.println(lazyValue.get()); // 输出: Computed Value
    }

    public void test() {
        Supplier<String> lazyValue = () -> "Computed Value";
        System.out.println(lazyValue.get());
    }
}