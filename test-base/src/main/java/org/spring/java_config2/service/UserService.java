package org.spring.java_config2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/17
 */
@Component
public class UserService {

    @Autowired
    private OrderService orderService;

    public void test() {
        System.out.println("test");
        System.out.println(orderService);
    }
}
