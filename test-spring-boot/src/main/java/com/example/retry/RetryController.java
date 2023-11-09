package com.example.retry;

import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/11/09
 */
@RestController
public class RetryController {

    /**
     * 在方法上添加 @Retryable 注解
     * 1. value 抛出指定的异常才会重试
     * 2. maxAttempts 最大重试次数，默认3次
     * 3. backoff 重试等等策略，默认使用 @Backoff, @Backoff 的value 默认是 1000
     * 3.1 multiplier 指定延迟位数，默认为0， 表示固定暂停1秒后进行重试。如果把 multiplier 设置为 1.5， 则第一次2秒，第二次3秒，第三次4.5秒。。。
     */
    @GetMapping("/test/retry")
    @Retryable(value = Exception.class, maxAttempts = 4, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public Integer testRetry(@RequestParam("code") Integer code) throws Exception {
        System.out.println("testRetry 被调用，时间：" + LocalTime.now());
        if (code == 0) {
            throw new Exception("出错了。。。");
        }
        System.out.println("test正常调用");
        return 200;
    }

    // TODO 可以看到传参里面写的是 Exception e，这个是作为回调的接头暗号(重试次数用完了，还是失败，抛出这个Exception e通知触发这个回调方法)。
    //  对于@Recover注解的方法，需要特别注意的是:
    //  方法的返回值必须与@Retryable方法一致
    //  方法的第一个参数，必须是Throwable类型的，建议是与@Retryable配置的异常一致，其他的参数，需要哪个参数，写进去就可以了
    //  该回调方法与重试方法写在同一个实现类里面
    @Recover
    public Integer testRetryRecover(Exception e, int code) {
        System.out.println("testRetry回调方法执行。。。");
        return 400;
    }

}
