package org.spring.boot.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import org.spring.model.UserFor12306;
import org.spring.syncdbtoredis.ConnectUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-11
 */
public class TestDBUtils {

    /**
     * 单机使用redis
     */
    @Test
    public void test1() throws SQLException {
        System.out.println("===== test1 =====");
        Connection conn = ConnectUtils.connect();
        long start = System.currentTimeMillis();
        List<UserFor12306> allUserFor12306List = queryForList(conn, 2000);
        // 大概1s可以加载全部数据
        System.out.println("从mysql中加载数据耗时：" + (System.currentTimeMillis() - start));
        System.out.println(allUserFor12306List.size());

        Jedis jedis = new Jedis("192.168.1.5", 6379);
        jedis.auth("123456");
        System.out.println(jedis.get("k1"));
        start = System.currentTimeMillis();
        Lists.partition(allUserFor12306List, 20000)
                .stream()
                .map(userFor12306List -> CompletableFuture.runAsync(() -> {
                            // userFor12306List.forEach(u ->
                            //         jedis.set(u.getIdNumber(), JSON.toJSONString(u))
                            // );

                            Pipeline pipelined = jedis.pipelined();
                            userFor12306List.forEach(u ->
                                    pipelined.set(u.getIdNumber(), JSON.toJSONString(u))
                            );
                            pipelined.sync();
                        })
                ).forEach(CompletableFuture::join);
        System.out.println("finish sync to redis");
        System.out.println("写入redis耗时：" + (System.currentTimeMillis() - start));
        // 使用set 大概38s
        // 使用 pipelined 大概 3s
    }

    private List<UserFor12306> queryForList(Connection conn, int limit) throws SQLException {
        QueryRunner totalRunner = new QueryRunner();
        Long totalFromDb = totalRunner.query(conn, "select count(*) from 12306_user", new ScalarHandler<>());
        int lastQueryId = 0;
        int total = 0;
        List<UserFor12306> allUserFor12306List = new ArrayList<>(Math.toIntExact(totalFromDb));
        do {
            QueryRunner listRunner = new QueryRunner();
            List<UserFor12306> userFor12306List = listRunner.query(conn,
                    "select * from 12306_user where user_id > ? order by user_id asc limit 0,? ",
                    new BeanListHandler<>(UserFor12306.class, new BasicRowProcessor(new BeanProcessor(mapColumnsToFields()))), lastQueryId, limit);
            // System.out.println(userFor12306List.size());
            if (CollectionUtils.isEmpty(userFor12306List)) {
                break;
            }
            // allUserFor12306List.addAll(userFor12306List);
            CollectionUtils.addAll(allUserFor12306List, userFor12306List);
            total += userFor12306List.size();
            lastQueryId = userFor12306List.get(userFor12306List.size() - 1).getUserId();
        } while (total <= totalFromDb);
        return allUserFor12306List;
    }

    private static Map<String, String> mapColumnsToFields() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("user_id", "userId");
        columnsToFieldsMap.put("login_id", "loginId");
        columnsToFieldsMap.put("real_name", "realName");
        columnsToFieldsMap.put("id_number", "idNumber");
        columnsToFieldsMap.put("password", "passwd");
        return columnsToFieldsMap;
    }
}
