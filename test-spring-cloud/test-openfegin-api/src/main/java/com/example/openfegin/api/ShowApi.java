package com.example.openfegin.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/12/09
 */
@FeignClient("test-eureka-client-b")
public interface ShowApi {
}
