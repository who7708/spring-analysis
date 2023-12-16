package org.spring.boot.test.base.slicedownload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

@Slf4j
public class SliceUtil {

    /**
     * 分片大小
     */
    public final static long PER_PAGE = (long) 1024 * 1024;

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    /**
     * 根据分片下载
     *
     * @param downloadUrl
     * @param start
     * @param end
     * @return
     */
    public static ResponseEntity<byte[]> getFileContentByUrlAndPosition(String downloadUrl, long start, long end) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Range", "bytes=" + start + "-" + end);

        org.springframework.http.HttpEntity<Object> httpEntity = new org.springframework.http.HttpEntity<>(httpHeaders);
        return REST_TEMPLATE.exchange(downloadUrl, HttpMethod.GET, httpEntity, byte[].class);
    }

    /**
     * 下载
     *
     * @param tempPath
     * @param downloadUrl
     * @param sliceInfo
     * @param fName
     */
    public static void download(String tempPath, String downloadUrl, SliceInfo sliceInfo, String fName) {
        log.info("下载分片文件：{}，分片序号 {}", fName, sliceInfo.getPage());

        // 创建一个分片文件对象
        File file = new File(tempPath, sliceInfo.getPage() + "-" + fName);

        if (file.exists() && file.length() == PER_PAGE) {
            log.info("此分片文件 {} 已存在", sliceInfo.getPage());
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(file);) {
            ResponseEntity<byte[]> responseEntity = SliceUtil.getFileContentByUrlAndPosition(downloadUrl, sliceInfo.getStart(), sliceInfo.getEnd());

            byte[] body = responseEntity.getBody();
            if (body != null && body.length == 0) {
                log.warn("分片文件：{},没有内容", file.getName());
                return;
            }
            // 将分片内容写入临时存储分片文件
            fos.write(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 合并文件
     *
     * @param tempPath
     * @param fName
     * @param page
     */
    public static void mergeFileTranTo(String tempPath, String fName, long page) {

        try (FileChannel channel = new FileOutputStream(new File(tempPath, fName)).getChannel()) {
            for (long i = 1; i <= page; i++) {
                File file = new File(tempPath, i + "-" + fName);
                FileChannel fileChannel = new FileInputStream(file).getChannel();
                long size = fileChannel.size();
                for (long left = size; left > 0; ) {
                    left -= fileChannel.transferTo((size - left), left, channel);
                }
                fileChannel.close();
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}