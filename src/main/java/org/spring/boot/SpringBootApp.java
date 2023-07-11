package org.spring.boot;

import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-06
 */
@EnableAsync
@EnableNacosDiscovery
// @EnableNacosConfig
// // 接入nacos 方法一
// @NacosPropertySource(groupId = "test", dataId = "nacos.cfg.dataId", autoRefreshed = true)
@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {
        // new SpringApplicationBuilder()
        //         .sources(SpringBootApp.class)
        //         .main(SpringBootApp.class)
        //         .web(false)
        //         .run(args);

        // SpringApplication app = new SpringApplication(SpringBootApp.class);
        // app.setWebEnvironment(false);
        // app.run(args);

        SpringApplication.run(SpringBootApp.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
    }

}
