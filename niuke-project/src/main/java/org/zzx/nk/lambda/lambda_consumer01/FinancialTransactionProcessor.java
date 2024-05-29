package org.zzx.nk.lambda.lambda_consumer01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 案例5：金融交易处理系统
 * 问题：使用 Consumer 批量处理交易记录。
 * 风格：记录每个交易，并计算总交易金额。
 */
public class FinancialTransactionProcessor {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
            new Transaction("Transaction1", 100.0),
            new Transaction("Transaction2", 200.0),
            new Transaction("Transaction3", 300.0)
        );

        Consumer<Transaction> transactionConsumer = transaction -> {
            // 处理交易，可能包括保存到数据库或更新余额等操作
            System.out.println("Processing transaction: " + transaction.getName() + " Amount: " + transaction.getAmount());
        };

        transactions.forEach(transactionConsumer);

        // 计算总交易金额
        double totalAmount = transactions.stream()
                                         .mapToDouble(Transaction::getAmount)
                                         .sum();
        System.out.println("Total transaction amount: " + totalAmount);
    }
}

class Transaction {
    private String name;
    private double amount;

    public Transaction(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }
}