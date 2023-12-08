package com.example.testeurekaclient.rest;

import com.example.openfegin.api.ShowSessionApi;
import com.example.openfegin.model.Show;
import com.example.openfegin.model.ShowSession;
import com.google.common.collect.Lists;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/show")
public class TestShowController {
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
    private ShowSessionApi showSessionApi;

    @RequestMapping("/list")
    public List<Show> showSessionList() {

        // List<ShowSession> showSessionList = showSessionApi.showSessionList();

        return Lists.newArrayList(
                Show.builder().id(1).name("【上海】苏有朋「在多重宇宙中遇见你」巡回演唱会-上海站").address("上海市 | 浦发银行东方体育中心体育馆")
                        .showSessionList(showSessionApi.showSessionList(1)).build(),
                Show.builder().id(2).name("【上海】杨乃文“MUSE缪斯”巡回演唱会-上海站").address("上海市 | 国家会展中心(上海)虹馆EH")
                        .showSessionList(showSessionApi.showSessionList(2)).build(),
                Show.builder().id(3).name("【上海】金玟岐2024“一起唱歌的夜晚”巡回演唱会—上海站").address("上海市 | 国家会展中心(上海)虹馆EH")
                        .showSessionList(showSessionApi.showSessionList(3)).build()
        );
    }
}
