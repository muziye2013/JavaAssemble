package com.tison.kong.basic.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Date：2019/08/17
 * @Description：netty的客户端
 */
public class EchoClient {

    private final int port;
    private final String host;

    public EchoClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void start() throws InterruptedException {
        //线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //客户端启动必备
            Bootstrap b = new Bootstrap();
            b.group(group)
                    //指明使用NIO进行网络通讯
                .channel(NioSocketChannel.class)
                    //配置远程服务器的地址
                .remoteAddress(new InetSocketAddress(host,port))
                .handler(new EchoClientHandler());

            //连接到远程节点，阻塞等待直到连接完成
            ChannelFuture f = b.connect().sync();
            /*阻塞，直到channel关闭*/
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient(9999,"127.0.0.1").start();
    }
}
