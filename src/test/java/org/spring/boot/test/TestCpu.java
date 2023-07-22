package org.spring.boot.test;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-22
 */
public class TestCpu {
    public static void main(String[] args) {
        cpu();
    }

    private static void cpu() {
        while (true) {
            System.out.println("666");
        }
    }
}
// 排查 CPU 占用过高：
// 先使用 top -c 查看哪个进程占用CPU过高
// 打印出来线程id占用的cpu，ps H -eo pid,tid,%cpu | grep <pid>
// 将线程ID转换成十六进制， printf "%x\n" <pid>
// 再通过 jstack 搜索到些线程对应的堆栈信息，即可找到占用cpu过高的代码信息 jstack <pid> | grep <十六进制> -A20

// 同样也可以使用 arthas 查询 thread 列表，查看占用cpu过高的 id，然后再使用 thread <id> 查堆栈信息即可