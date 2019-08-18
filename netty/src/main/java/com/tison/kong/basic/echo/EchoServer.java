package com.tison.kong.basic.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Date：2019/08/17
 * 类说明：Netty服务端
 */
public class EchoServer  {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 9999;
        EchoServer echoServer = new EchoServer(port);
        System.out.println("服务器即将启动");
        echoServer.start();
        System.out.println("服务器关闭");
    }

    public void start() throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //线程组
        EventLoopGroup group = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            /*服务端启动必备*/
            ServerBootstrap b = new ServerBootstrap();
            b.group(group,work)
                    /*指明使用NIO进行网络通讯*/
                    .channel(NioServerSocketChannel.class)
                    /*指明服务器监听端口*/
                    .localAddress(new InetSocketAddress(port))
                    /*接收到连接请求，新启一个socket通信，也就是channel，每个channel
                    * 有自己的事件的handler*/
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            /*绑定到端口，阻塞等待直到连接完成*/
            ChannelFuture f = b.bind().sync();
            /*阻塞到channel关闭*/
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }


}
