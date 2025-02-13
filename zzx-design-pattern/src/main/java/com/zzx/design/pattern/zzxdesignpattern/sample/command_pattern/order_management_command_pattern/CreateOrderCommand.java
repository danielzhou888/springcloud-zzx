package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern;

import com.zzx.design.pattern.zzxdesignpattern.common.base.ApiResponse;
import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.bean.Order;
import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.service.InventoryService;
import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.service.OrderService;
import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrderCommand implements OrderCommand {

    private final OrderService orderService;
    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private OrderCreateDTO orderDTO;
    private Long createdOrderId;

    public CreateOrderCommand withRequest(OrderCreateDTO dto) {
        this.orderDTO = dto;
        return this;
    }

    @Override
    @Transactional
    public ApiResponse<String> execute() {
        // 库存预扣减
        inventoryService.lockStock(orderDTO.getSkuId(), orderDTO.getQuantity());
        
        // 创建订单
        Order order = orderService.createOrder(orderDTO);
        this.createdOrderId = order.getId();
        
        // 生成支付流水
        paymentService.createPaymentFlow(order);
        
        return new ApiResponse<>("SUCCESS", "订单创建成功", order.getOrderNo());
    }

    @Override
    @Transactional
    public void undo() {
        if (createdOrderId != null) {
            // 回退库存
            inventoryService.unlockStock(orderDTO.getSkuId(), orderDTO.getQuantity());
            
            // 标记订单为已取消
            orderService.cancelOrder(createdOrderId, "命令撤销");
            
            // 撤销支付流水
            paymentService.cancelPaymentFlow(createdOrderId);
        }
    }
}