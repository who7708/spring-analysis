package org.spring.boot.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-22
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogLevelController {

    @GetMapping("update/{logLevel}")
    public void updateLogLevel(@PathVariable String logLevel) {
        // 获取LoggerContext实例
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        // 获取RootLogger并设置日志级别
        // loggerContext.getLogger("ROOT").setLevel(Level.DEBUG);
        // 获取指定包下的 Logger 并设置日志级别
        //      日志级别：
        // TRACE 最详细的日志
        // DEBUG 调试
        // INFO 提示
        // WARN 警告
        // ERROR: 错误级别 --> 级别最高，打印的日志越少
        loggerContext.getLogger(LogLevelController.class.getPackage().getName())
                .setLevel(Level.valueOf(logLevel));
    }

    @GetMapping("/test")
    public void testLogLevel() {
        log.trace("time: {}", LocalDateTime.now());
        log.debug("time: {}", LocalDateTime.now());
        log.info("time: {}", LocalDateTime.now());
        log.warn("time: {}", LocalDateTime.now());
        log.error("time: {}", LocalDateTime.now());
    }


}
