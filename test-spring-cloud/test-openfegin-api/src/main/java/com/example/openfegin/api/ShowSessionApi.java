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

    @RequestMapping(value = "/show/session/{id}", method = RequestMethod.GET)
    List<ShowSession> showSessionList(@PathVariable("id") int id);
}
