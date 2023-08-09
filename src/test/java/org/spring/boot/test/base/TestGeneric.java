package org.spring.boot.test.base;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-09
 */
public class TestGeneric {
    @Test
    public void test1() throws Exception {
        String testJson = "{\n" +
                "    \"resultCode\":\"200\",\n" +
                "    \"reason\":\"成功的返回\",\n" +
                "    \"result\":{\n" +
                "        \"area\":\"浙江省温州市平阳县\",\n" +
                "        \"sex\":\"男\",\n" +
                "        \"birthday\":\"1989年03月08日\"\n" +
                "    }\n" +
                "}";
        ResponseUser response1 = JSONObject.parseObject(testJson, ResponseUser.class);
        Response response2 = JSONObject.parseObject(testJson, Response.class);
        Response<User> response3 = JSONObject.parseObject(testJson, new TypeReference<Response<User>>() {
        });
        System.out.println(response1);
        System.out.println(response2);
        System.out.println(response3);

        System.out.println("response1 =========");
        Field field1 = response1.getClass().getDeclaredField("result");
        System.out.println("result Type is " + field1.getType());
        System.out.println("result GenericType is " + field1.getGenericType());

        System.out.println("response2 =========");
        Field field2 = response2.getClass().getDeclaredField("result");
        System.out.println("result Type is " + field2.getType());
        System.out.println("result GenericType is " + field2.getGenericType());

        System.out.println("response3 =========");
        Field field3 = response3.getClass().getDeclaredField("result");
        System.out.println("result Type is " + field3.getType());
        System.out.println("result GenericType is " + field3.getGenericType());
    }

    @Data
    static class Response<T> {
        private String resultCode;

        private String reason;

        private T result;

    }

    @Data
    static class ResponseUser {
        private String resultCode;

        private String reason;

        private User result;
    }

    @Data
    static class User {
        private String area;

        private String sex;

        private String birthday;
    }

}
