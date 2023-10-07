package com.example.demo.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Chris
 * @date 2020/05/20
 * @since 1.0.0
 */
public class TestSocket {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8090);
        System.out.println("step1: new ServerSocket(8090");
        while (true) {
            final Socket client = server.accept();
            System.out.println("step2: client\t" + client.getPort());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = client.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        while (true) {
                            System.out.println(reader.readLine());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
