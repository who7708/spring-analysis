package com.example.mp.rest;

import com.example.mp.mapper.UserMapper;
import com.example.mp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/12/04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/select/all")
    public List<User> selectAll() {
        return userMapper.selectList(null);
    }
}
