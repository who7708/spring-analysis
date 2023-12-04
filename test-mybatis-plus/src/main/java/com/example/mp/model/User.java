package com.example.mp.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/12/04
 */
@Data
@TableName("user")
public class User {
    private Long id;

    private String name;

    private Integer age;

    private String email;
}
