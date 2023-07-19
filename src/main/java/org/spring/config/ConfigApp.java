package org.spring.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-19
 */
@SpringBootApplication(scanBasePackages = "org.spring.config")
public class ConfigApp {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApp.class, args);
    }
}
