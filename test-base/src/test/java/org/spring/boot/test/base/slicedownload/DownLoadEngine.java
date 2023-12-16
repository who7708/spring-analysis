package org.spring.boot.test.base.slicedownload;

import com.alibaba.nacos.common.executor.ExecutorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import static org.spring.boot.test.base.slicedownload.SliceUtil.PER_PAGE;

@Slf4j
public class DownLoadEngine {
    // 原生线程池
    private static final ExecutorService executorService = ExecutorFactory.newFixedExecutorService(5);
    // 若依线程池
    // private static ThreadPoolTaskExecutor executorService = SpringUtils.getBean("threadPoolTaskExecutor");


    /**
     * 分片下载
     *
     * @param downloadUrl 下载链接
     * @param tempPath    临时文件路径
     * @param fileName    文件名称
     */
    public static void downloadSlice(String downloadUrl, String tempPath, String fileName) {
        // 大小探测
        ResponseEntity<byte[]> responseEntity = SliceUtil.getFileContentByUrlAndPosition(downloadUrl, 0, 1);
        HttpHeaders headers = responseEntity.getHeaders();
        String rangeBytes = headers.getFirst("Content-Range");

        if (Objects.isNull(rangeBytes)) {
            log.error("url:{},不支持分片下载", downloadUrl);
            return;
        }

        long allBytes = Long.parseLong(rangeBytes.split("/")[1]);
        log.info("文件总大小：{}M", allBytes / 1024.0 / 1024.0);

        // 分页
        SlicePageInfo slicePageInfo = splitPage(allBytes);

        CountDownLatch countDownLatch = new CountDownLatch(Math.toIntExact(slicePageInfo.getPage()));
        CountDownLatch mainLatch = new CountDownLatch(1);

        executorService.execute(() -> {
            try {
                countDownLatch.await();
                SliceUtil.mergeFileTranTo(tempPath, fileName, slicePageInfo.getPage());
                mainLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        for (SliceInfo sliceInfo : slicePageInfo.getSliceInfoList()) {
            executorService.submit(() -> {
                SliceUtil.download(tempPath, downloadUrl, sliceInfo, fileName);
                countDownLatch.countDown();
            });
        }

        try {
            mainLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * 文件分片
     *
     * @param allBytes 文件总大小
     * @return /
     */
    public static SlicePageInfo splitPage(long allBytes) {
        CopyOnWriteArrayList<SliceInfo> list = new CopyOnWriteArrayList<>();
        long size = allBytes;
        long left = 0;
        long page = 0;
        while (size > 0) {
            long start = 0;
            long end;
            start = left;
            // 分页
            if (size < PER_PAGE) {
                end = left + size;
            } else {
                end = left += PER_PAGE;
            }
            size -= PER_PAGE;
            page++;
            if (start != 0) {
                start++;
            }
            System.out.printf("页码 %d 开始位置 %d 结束位置 %d \n", page, start, end);
            final SliceInfo sliceInfo = new SliceInfo(start, end, page);
            list.add(sliceInfo);
        }
        SlicePageInfo slicePageInfo = new SlicePageInfo();
        slicePageInfo.setSliceInfoList(list);
        slicePageInfo.setPage(page);
        return slicePageInfo;
    }
}