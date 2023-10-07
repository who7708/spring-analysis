package com.example.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/06/23
 */
public class DiscardClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        // try {
        //     while (in.isReadable()) {
        //         System.out.println((char) in.readByte());
        //         System.out.flush();
        //     }
        // } finally {
        //     ReferenceCountUtil.release(msg);
        // }
        System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));

        System.out.println("Yes, A new client in = " + ctx.name());
    }

}
