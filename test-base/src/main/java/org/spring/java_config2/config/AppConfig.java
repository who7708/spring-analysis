package org.spring.java_config2.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/17
 */
@Configuration
// 扫描路径
@ComponentScan("org.spring.java_config2")
public class AppConfig {

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
            }
        };
    }
}
