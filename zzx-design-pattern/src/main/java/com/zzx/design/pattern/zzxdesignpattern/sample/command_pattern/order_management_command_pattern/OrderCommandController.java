package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern;

import com.zzx.design.pattern.zzxdesignpattern.common.advice.GlobalExceptionHandler;
import com.zzx.design.pattern.zzxdesignpattern.common.base.ApiResponse;
import com.zzx.design.pattern.zzxdesignpattern.common.exception.CommandExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
@RestController
@RequestMapping("/api/order/command")
@RequiredArgsConstructor
public class OrderCommandController {
    
    private final CommandInvoker commandInvoker;
    private final CreateOrderCommand createOrderCommand;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> createOrder(@Valid @RequestBody OrderCreateDTO dto) {
        try {
            OrderCommand command = createOrderCommand.withRequest(dto);
            return ResponseEntity.ok(commandInvoker.executeCommand(command));
        } catch (CommandExecutionException e) {
            return GlobalExceptionHandler.errorResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/undo")
    public ResponseEntity<ApiResponse<Void>> undoLastCommand() {
        commandInvoker.undoLastCommand();
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "撤销成功", null));
    }
}