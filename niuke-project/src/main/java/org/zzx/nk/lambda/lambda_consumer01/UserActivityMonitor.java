package org.zzx.nk.lambda.lambda_consumer01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 案例3：用户活动监控系统
 * 在用户活动监控系统中，使用 Consumer 接口批量处理用户活动记录。
 */
public class UserActivityMonitor {
    public static void main(String[] args) {
        List<String> userActivities = Arrays.asList(
            "User1 logged in",
            "User2 viewed page A",
            "User3 made a purchase",
            "User1 logged out"
        );

        Consumer<String> activityConsumer = activity -> System.out.println("Processing activity: " + activity);

        processBatch(userActivities, activityConsumer);
    }

    public static <T> void processBatch(List<T> items, Consumer<T> consumer) {
        for (T item : items) {
            consumer.accept(item);
        }
    }
}