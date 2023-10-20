package org.spring.boot.test.demo;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/11 011
 */
public class T2 {
    public void mySort() {
        int x = 11; // 语句1
        int y = 12; // 语句2
        x = x + 5; // 语句3
        y = x * x; // 语句4
    }
}
// 源码中语句顺序是 1234，但由于指令重排的存在，则可能编译结果会是 2134 或 1324，指令重排仍然要考虑数据依赖性
