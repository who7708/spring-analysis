package com.example.openfegin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/12/09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowSession {
    private int id;

    // 演出场次
    private String session;

    // 演出时间
    private String time;

}
