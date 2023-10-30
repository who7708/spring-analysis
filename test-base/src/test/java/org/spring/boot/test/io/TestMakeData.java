package org.spring.boot.test.io;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/30
 */
public class TestMakeData {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        File file = new File("F:\\test\\data.txt");
        long begin = System.currentTimeMillis();
        List<String> dataList = new ArrayList<>();
        for (int i = 1; i <= 50_000_000; i++) {
            String data = String.format("%d,用户%d", i, i);
            dataList.add(data);
            if (i % 1_000_000 == 0) {
                FileUtils.writeLines(file, StandardCharsets.UTF_8.name(), dataList, true);
                dataList = new ArrayList<>();
            }
        }
        FileUtils.writeLines(file, StandardCharsets.UTF_8.name(), dataList, true);
        System.out.println("耗时： " + (System.currentTimeMillis() - begin));
    }
}
