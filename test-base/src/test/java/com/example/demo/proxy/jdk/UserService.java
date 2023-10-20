package com.example.demo.proxy.jdk;

public interface UserService {

    @Select("select hello world")
    String select();

    @Update("update hello world")
    String update();
}