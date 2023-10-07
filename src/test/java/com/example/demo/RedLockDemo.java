// package com.example.demo;
//
// import java.util.concurrent.TimeUnit;
// import lombok.extern.slf4j.Slf4j;
// import org.redisson.Redisson;
// import org.redisson.api.RLock;
// import org.redisson.api.RedissonClient;
// import org.redisson.config.Config;
//
// /**
//  * <p>RedLock的基本操作</P>
//  *
//  * @author hanchao
//  */
// @Slf4j
// public class RedLockDemo {
//     public static void main(String[] args) {
//         //连接redis
//         Config config = new Config();
//         config.useSingleServer().setAddress("redis://127.0.0.1:26379");
//         config.useSingleServer().setAddress("redis://192.168.31.226:6379");
//         RedissonClient redisson = Redisson.create(config);
//         log.info("连接Redis");
//
//         //1.定义锁
//         RLock lock = redisson.getLock("myTest001");
//
//         try {
//             //尝试加锁的超时时间
//             Long timeout = 300L;
//             //锁过期时间
//             Long expire = 30L;
//             //2.获取锁
//             if (lock.tryLock(timeout, expire, TimeUnit.MILLISECONDS)) {
//                 //2.1.获取锁成功的处理
//                 log.info("加锁成功");
//                 //...do something
//                 log.info("使用完毕");
//             } else {
//                 //2.2.获取锁失败的处理
//                 log.info("加锁失败");
//                 log.info("其他处理");
//             }
//         } catch (InterruptedException e) {
//             log.error("尝试获取分布式锁失败", e);
//         } finally {
//             //3.释放锁
//             try {
//                 lock.unlock();
//                 log.info("锁释放成功");
//             } catch (Exception e) {
//                 //do nothing...
//             }
//         }
//
//         //关闭连接
//         redisson.shutdown();
//         log.info("关闭redis连接");
//     }
// }
