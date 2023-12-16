package org.spring.boot.test.base.slicedownload;

import java.net.HttpURLConnection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/12/16
 */
public class SliceDownloadDemo {
    private static final int threadNum  = 50;
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(threadNum);

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(threadNum);

    public void download(String url) {
        HttpURLConnection httpURLConnection = null;

    }
}
