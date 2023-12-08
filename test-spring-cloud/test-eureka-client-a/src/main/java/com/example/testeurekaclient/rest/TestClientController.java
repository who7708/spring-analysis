package com.example.testeurekaclient.rest;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/12/09
 */
@RestController
@RequestMapping("/hello")
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
}
