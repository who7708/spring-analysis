package com.example.demo.proxy.jdk;

import java.util.Date;

/**
 * 静态代理
 */
public class UserServiceProxy implements UserService {
    private UserService target; // 被代理的对象

    public UserServiceProxy(UserService target) {
        this.target = target;
    }

    public String select() {
        before();
        target.select();    // 这里才实际调用真实主题角色的方法
        after();
        return null;
    }

    public String update() {
        before();
        target.update();    // 这里才实际调用真实主题角色的方法
        after();
        return null;
    }

    private void before() {     // 在执行方法之前执行
        System.out.println(String.format("log start time [%s] ", new Date()));
    }

    private void after() {      // 在执行方法之后执行
        System.out.println(String.format("log end time [%s] ", new Date()));
    }
}
