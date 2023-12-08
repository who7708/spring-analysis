package com.example.openfegin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/12/09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Show {
    // 演出ID
    private int id;

    // 演出名称
    private String name;

    // 演出场地
    private String address;

    // 演出场次
    private List<ShowSession> showSessionList;

}
