package com.tison.kong.advance.heartbeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;

/**
 * @author tison
 * @date 2019/9/1
 * @description 心跳检测客户端启动类
 */
public class ClientStarter {

    private Bootstrap bootstrap;
    private int times = 0;

    public static void main(String[] args) {
        ClientStarter starter = new ClientStarter(new Bootstrap());
        starter.connect();
    }

    public ClientStarter(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
        ClientStarter clientStarter = this;
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new IdleStateHandler(0, 0, 4));
                        ch.pipeline().addLast(new HeartBeatClientHandler(clientStarter));
                    }
                });
    }

    public void connect() {
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 1111));
        channelFuture.addListener(future ->{
            if (future.isSuccess()) {
                System.out.println(LocalDateTime.now().toString() + ":connect to server success");
            } else {
                System.out.println(LocalDateTime.now().toString() + ":connect to server failed,try times:" + (++times));
                //断线重连，最大重试次数5次
                if(times < 5) {
                    connect();
                }
                //重试次数用完，优雅关机
                else{
                    System.out.println(LocalDateTime.now().toString() + ":client shutdown!");
                    //客户端线程组关机
                    channelFuture.channel().eventLoop().parent().shutdownGracefully();
                }
            }
        });
    }
}
