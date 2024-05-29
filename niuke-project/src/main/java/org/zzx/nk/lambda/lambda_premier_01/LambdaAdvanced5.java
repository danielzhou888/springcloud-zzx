package org.zzx.nk.lambda.lambda_premier_01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 案例5：使用 Consumer 进行批量处理
 * 问题：使用 Consumer 接口进行批量处理操作。
 */
public class LambdaAdvanced5 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        Consumer<String> print = System.out::println;
        Consumer<String> printWithPrefix = s -> System.out.println("Fruit: " + s);
        
        list.forEach(print.andThen(printWithPrefix));
        // 输出: 
        // apple
        // Fruit: apple
        // banana
        // Fruit: banana
        // cherry
        // Fruit: cherry
    }

    public void test() {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        Consumer<String> print = System.out::println;
        Consumer<String> printWithPrefix = s -> System.out.println("Fruit: " + s);
        list.forEach(print.andThen(printWithPrefix));
    }
}