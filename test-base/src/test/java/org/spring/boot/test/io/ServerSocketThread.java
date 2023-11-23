package org.spring.boot.test.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerSocketThread extends Thread {
    private final Socket socket;

    public ServerSocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg = "";
            while ((msg = bufferedReader.readLine()) != null) {
                System.out.println(Thread.currentThread().getName() + ":服务端收到消息：" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

