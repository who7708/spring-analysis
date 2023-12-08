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
public class ApiResult<T> {
    private String code;

    private String msg;

    private T data;
}
