package org.zzx.nk.lambda.lambda_Stream.generate;

import java.util.stream.Stream;

/**
 * 案例15：使用 Stream.generate 生成无限流
 * 问题：使用 Stream.generate 方法生成一个无限流，并限制其大小。
 */
public class LambdaAdvanced15 {
    public static void main(String[] args) {
        Stream<Double> infiniteStream = Stream.generate(Math::random);
        
        infiniteStream.limit(5)
                      .forEach(System.out::println);
        // 输出: 5个随机数
    }

    public void test1() {
        Stream<Double> infiniteStream = Stream.generate(Math::random);
        infiniteStream.limit(5)
                .forEach(System.out::println);
    }
}