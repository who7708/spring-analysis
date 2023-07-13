package org.spring.boot.test;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

class MyOutOfMemoryTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("-------------MyOutOfMemoryTask----------------");
        Map<String, Object> m = new HashMap<>();
        int i = 0;
        do {
            byte[] bytes = new byte[1000 * 1000];
            m.put(String.valueOf(i), bytes);
            i++;
        } while (i < 100000);
    }
}