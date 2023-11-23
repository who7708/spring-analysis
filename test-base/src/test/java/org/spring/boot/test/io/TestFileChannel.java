package org.spring.boot.test.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/11/23
 */
public class TestFileChannel {
    @Test
    public void writeByFileChannel() {
        try (FileOutputStream fos = new FileOutputStream("test.txt")) {
            FileChannel channel = fos.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello zuiyu!".getBytes());
            buffer.flip();
            channel.write(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void readByFileChannel() {
        try (FileInputStream is = new FileInputStream("test.txt")) {
            FileChannel channel = is.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer);
            buffer.flip();
            String rs = new String(buffer.array(), 0, buffer.remaining());
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void copyByFileChannel() throws Exception {
        File srcFile = new File("test.txt");
        File destFile = new File("test_copy.txt");

        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            // 必须清空缓冲区，然后在写入数据到缓冲区
            buffer.clear();
            // 开始读取一次数据
            int flag = fisChannel.read(buffer);
            if (flag == -1) {
                break;
            }
            // 已经读取数据，切换缓冲区模式为可读模式
            buffer.flip();
            fosChannel.write(buffer);
        }
        fisChannel.close();
        fosChannel.close();
        System.out.println("文件复制完成！");
    }

}
