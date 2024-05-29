package org.zzx.nk.lambda.lambda_consumer01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 案例4：库存管理系统
 * 问题：使用 Consumer 批量更新库存信息。
 * 风格：更新库存并打印更新后的库存状态。
 */
public class InventoryManagementSystem {
    public static void main(String[] args) {
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Product1", 10);
        inventory.put("Product2", 20);
        inventory.put("Product3", 30);

        List<String> inventoryUpdates = Arrays.asList(
            "Product1 stock updated",
            "Product2 stock updated",
            "Product3 stock updated"
        );

        Consumer<String> inventoryConsumer = update -> {
            String product = update.split(" ")[0];
            inventory.put(product, inventory.get(product) + 10);
            System.out.println(update + ": New stock = " + inventory.get(product));
        };

        inventoryUpdates.forEach(inventoryConsumer);
    }
}