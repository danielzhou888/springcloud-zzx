package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.service;

public interface InventoryService {
    void lockStock(String skuId, Integer quantity);
    void unlockStock(String skuId, Integer quantity);
    boolean checkStock(String skuId, Integer quantity);
}