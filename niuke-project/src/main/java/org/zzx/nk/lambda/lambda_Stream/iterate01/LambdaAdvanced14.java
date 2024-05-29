package org.zzx.nk.lambda.lambda_Stream.iterate01;

import java.util.stream.Stream;

/**
 * 案例14：使用 Stream.iterate 生成无限流
 * 问题：使用 Stream.iterate 方法生成一个无限流，并限制其大小。
 */
public class LambdaAdvanced14 {
    public static void main(String[] args) {
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2);
        
        infiniteStream.limit(10)
                      .forEach(System.out::println);
        // 输出: 0, 2, 4, 6, 8, 10, 12, 14, 16, 18
    }

    public void test1() {
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2);
        infiniteStream.limit(10).forEach(System.out::println);
    }
}