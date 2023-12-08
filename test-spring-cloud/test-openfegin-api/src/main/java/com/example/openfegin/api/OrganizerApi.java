package com.example.openfegin.api;

import com.example.openfegin.model.Organizer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/12/09
 */
@FeignClient("test-eureka-client-a")
public interface OrganizerApi {
    @RequestMapping("/organizer/list")
    List<Organizer> organizerList();

    @RequestMapping("/organizer/detail/{id}")
    Organizer organizerList(@PathVariable("id") int organizerId);
}
