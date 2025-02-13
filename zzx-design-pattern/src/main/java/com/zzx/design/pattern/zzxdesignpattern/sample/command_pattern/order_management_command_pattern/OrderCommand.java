package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern;

import com.zzx.design.pattern.zzxdesignpattern.common.base.ApiResponse;

public interface OrderCommand {
    ApiResponse<String> execute();
    void undo();
}