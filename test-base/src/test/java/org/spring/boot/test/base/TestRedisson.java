package org.spring.boot.test.base;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-17
 */
public class TestRedisson {
    @Test
    public void testRedisson() throws Exception {
        System.out.println("===== testRedisson =====");
        // 1.创建配置
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        // 集群模式
        // config.useClusterServers().addNodeAddress("127.0.0.1:7004", "127.0.0.1:7001");
        // 2.根据 Config 创建出 RedissonClient 示例。
        singleServerConfig.setAddress("redis://192.168.1.6:6379");
        singleServerConfig.setPassword("123456");
        RedissonClient redissonClient = Redisson.create(config);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                RLock myLock = redissonClient.getLock("myLock");
                try {
                    if (myLock.tryLock(100, TimeUnit.SECONDS)) {
                        System.out.println("加锁成功");
                    }
                    TimeUnit.SECONDS.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    myLock.unlock();
                }
            });
            thread.start();
        }

        TimeUnit.SECONDS.sleep(1000);

        // config.useMasterSlaveServers()
        //         .setMasterAddress()
        //         .setSlaveAddresses();

        // config.useClusterServers()
        //         .setNodeAddresses();
        // CompletableFuture.runAsync()
    }

    public String testLock(RedissonClient redisson) {
        // 1.获取锁，只要锁的名字一样，获取到的锁就是同一把锁。
        RLock lock = redisson.getLock("lock");
        // 2.加锁
        lock.lock();
        try {
            System.out.println("加锁成功，执行后续代码。线程 ID：" + Thread.currentThread().getId());
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            // 3.解锁
            System.out.println("Finally，释放锁成功。线程 ID：" + Thread.currentThread().getId());
        }
        return "test lock ok";
    }
}
