package org.spring.boot.test;

import org.junit.Test;
import org.spring.hashmap.MaySameHashModel;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-25
 */
public class TestHashMap {
    @Test
    public void test1() {
        System.out.println("===== test1 =====");
        int initialCapacity = 2;
        HashMap<String, String> map = new HashMap<>(initialCapacity);
        for (int i = 1; i <= initialCapacity; i++) {
            map.put("key_" + i, "value_" + i);
        }
        System.out.println("已装载");
        map.put("key_" + (initialCapacity + 1), "value_" + (initialCapacity++));

        String aa = map.get("key_" + initialCapacity);
        System.out.println(aa);
    }

    @Test
    public void test2() {
        System.out.println("===== test1 =====");
        int initialCapacity = 16;
        HashMap<MaySameHashModel, String> map = new HashMap<>(initialCapacity);
        // HashMap Node数组+链表的方式存储数据，如果某个链表长度大于 8 & Node数组长度大于 64 ，则此链表将转化成红黑树。
        // MaySameHashModel 返回相同的 hashCode 值，这样就会更快的形成链表或转换成红黑树结构
        for (int i = 1; i <= initialCapacity; i++) {
            MaySameHashModel maySameHashModel = new MaySameHashModel();
            maySameHashModel.setId(i);
            maySameHashModel.setName("key_" + i);
            map.put(maySameHashModel, "value_" + i);
        }
        System.out.println("已装载");
        MaySameHashModel maySameHashModel = new MaySameHashModel();
        maySameHashModel.setId((initialCapacity + 1));
        maySameHashModel.setName("key_" + (initialCapacity + 1));
        map.put(maySameHashModel, "value_" + (initialCapacity + 1));

        String aa = map.get(maySameHashModel);
        System.out.println(aa);
    }
}
