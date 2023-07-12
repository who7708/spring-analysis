package org.spring.boot.test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.spring.model.UserForCsdn;
import org.spring.syncdbtoredis.ConnectUtils;
import redis.clients.jedis.ClusterPipeline;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Pipeline;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-11
 */
public class TestForCsdn {

    /**
     * 单机使用redis
     */
    @Test
    public void testRedisCluster() throws SQLException {
        System.out.println("===== test1 =====");
        Connection conn = ConnectUtils.connect();
        long start = System.currentTimeMillis();
        List<UserForCsdn> allUserForCsdnList = queryForList(conn, 20000);
        // 大概1s可以加载全部数据
        System.out.println("从mysql中加载数据耗时：" + (System.currentTimeMillis() - start));
        System.out.println(allUserForCsdnList.size());

        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.1.5", 7000));
        nodes.add(new HostAndPort("192.168.1.5", 7001));
        nodes.add(new HostAndPort("192.168.1.5", 7002));
        nodes.add(new HostAndPort("192.168.1.5", 8000));
        nodes.add(new HostAndPort("192.168.1.5", 8001));
        nodes.add(new HostAndPort("192.168.1.5", 8002));
        JedisCluster jedis = new JedisCluster(nodes);

        System.out.println(jedis.get("key1"));
        start = System.currentTimeMillis();
        ClusterPipeline pipelined = jedis.pipelined();
        Lists.partition(allUserForCsdnList, 2000)
                .forEach(userForCsdnList -> {
                    userForCsdnList.forEach(u ->
                            pipelined.set(u.getLoginId(), JSON.toJSONString(u))
                    );
                    pipelined.sync();
                });
        System.out.println("finish sync to redis cluster");
        System.out.println("写入redis耗时：" + (System.currentTimeMillis() - start));
        // 使用set 大概38s
        // 使用 pipelined 大概 3s
    }

    /**
     * 单机使用redis
     */
    @Test
    public void testForCsdnUser() throws SQLException {
        System.out.println("===== test1 =====");
        Connection conn = ConnectUtils.connect();
        long start = System.currentTimeMillis();
        List<UserForCsdn> allUserForCsdnList = queryForList(conn, 2000);
        // 大概1s可以加载全部数据
        System.out.println("从mysql中加载数据耗时：" + (System.currentTimeMillis() - start));
        System.out.println(allUserForCsdnList.size());

        Jedis jedis = new Jedis("192.168.1.5", 6379);
        jedis.auth("123456");
        jedis.select(1);
        System.out.println(jedis.get("k1"));
        start = System.currentTimeMillis();
        Pipeline pipelined = jedis.pipelined();
        Lists.partition(allUserForCsdnList, 20000)
                .stream()
                .map(userForCsdnList -> CompletableFuture.runAsync(() -> {
                            // userForCsdnList.forEach(u ->
                            //         jedis.set(u.getIdNumber(), JSON.toJSONString(u))
                            // );

                            userForCsdnList.forEach(u ->
                                    pipelined.set(u.getLoginId(), JSON.toJSONString(u))
                            );
                            pipelined.sync();
                        })
                ).forEach(CompletableFuture::join);
        System.out.println("finish sync to redis");
        System.out.println("写入redis耗时：" + (System.currentTimeMillis() - start));
        // 使用set 大概38s
        // 使用 pipelined 大概 3s
    }

    private List<UserForCsdn> queryForList(Connection conn, int limit) throws SQLException {
        QueryRunner totalRunner = new QueryRunner();
        Long totalFromDb = totalRunner.query(conn, "select count(*) from csdn_user", new ScalarHandler<>());
        int lastQueryId = 0;
        int total = 0;
        List<UserForCsdn> allUserForCsdnList = new ArrayList<>(Math.toIntExact(totalFromDb));
        do {
            QueryRunner listRunner = new QueryRunner();
            List<UserForCsdn> userForCsdnList = listRunner.query(conn,
                    "select * from csdn_user where id > ? order by id asc limit 0,? ",
                    new BeanListHandler<>(UserForCsdn.class, new BasicRowProcessor(new BeanProcessor(mapColumnsToFields()))), lastQueryId, limit);
            // System.out.println(userForCsdnList.size());
            if (CollectionUtils.isEmpty(userForCsdnList)) {
                break;
            }
            // allUserForCsdnList.addAll(userForCsdnList);
            CollectionUtils.addAll(allUserForCsdnList, userForCsdnList);
            total += userForCsdnList.size();
            lastQueryId = userForCsdnList.get(userForCsdnList.size() - 1).getId();
        } while (total <= totalFromDb);
        return allUserForCsdnList;
    }

    private static Map<String, String> mapColumnsToFields() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("login_id", "loginId");
        columnsToFieldsMap.put("password", "passwd");
        return columnsToFieldsMap;
    }


    @Test
    public void writeMysqlForCsdn() throws IOException {
        System.out.println("===== writeMysqlForCsdn =====");
        List<String> readLines = FileUtils.readLines(new File(
                "F:\\BaiduNetdiskDownload\\sql脚本备份\\12306与csdn用户信息脚本\\www.csdn.net.txt"), StandardCharsets.UTF_8);

        List<String> results = new ArrayList<>(readLines.size());
        for (String readLine : readLines) {
            if (StringUtils.containsAny(readLine, '\\', '\'', '"')) {
                continue;
            }
            List<String> split = Splitter.on("#")
                    .trimResults()
                    .omitEmptyStrings()
                    .limit(3)
                    .splitToList(readLine);
            if (split.size() != 3) {
                continue;
            }

            // ('a','b','c')
            String s = Joiner.on("','").appendTo(new StringBuilder("('"), split).append("')").toString();
            results.add(s);
        }


        Connection conn = ConnectUtils.connect();
        long start = System.currentTimeMillis();
        int total = Lists.partition(results, 20000)
                .stream()
                .map(valueList -> Joiner.on(",")
                        .appendTo(new StringBuilder("INSERT INTO csdn_user(`login_id`, `password`, `email`) VALUES"), valueList)
                        .toString())
                .mapToInt(sql -> {
                    QueryRunner insertRunner = new QueryRunner();
                    try {
                        int count = insertRunner.update(conn, sql);
                        return count;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return 0;
                }).sum();
        System.out.println("共写入" + total + "条数据，耗时：" + (System.currentTimeMillis() - start));
    }

    @Test
    public void testBatchInsert() throws SQLException {
        System.out.println("===== testBatchInsert =====");
        String sql = "INSERT INTO csdn_user(`login_id`, `password`, `email`) VALUES('zdg','12344321','zdg@csdn.net'),('LaoZheng','670203313747','chengming_zheng@163.com'),('fstao','730413','fstao@tom.com')";
        Connection conn = ConnectUtils.connect();
        QueryRunner insertRunner = new QueryRunner();
        int update = insertRunner.update(conn, sql);
        System.out.println(update);
    }

}
