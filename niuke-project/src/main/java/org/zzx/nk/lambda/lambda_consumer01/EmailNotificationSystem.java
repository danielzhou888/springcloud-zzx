package org.zzx.nk.lambda.lambda_consumer01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 案例2：电子邮件通知系统
 * 在电子邮件通知系统中，使用 Consumer 接口批量发送电子邮件通知。
 */
public class EmailNotificationSystem {
    public static void main(String[] args) {
        List<String> emailAddresses = Arrays.asList(
            "user1@example.com",
            "user2@example.com",
            "user3@example.com"
        );

        Consumer<String> emailConsumer = email -> System.out.println("Sending email to: " + email);

        processBatch(emailAddresses, emailConsumer);
    }

    public static <T> void processBatch(List<T> items, Consumer<T> consumer) {
        for (T item : items) {
            consumer.accept(item);
        }
    }
}