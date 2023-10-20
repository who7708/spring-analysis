package com.example.demo.netty;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/06/23
 */
public class DiscardClient {

    private int port;

    public DiscardClient(int port) {
        this.port = port;
    }

    public void run() {

    }

    public static void main(String[] args) {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardClient(port).run();
    }
}
