package org.spring.boot.test.io;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BioClient {
    public static void main(String[] args) throws InterruptedException {
        // bioClient1();
        // bioClient2();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " : " + finalI);
            });
        }
        TimeUnit.SECONDS.sleep(100);
    }

    // 单客户端对单服务端，发送单条消息
    public static void bioClient1() {
        try (Socket socket = new Socket("127.0.0.1", 8888)) {
            OutputStream outputStream = socket.getOutputStream();
            PrintStream p = new PrintStream(outputStream);
            p.println("hello server!");
            p.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 单客户端对单服务端，发送多条消息
    public static void bioClient2() {
        try (Socket socket = new Socket("127.0.0.1", 8888)) {
            OutputStream outputStream = socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("请输入:");
                String next = scanner.next();
                PrintStream p = new PrintStream(outputStream);
                p.println(next);
                p.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
