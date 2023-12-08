package com.example.testeurekaclient.rest;

import com.example.openfegin.model.ShowSession;
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
@RequestMapping("/show/session")
public class TestShowSessionController {
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

    @RequestMapping("/list")
    public List<ShowSession> showSessionList() {
        return Lists.newArrayList(
                ShowSession.builder().id(1).session("12月08日 周五 第一场").time("09:00-10:00").build(),
                ShowSession.builder().id(2).session("12月11日 周一 第一场").time("14:00-16:00").build(),
                ShowSession.builder().id(3).session("12月12日 周二 第一场").time("13:00-14:00").build(),
                ShowSession.builder().id(4).session("12月13日 周三 第一场").time("10:00-11:00").build()
        );
    }

    @RequestMapping("/{id}")
    public List<ShowSession> showSessionList(@PathVariable("id") int showId) {
        return showSessionListById(showId);
    }

    private List<ShowSession> showSessionListById(int showId) {
        if (showId == 1) {
            return Lists.newArrayList(
                    ShowSession.builder().id(1).session("12月08日 周五 第一场").time("09:00-10:00").build(),
                    ShowSession.builder().id(2).session("12月11日 周一 第一场").time("14:00-16:00").build(),
                    ShowSession.builder().id(3).session("12月12日 周二 第一场").time("13:00-14:00").build(),
                    ShowSession.builder().id(4).session("12月13日 周三 第一场").time("10:00-11:00").build()
            );
        }
        if (showId == 2) {
            return Lists.newArrayList(
                    ShowSession.builder().id(5).session("12月08日 周五 第一场").time("09:00-10:00").build(),
                    ShowSession.builder().id(6).session("12月11日 周一 第一场").time("14:00-16:00").build(),
                    ShowSession.builder().id(7).session("12月12日 周二 第一场").time("13:00-14:00").build(),
                    ShowSession.builder().id(8).session("12月13日 周三 第一场").time("10:00-11:00").build()
            );
        }

        return Lists.newArrayList(
                ShowSession.builder().id(9).session("12月08日 周五 第一场").time("09:00-10:00").build(),
                ShowSession.builder().id(10).session("12月11日 周一 第一场").time("14:00-16:00").build(),
                ShowSession.builder().id(11).session("12月12日 周二 第一场").time("13:00-14:00").build(),
                ShowSession.builder().id(12).session("12月13日 周三 第一场").time("10:00-11:00").build()
        );
    }
}
