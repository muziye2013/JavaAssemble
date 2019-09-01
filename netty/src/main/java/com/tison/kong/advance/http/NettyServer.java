package com.tison.kong.advance.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author tison
 * @date 2019/9/1
 * @description Http服务端启动类
 */
public class NettyServer {

    /**
     * 设置服务端端口
     */
    private static final int port = 8080;

    /**
     * 通过nio方式来接收连接和处理连接
     */
    private static EventLoopGroup group = new NioEventLoopGroup();

    private static ServerBootstrap b = new ServerBootstrap();

    public static void main(String[] args) throws InterruptedException {
        try {
            b.group(group);
            b.channel(NioServerSocketChannel.class);
            //设置过滤器
            b.childHandler(new NettyServerFilter());
            // 服务器绑定端口监听
            ChannelFuture f = b.bind(port).sync();
            System.out.println("服务端启动成功...");
            // 监听服务器关闭监听
            f.channel().closeFuture().sync();
        } finally {
            //关闭EventLoopGroup，释放掉所有资源包括创建的线程
            group.shutdownGracefully();
        }
    }
}
