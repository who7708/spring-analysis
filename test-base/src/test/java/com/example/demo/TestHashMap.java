package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/04/21
 */
public class TestHashMap {

    // map 按照 value 指定属性进行倒序排序输出
    @Test
    public void test1() {
        System.out.println("===== test1 ====");
        Map<Integer, User> users = new HashMap<>();
        users.put(1, User.builder().name("张三").age(25).build());
        users.put(2, User.builder().name("李四").age(22).build());
        users.put(3, User.builder().name("王五").age(28).build());

        System.out.println(users);

        Map<Integer, User> sortedHashMap = sortedMap(users);
        System.out.println(sortedHashMap);

        // Map<Integer, User> sortedHashMap2 = new TreeMap<>(new Comparator<Integer>() {
        //     @Override
        //     public int compare(Integer o1, Integer o2) {
        //         return 0;
        //     }
        // });
    }

    private Map<Integer, User> sortedMap(Map<Integer, User> map) {
        Set<Map.Entry<Integer, User>> entries = map.entrySet();
        List<Map.Entry<Integer, User>> list = new ArrayList<>(entries);
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                // 正序
                // return o1.getValue().getAge() - o2.getValue().getAge();
                // 倒序
                return o2.getValue().getAge() - o1.getValue().getAge();
            }
        });
        Map<Integer, User> temp = new LinkedHashMap<>();
        for (Map.Entry<Integer, User> entry : list) {
            temp.put(entry.getKey(), entry.getValue());
        }
        return temp;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class User {
        private String name;
        private int age;
    }
}
