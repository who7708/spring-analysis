package com.example.demo.lock;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author Chris
 * @date 2020/04/24
 * @since 1.0.0
 */
public class TestJedis {
    @Test
    public void test1() {
        System.out.println("===== test1 =====");
        Jedis jedis = new Jedis("127.0.0.1", 26379);
        String result = jedis.set("a", "a");
        System.out.println(result);
        jedis.close();
    }
}
