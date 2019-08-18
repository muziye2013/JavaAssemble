package com.tison.kong.basic.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Date：2019/08/17
 * @Description：服务端的业务处理
 * @ChannelHandler.Sharable
 * 类上的注解指明该handler可以在多个channel之间共享，意味这个实现必须线程安全的。
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 服务端读取到网络数据后的处理
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        /*netty实现的缓冲区*/
        ByteBuf in = (ByteBuf)msg;
        System.out.println("Server Accept:"+in.toString(CharsetUtil.UTF_8));
        ctx.write(in);
    }

    /**
     * 服务端读取完成网络数据后的处理
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
            throws Exception {
        /*flush掉所有的数据*/
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                /*当flush完成后，关闭连接*/
                .addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 发生异常后的处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
