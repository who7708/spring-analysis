package com.example.openfegin.api;

import com.example.openfegin.model.Show;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/12/09
 */
@FeignClient("test-eureka-client-b")
public interface ShowApi {
    @RequestMapping("/show/list")
    List<Show> showSessionList();

    /**
     * 根据主办方ID查询演出列表
     *
     * @param organizerId 主办方ID
     * @return 演出列表
     */
    @RequestMapping("/show/list/{id}")
    List<Show> showSessionList(@PathVariable("id") int organizerId);
}
