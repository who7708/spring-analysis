package org.spring.boot.test.jvm;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

/**
 * 由jdk-bug引发的线上oom
 * <a href="http://ifeve.com/%e7%94%b1jdk-bug%e5%bc%95%e5%8f%91%e7%9a%84%e7%ba%bf%e4%b8%8aoom/#more-62974">...</a>
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-24
 */
public class TestOOM2 {

    // javax.crypto.JceSecurity.getVerificationResult 导致的内存泄露问题
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        for (int i = 0; i < 500; i++) {
            Cipher c = Cipher.getInstance("AES", new BouncyCastleProvider());
            // Cipher c = Cipher.getInstance("AES", "bc");
        }
    }
}

// -ea
// -Xms100m
// -Xmx100m
// -XX:+UseG1GC
// -XX:MaxGCPauseMillis=200
// -XX:+HeapDumpOnOutOfMemoryError
// -XX:+PrintGCDetails
// -Dfile.encoding=UTF-8
// 或执行：  jmap：jmap -dump:format=b,file=<filename.hprof> <pid>； 手动生成dump文件

// -Xms 初始化堆大小
// -Xmx 堆大小
// -Xmn 年轻代大小， 老年代的大小就等于堆大小减去年轻代大小
// java -Xms20m -Xmn10M TestGC
