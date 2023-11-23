package org.spring.boot.test.io;

import java.nio.ByteBuffer;

public class NioBufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println("=============");
        buffer.put("zuiyu".getBytes());
        System.out.println(buffer.position());//5
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println("=============");

        buffer.flip();
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//5
        System.out.println(buffer.capacity());//10
        System.out.println("=============");

        char ch = (char) buffer.get();
        System.out.println(ch);
        System.out.println(buffer.position());//1
        System.out.println(buffer.limit());//5
        System.out.println(buffer.capacity());//10
        System.out.println("=============");

        buffer.clear();
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println((char) buffer.get());//z
        System.out.println("=============");

        ByteBuffer buf = ByteBuffer.allocate(10);
        buf.put("zuiyu".getBytes());
        buf.flip();
        byte[] b = new byte[2];
        buf.get(b);
        String rs = new String(b);
        System.out.println(rs);
        System.out.println(buffer.position());//1
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println("=============");

        buf.mark();
        byte[] b2 = new byte[3];
        buf.get(b2);
        System.out.println(new String(b2));
        System.out.println(buffer.position());//1
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println("=============");

        buf.reset();
        if (buf.hasRemaining()) {
            System.out.println(buf.remaining());//3
        }
    }
}
