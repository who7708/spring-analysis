package org.spring.boot.test.demo;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UUIDtoBigInt {

    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        Set<BigInteger> list = new HashSet<>();
        int count = 1;
        for (int i = 0; i < count; i++) {
            String uuid = uuid();
            BigInteger bi = new BigInteger(uuid, 10);
            list.add(bi);
        }
        System.out.println(list.size());
    }

    @Test
    public void test3() throws Exception {
        System.out.println("===== test1 =====");
        Set<Integer> list = new HashSet<>();
        int count = 10_000;
        for (int i = 0; i < count; i++) {
            String uuid = uuid();
            BigInteger bi = new BigInteger(uuid, 16);
            list.add(bi.toString().length());
        }
        // 16 进制时 [32, 33, 34, 35, 36, 37, 38, 39, 31]
        // 32 进制时 [48, 42, 43, 44, 45, 46, 47]
        System.out.println(list);
        System.out.println(list.size());
    }

    @Test
    public void test2() throws Exception {
        System.out.println("===== test2 =====");
        BigInteger bigInteger = new BigInteger("240091359496723941825273843935174304212");
        System.out.println(bigInteger.intValue());
        System.out.println(bigInteger.longValue());
        // 240091359496723941825273843935174304212
        System.out.println(bigInteger);
        System.out.println(bigInteger.bitCount());
        System.out.println(bigInteger.bitLength());
        System.out.println(Arrays.toString(bigInteger.toByteArray()));
        System.out.println(bigInteger.negate());
    }

    @Test
    public void test4() throws Exception {
        System.out.println("===== test4 =====");

        String uuid = uuid();
        String uuidWithUnderline = uuidWithUnderline();
        System.out.println(uuid);
        System.out.println(uuidWithUnderline);
    }

    @Test
    public void test5() throws Exception {
        System.out.println("===== test5 =====");
        int count = 10_000_000;
        List<String> list = new ArrayList<>();
        int no = 1;
        for (int i = 0; i < count; i++) {
            String uuid = uuid();
            BigInteger bi = new BigInteger(uuid, 16);
            list.add(bi + "," + uuid);
            if (list.size() % 3_000_000 == 0) {
                // IOUtils.writeLines(list, StandardCharsets.UTF_8);
                FileUtils.writeLines(new File("D:\\git\\GitHub\\spring-analysis\\build\\test_uuid_int_sort_" + no + ".csv"), list, true);
                list = new ArrayList<>();
                no++;
            }
        }
        if (list.size() > 0) {
            FileUtils.writeLines(new File("D:\\git\\GitHub\\spring-analysis\\build\\test_uuid_int_sort_" + no + ".csv"), list, true);
        }
    }

    @Test
    public void testInsert() throws Exception {
        System.out.println("===== testInsert =====");
        String uuid = uuid();
        BigInteger bi = new BigInteger(uuid, 16);
        String sql = "insert into test_uuid_int_sort(dept_no, dept_name) values ('" + bi + "','" + uuid + "');";
        System.out.println(sql);
    }

    private String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    private String uuidWithUnderline() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
// 240091359496723941825273843935174304212
// 9223372036854775807

// CREATE TABLE `dept` (
//   `id` int unsigned NOT NULL AUTO_INCREMENT,
//   `deptno` bigint unsigned DEFAULT NULL,
//   `dname` varchar(32) DEFAULT NULL,
//   `loc` varchar(32) DEFAULT NULL,
//   PRIMARY KEY (`id`)
// ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci