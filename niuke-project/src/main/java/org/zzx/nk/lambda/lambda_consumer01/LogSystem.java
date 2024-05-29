package org.zzx.nk.lambda.lambda_consumer01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 案例1：日志记录系统
 * 在日志记录系统中，使用 Consumer 接口批量记录日志信息。
 */
public class LogSystem {
    public static void main(String[] args) {
        List<String> logMessages = Arrays.asList(
            "INFO: Application started",
            "DEBUG: Initializing components",
            "ERROR: Unable to find configuration file",
            "INFO: Application stopped"
        );

        Consumer<String> logConsumer = message -> System.out.println("Logging: " + message);

        processBatch(logMessages, logConsumer);
    }

    public static <T> void processBatch(List<T> items, Consumer<T> consumer) {
        for (T item : items) {
            consumer.accept(item);
        }
    }
}