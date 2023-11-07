package org.spring.boot.test.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/11/07
 */
// 统计平均响应时间,不仅可以用在类上,也可用在测试方法上
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@Fork(2)
// 输出的时间单位,纳秒
@OutputTimeUnit(TimeUnit.NANOSECONDS)
// 基准测试前进行一次预热执行,也可用在测试方法上
@Warmup(iterations = 1)
// 进行5次基准测试,也可用在测试方法上
@Measurement(iterations = 5)
public class TestStringJmh {
    private String str = "";

    private StringBuilder sb = new StringBuilder();

    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestStringJmh.class.getSimpleName())
                // .result("result.json")
                // .resultFormat(ResultFormatType.JSON).build();
                // .result("result.txt")
                // .resultFormat(ResultFormatType.TEXT)
                .build();
        new Runner(opt).run();
        System.out.println("counter.get() = " + counter.get());
    }

    @Benchmark
    public String strSplice() throws Exception {
        counter.incrementAndGet();
        for (int i = 0; i < 100; i++) {
            str += i;
        }
        return str;
    }

    // 基准测试,主要用来测试方法性能
    @Benchmark
    public String strBuilderAppend() {
        for (int i = 0; i < 100; i++) {
            sb.append(i);
        }
        return sb.toString();
    }
}
