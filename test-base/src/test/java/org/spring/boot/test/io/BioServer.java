package org.spring.boot.test.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {
    public static void main(String[] args) {
        // bioServer1();
        // bioServer2();
        bioServer3();
    }

    public static void bioServer1() {
        try (ServerSocket socket = new ServerSocket(8888)) {
            Socket accept = socket.accept();
            InputStream inputStream = accept.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg = "";
            while ((msg = bufferedReader.readLine()) != null) {
                System.out.println("服务端收到消息：" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bioServer2() {
        try {
            ServerSocket socket = new ServerSocket(8888);
            while (true) {
                Socket accept = socket.accept();
                // 每次client连接，就创建一个线程进行处理，BIO
                new ServerSocketThread(accept).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bioServer3() {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            HandlerSocketServerPool pool = new HandlerSocketServerPool(3, 10);
            while (true) {
                Socket accept = serverSocket.accept();
                Runnable runnable = new ServerRunnableTarget(accept);
                pool.execute(runnable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
