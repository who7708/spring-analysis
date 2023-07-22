package org.spring.boot.oom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-22
 */
@RestController
@RequestMapping("/oom")
public class OOMController {

    @GetMapping("/test")
    public String oom() {
        Map<String, Object> m = new HashMap<>();
        int i = 0;
        do {
            byte[] bytes = new byte[1024 * 1024];
            m.put(String.valueOf(i), bytes);
            i++;
        } while (i < 100000);
        return "oomFinished";
    }
}
