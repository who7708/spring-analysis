package org.spring.boot.nacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-06
 */
@Controller
@RequestMapping("config")
public class ConfigController {

    // @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @RequestMapping("/get")
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }
}