package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern;

import com.zzx.design.pattern.zzxdesignpattern.ZzxDesignPatternApplication;
import com.zzx.design.pattern.zzxdesignpattern.common.base.ApiResponse;
import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.bean.CouponSelectionDTO;
import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.enums.CouponType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 电商订单命令系统: 支持撤销/重做的订单操作系统
 * <desc>
 *     命令模式的核心概念，即封装请求为对象，从而支持撤销、排队、日志等功能
 *     适用场景：比如电商的订单处理系统，这样的场景适合应用命令模式，因为订单操作通常需要支持撤销、重做、日志记录等
 * </desc>
 * <系统架构>
 *     └── command
 *     ├── OrderCommand.java        // 命令模式抽象接口
 *     ├── CreateOrderCommand.java  // 具体命令实现（含undo逻辑）
 *     ├── CommandInvoker.java      // 命令执行器（支持undo/redo）
 *     └── controller
 *         └── OrderCommandController.java // 对外暴露的API端点
 * </系统架构>
 * <核心流程>
 *      1. 命令创建：Controller接收DTO -> 构建具体命令对象
 *      2. 命令执行：库存锁定 -> 订单创建 -> 支付流水生成（原子操作）
 *      3. 状态管理：成功命令存入历史栈，失败触发全局异常处理
 *      4. 补偿机制：undo时逆向执行库存释放+订单取消+支付撤销
 * </核心流程>
 * <设计亮点>
 *      1. 事务边界控制：@Transactional注解管理复杂操作
 *      2. 补偿策略：undo方法实现最终一致性
 *      3. 审计追踪：历史栈记录所有操作轨迹
 *      4. 高可用设计：支持批量操作回滚和灰度发布回退
 * </设计亮点>
 * <适用业务场景>
 *      1. 订单创建异常自动回滚
 *      2. 客服人工订单干预（修改/取消）
 *      3. 大促期间批量订单操作
 *      4. 支付超时自动撤单
 *      5. 订单操作行为审计
 * </适用业务场景>
 * @author: 周志祥
 * @create: 2025-02-12 11:16
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZzxDesignPatternApplication.class)
public class Main {

    @Autowired
    private OrderCommandController orderCommandController;

    @Autowired
    private CommandInvoker commandInvoker;

    @Autowired
    private CreateOrderCommand createOrderCommand;

    @Before
    public void setup() {
        orderCommandController = new OrderCommandController(
                commandInvoker,
                createOrderCommand
        );
    }

    @Test
    public void test1() {
        // 1. 创建订单
        log.info("=== 测试创建订单 ===");
        OrderCreateDTO orderDTO = createOrderDTO();
        ResponseEntity<ApiResponse<String>> createResponse = orderCommandController.createOrder(orderDTO);
        log.info("创建订单响应：{}", createResponse.getBody());

        // 2. 测试撤销
        log.info("=== 测试撤销订单 ===");
        ResponseEntity<ApiResponse<Void>> undoResponse = orderCommandController.undoLastCommand();
        log.info("撤销操作响应：{}", undoResponse.getBody());
    }

    private OrderCreateDTO createOrderDTO() {
        OrderCreateDTO dto = new OrderCreateDTO();
        // 设置基本信息
        dto.setUserId(1001L);
        dto.setSkuId("SKU123");
        dto.setQuantity(2);
        dto.setDeliveryDate("2024-03-20");

        // 设置优惠券信息（如果需要）
        List<CouponSelectionDTO> coupons = new ArrayList<>();
        CouponSelectionDTO coupon = new CouponSelectionDTO();
        coupon.setCouponId(1L);
        coupon.setAmount(new BigDecimal("10.00"));
        coupon.setCouponType(CouponType.CASH);
        coupon.setDescription("测试优惠券");
        coupons.add(coupon);

        dto.setCoupons(coupons);
        return dto;
    }

    @Test
    public void test2() {
        // 测试创建多个订单后撤销
        log.info("=== 测试创建多个订单 ===");

        // 创建第一个订单
        OrderCreateDTO order1 = createOrderDTO();
        order1.setSkuId("SKU001");
        ResponseEntity<ApiResponse<String>> response1 = orderCommandController.createOrder(order1);
        log.info("创建订单1响应：{}", response1.getBody());

        // 创建第二个订单
        OrderCreateDTO order2 = createOrderDTO();
        order2.setSkuId("SKU002");
        ResponseEntity<ApiResponse<String>> response2 = orderCommandController.createOrder(order2);
        log.info("创建订单2响应：{}", response2.getBody());

        // 撤销最后一个订单
        log.info("=== 测试撤销最后一个订单 ===");
        ResponseEntity<ApiResponse<Void>> undoResponse = orderCommandController.undoLastCommand();
        log.info("撤销操作响应：{}", undoResponse.getBody());
    }
}
