package com.zzx.design.pattern.zzxdesignpattern.sample.singleton_mode.log_manage_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 日志管理器
 * @author: 周志祥
 * @create: 2024-10-12 15:26
 */
public class LogManager {
    private static Logger log = LoggerFactory.getLogger(LogManager.class);

    private static volatile LogManager instance;

    private LogManager() {
        log.info("初始化日志系统配置...");
    }

    public static LogManager getInstance() {
        if (instance == null) {
            synchronized (LogManager.class) {
                if (instance == null) {
                    instance = new LogManager();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        log.info("Log: {}", message);
        log.info("写入到日志文件...");
    }
}
