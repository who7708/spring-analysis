package org.spring.hashmap;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-25
 */
public class MaySameHashModel {
    private static final int mode1 = 11;
    private final int mode2 = 22;
    private static int mode3;

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        // 默认返回相同的 hashCode
        return RandomUtils.nextInt(998, 1000);
    }
}
