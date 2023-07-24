package org.spring.boot.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = format.format(System.currentTimeMillis());
            System.out.println("当前时间：" + dateStr);
        }
    }
}
// 排查 CPU 占用过高：
// 1. 先使用 top -c 查看哪个进程占用CPU过高
// 2. 打印出来线程id占用的cpu，ps H -eo pid,tid,%cpu | grep <pid>
// 3. 将线程ID转换成十六进制， printf "%x\n" <tid>
// 4. 再通过 jstack 搜索到些线程对应的堆栈信息，即可找到占用cpu过高的代码信息 jstack <pid> | grep -A20 <十六进制>
// 第2步：或直接使用 top -Hp <pid> 第一行展示就是线程id（tid），后结步骤一样

// 同样也可以使用 arthas 查询 thread 列表，查看占用cpu过高的 id，然后再使用 thread <id> 查堆栈信息即可


