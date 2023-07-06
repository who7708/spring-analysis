package org.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-06
 */
@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {
        // new SpringApplicationBuilder()
        //         .sources(SpringBootApp.class)
        //         .main(SpringBootApp.class)
        //         .web(false)
        //         .run(args);

        SpringApplication app = new SpringApplication(SpringBootApp.class);
        app.setWebEnvironment(false);
        app.run(args);

        // SpringApplication.run(SpringBootApp.class, args);
    }
}
