package org.spring.boot.test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 零拷贝测试 mmap
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/11/29
 */
public class TestZeroCopy {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\tmp\\test.txt");
        RandomAccessFile rw = new RandomAccessFile(file, "rw");
        MappedByteBuffer map = rw.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 2048);
        map.put("mmap content".getBytes());
        rw.close();

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            FileChannel channel = fileInputStream.getChannel();
            try (FileOutputStream fileOutputStream = new FileOutputStream("D:\\tmp\\test_copy.txt")) {
                FileChannel outChannel = fileOutputStream.getChannel();
                // transferTo 从一个管道直接到中一个管道
                channel.transferTo(0, file.length(), outChannel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
