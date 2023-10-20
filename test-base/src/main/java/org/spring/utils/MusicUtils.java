package org.spring.utils;

import lombok.extern.slf4j.Slf4j;
import org.spring.task.LimitedBlockingQueue;

import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @date 2020/06/17
 * @since 1.0.0
 */
@Slf4j
public class MusicUtils {
    private static final String LETTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS
            , new LimitedBlockingQueue<>(100), (r, executor) -> System.out.println(executor.toString()));

    /** 随机睡眠时间 [1, length] */
    public static int time(int length) {
        Random random = new Random();
        // [1, length]
        return random.nextInt(length) + 1;
    }
}
