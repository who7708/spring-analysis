package com.example.openfegin.api;

import com.example.openfegin.model.ShowSession;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/12/09
 */
@FeignClient("test-eureka-client-c")
public interface ShowSessionApi {
    @RequestMapping(value = "/show/session/list", method = RequestMethod.GET)
    List<ShowSession> showSessionList();

    /**
     * 根据演出ID查询演出场次列表
     *
     * @param showId 演出ID
     * @return 场次列表
     */
    @RequestMapping(value = "/show/session/list/{id}", method = RequestMethod.GET)
    List<ShowSession> showSessionList(@PathVariable("id") int showId);
}
