package org.spring.boot.test.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023.09.11 011
 */
public class TestIo {

    private static final String SOURCE_FILE_PATH = "F:\\BaiduNetdiskDownload\\书籍\\Spring揭秘.pdf";

    private static final String TARGET_FILE_PATH = "F:\\test\\Spring揭秘.pdf";

    @Test
    public void ioCopy() throws Exception {
        System.out.println("===== ioCopy =====");
        long begin = System.currentTimeMillis();
        try {
            File sourceFile = new File(SOURCE_FILE_PATH);
            File targetFile = new File(TARGET_FILE_PATH);
            try (FileInputStream fis = new FileInputStream(sourceFile);
                 FileOutputStream fos = new FileOutputStream(targetFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }
            System.out.println("传输 " + formatFileSize(sourceFile.length()) + " 字节到目标文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("耗时： " + (System.currentTimeMillis() - begin));
    }

    @Test
    public void nioTransferTo() {
        long begin = System.currentTimeMillis();
        try {
            File sourceFile = new File(SOURCE_FILE_PATH);
            File targetFile = new File(TARGET_FILE_PATH);
            try (FileChannel sourceChannel = new RandomAccessFile(sourceFile, "r").getChannel();
                 FileChannel targetChannel = new RandomAccessFile(targetFile, "rw").getChannel()) {
                long transferredBytes = sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);

                System.out.println("传输 " + formatFileSize(transferredBytes) + " 字节到目标文件");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("耗时： " + (System.currentTimeMillis() - begin));
    }

    @Test
    public void nioMap() {
        long begin = System.currentTimeMillis();
        try {
            File sourceFile = new File(SOURCE_FILE_PATH);
            File targetFile = new File(TARGET_FILE_PATH);

            try (FileChannel sourceChannel = new RandomAccessFile(sourceFile, "r").getChannel();
                 FileChannel targetChannel = new RandomAccessFile(targetFile, "rw").getChannel()) {
                long fileSize = sourceChannel.size();
                MappedByteBuffer buffer = sourceChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);
                targetChannel.write(buffer);
                System.out.println("传输 " + formatFileSize(fileSize) + " 字节到目标文件");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("耗时： " + (System.currentTimeMillis() - begin));
    }

    private String formatFileSize(long transferredBytes) {
        return null;
    }

}
