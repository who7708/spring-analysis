package com.example.demo.proxy.jdk;

public class UserServiceImpl implements UserService {
    public String select() {
        System.out.println("查询 selectById");
        return null;
    }

    public String update() {
        System.out.println("更新 update");
        return null;
    }
}
