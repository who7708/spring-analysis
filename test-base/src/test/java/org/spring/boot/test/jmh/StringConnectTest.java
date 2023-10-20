package org.spring.boot.test.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

// AverageTime：表示统计平均响应时间，不仅可以用在类上，也可用在测试方法上。
// Throughput：统计单位时间内可以对方法测试多少次。
// SampleTime：统计每个响应时间范围内的响应次数，比如 0-1ms，3 次；1-2ms，5 次。
// SingleShotTime：跳过预热阶段，直接进行一次 **** 微基准测试。
@BenchmarkMode(Mode.AverageTime)
// 微基准测试前进行三次预热执行，也可用在测试方法上
@Warmup(iterations = 3, time = 1)
// 进行 5 次微基准测试，也可用在测试方法上
@Measurement(iterations = 5, time = 5)
@Threads(4)
@Fork(1)
// Benchmark：多线程共享一个示例
// Group：线程组共享一个示例，在测试方法上使用 @Group 设置线程组
@State(value = Scope.Benchmark)
// 输出的时间单位，这里写的是毫秒。
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class StringConnectTest {

    // @Param(value = {"10", "50", "100"})
    @Param(value = {"10"})
    private int length;

    @Benchmark
    public void testStringAdd(Blackhole blackhole) {
        String a = "";
        for (int i = 0; i < length; i++) {
            a += i;
        }
        blackhole.consume(a);
    }

    @Benchmark
    public void testStringBuilderAdd(Blackhole blackhole) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(i);
        }
        blackhole.consume(sb.toString());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringConnectTest.class.getSimpleName())
                // .result("result.json")
                // .resultFormat(ResultFormatType.JSON).build();
                .result("result.txt")
                .resultFormat(ResultFormatType.TEXT).build();
        new Runner(opt).run();
    }
}
// Benchmark                               (length)  Mode  Cnt    Score    Error  Units
// StringConnectTest.testStringAdd               10  avgt    5  235.610 ± 65.032  ns/op
// StringConnectTest.testStringBuilderAdd        10  avgt    5  113.698 ± 36.533  ns/op

// 在 JMH 基准测试中，每次运行测试方法都会生成一个 Score 值。Score 值代表了方法运行时间的度量值，其具体含义取决于所使用的测量模式。
//
// 在 Throughput（吞吐量）模式下，Score 值表示每秒钟可以执行的操作次数，即方法的吞吐量。Score 值越高，表示方法的吞吐量越大，性能越好。
//
// 在 AverageTime（平均时间）和 SampleTime（采样时间）模式下，Score 值表示方法的平均执行时间。Score 值越低，表示方法的平均执行时间越短，性能越好。
//
// 在 SingleShotTime（单次执行时间）模式下，Score 值表示方法的单次执行时间。Score 值越低，表示方法的单次执行时间越短，性能越好。
//
// Score 值是基准测试结果的一个重要指标，可以用来比较不同方法或不同实现之间的性能差异。需要注意的是，Score 值一般只是基准测试结果的一个方面，还需要结合其他指标和实际应用场景来进行综合分析和评估。