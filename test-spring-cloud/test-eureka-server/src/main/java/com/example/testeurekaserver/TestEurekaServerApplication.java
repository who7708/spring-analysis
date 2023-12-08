package com.example.testeurekaserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@EnableEurekaServer
@SpringBootApplication
public class TestEurekaServerApplication {

    public static void main(String[] args) {
        // SpringApplication.run(TestEurekaServerApplication.class, args);
        new SpringApplicationBuilder(TestEurekaServerApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
    }

}
