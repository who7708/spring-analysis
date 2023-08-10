package org.spring.boot.test.jvm;

import org.openjdk.jol.info.GraphLayout;

import java.util.HashMap;

public class JolTest {

    public static void main(String[] args) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("key", "value");
        System.out.println(GraphLayout.parseInstance(hashMap).toFootprint());
    }
}