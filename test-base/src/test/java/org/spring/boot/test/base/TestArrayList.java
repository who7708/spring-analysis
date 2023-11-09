package org.spring.boot.test.base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/11/02
 */
public class TestArrayList {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            list.add(i);
        }
        System.out.println(list);
    }
}
