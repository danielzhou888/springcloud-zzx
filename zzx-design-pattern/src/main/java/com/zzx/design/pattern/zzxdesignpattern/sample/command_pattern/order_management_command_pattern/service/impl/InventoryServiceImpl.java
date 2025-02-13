package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.service.impl;

import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    
    @Override
    public void lockStock(String skuId, Integer quantity) {
        log.info("锁定库存：skuId={}, quantity={}", skuId, quantity);
        // 实际项目中需要调用库存系统
    }

    @Override
    public void unlockStock(String skuId, Integer quantity) {
        log.info("释放库存：skuId={}, quantity={}", skuId, quantity);
    }

    @Override
    public boolean checkStock(String skuId, Integer quantity) {
        // 实际项目中需要查询库存系统
        return true;
    }
}