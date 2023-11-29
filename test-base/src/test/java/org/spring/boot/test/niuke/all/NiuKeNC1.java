package org.spring.boot.test.niuke.all;

import org.junit.Test;

import java.math.BigInteger;

/**
 * NC1 大数加法
 *<a href="https://www.nowcoder.com/practice/11ae12e8c6fe48f883cad618c2e81475?tpId=196&tqId=37176&rp=1&ru=%2Fexam%2Foj&qru=%2Fexam%2Foj&sourceUrl=%2Fexam%2Foj&difficulty=undefined&judgeStatus=undefined&tags=&title=&dayCountBigMember=30%E5%A4%A9">...</a>
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class NiuKeNC1 {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        System.out.println(solve("1", "99"));
        System.out.println(solve("114514", ""));
        System.out.println(solve("111212121", "2342342342342"));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 计算两个数之和
     *
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public String solve(String s, String t) {
        // write code here
        BigInteger b1 = new BigInteger("0");
        BigInteger b2 = new BigInteger("0");
        if (s != null && !s.trim().isEmpty()) {
            b1 = new BigInteger(s);
        }
        if (t != null && !t.trim().isEmpty()) {
            b2 = new BigInteger(t);
        }
        return b1.add(b2).toString();
    }
}
