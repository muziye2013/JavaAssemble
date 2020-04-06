package com.tison.kong.basic.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class LinkChatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String context = "hello";
        byte[] contxtByts = context.getBytes();
        int length = contxtByts.length;
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeByte(1);
        byteBuf.writeShort(3);
        byteBuf.writeInt(encodeLength(length));
        byteBuf.writeBytes(contxtByts);

        ctx.writeAndFlush(byteBuf);
    }

    private int encodeLength(int length){
        return length << 27 | length >>> 55;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelContext"+ctx);
        System.out.println("message"+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close().sync();
    }
}
