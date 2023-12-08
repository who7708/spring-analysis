package com.example.testeurekaclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@EnableEurekaClient
@EnableFeignClients(basePackages = "com.example.openfegin")
@SpringBootApplication//(scanBasePackages = {"com.example.testeurekaclient", "com.example.openfegin"})
public class TestEurekaClient2Application {

    public static void main(String[] args) {
        // SpringApplication.run(TestEurekaServerApplication.class, args);
        new SpringApplicationBuilder(TestEurekaClient2Application.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
    }

}
