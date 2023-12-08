package com.example.testeurekaclient.rest;

import com.example.openfegin.api.OrganizerApi;
import com.example.openfegin.model.ApiResult;
import com.example.openfegin.model.Organizer;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/12/09
 */
@RestController
@RequestMapping("/endpoints")
public class TestClientController {
    @Autowired
    private EurekaClient discoveryClient;

    public String serviceUrl(String virtualHostname) {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka(virtualHostname, false);
        return instance.getHomePageUrl();
    }

    @RequestMapping("/world")
    public String home(@RequestParam("name") String virtualHostname) {
        return serviceUrl(virtualHostname);
    }

    @Autowired
    private OrganizerApi organizerApi;

    @RequestMapping("/organizer/list")
    public ApiResult<List<Organizer>> organizerList() {
        List<Organizer> organizerList = organizerApi.organizerList();
        return ApiResult.<List<Organizer>>builder()
                .code("200")
                .msg("成功")
                .data(organizerList)
                .build();
    }

    @RequestMapping("/organizer/detail/{id}")
    public ApiResult<Organizer> organizerList(@PathVariable("id") int organizerId) {
        Organizer organizerDetail = organizerApi.organizerList(organizerId);
        return ApiResult.<Organizer>builder()
                .code("200")
                .msg("成功")
                .data(organizerDetail)
                .build();
    }
}
