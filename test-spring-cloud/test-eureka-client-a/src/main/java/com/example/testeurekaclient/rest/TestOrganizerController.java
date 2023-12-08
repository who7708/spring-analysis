package com.example.testeurekaclient.rest;

import com.example.openfegin.api.ShowApi;
import com.example.openfegin.model.Organizer;
import com.google.common.collect.Lists;
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
@RequestMapping("/organizer")
public class TestOrganizerController {
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
    private ShowApi showApi;

    @RequestMapping("/list")
    public List<Organizer> organizerList() {
        return Lists.newArrayList(
                Organizer.builder().id(1).name("浦发银行东方体育中心体育馆")
                        .showList(showApi.showList(1)).build(),
                Organizer.builder().id(2).name("国家会展中心(上海)虹馆EH")
                        .showList(showApi.showList(2)).build()
        );
    }

    @RequestMapping("/detail/{id}")
    public Organizer organizerList(@PathVariable("id") int organizerId) {
        if (organizerId == 1) {
            return Organizer.builder().id(1).name("浦发银行东方体育中心体育馆")
                    .showList(showApi.showList(1)).build();
        }

        return Organizer.builder().id(2).name("国家会展中心(上海)虹馆EH")
                .showList(showApi.showList(2)).build();
    }
}
