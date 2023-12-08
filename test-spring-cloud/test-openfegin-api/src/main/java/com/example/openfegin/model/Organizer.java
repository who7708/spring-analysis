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
public class Organizer {

    // 主办方ID
    private int id;

    // 主办方名称
    private String name;

    // 演出列表
    private List<Show> showList;
}
