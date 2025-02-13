package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern;

import com.zzx.design.pattern.zzxdesignpattern.common.base.ApiResponse;
import com.zzx.design.pattern.zzxdesignpattern.common.exception.CommandExecutionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Stack;

@Service
public class CommandInvoker {
    private final Stack<OrderCommand> history = new Stack<>();
    private final Stack<OrderCommand> redoStack = new Stack<>();

    @Transactional
    public ApiResponse<String> executeCommand(OrderCommand command) {
        try {
            ApiResponse<String> response = command.execute();
            history.push(command);
            redoStack.clear();
            return response;
        } catch (Exception e) {
            throw new CommandExecutionException("命令执行失败: " + e.getMessage());
        }
    }

    public void undoLastCommand() {
        if (!history.isEmpty()) {
            OrderCommand command = history.pop();
            command.undo();
            redoStack.push(command);
        }
    }

}