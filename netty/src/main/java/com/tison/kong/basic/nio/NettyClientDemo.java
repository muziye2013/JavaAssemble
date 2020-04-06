package com.tison.kong.basic.nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class NettyClientDemo {

    public static void main(String args[]){
        NettyClientDemo clientDemo = new NettyClientDemo();
        clientDemo.createConnection();
    }

    public void createConnection(){
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast("IdleState", new IdleStateHandler(5,0,0, TimeUnit.MINUTES));
                        socketChannel.pipeline().addLast("Chat", new LinkChatHandler());
                    }
                });

        bootstrap.connect("bird-link.pingan.com.cn",16060).addListener(future -> {
            Channel channel= ((ChannelFuture)future).channel();
            if(future.isSuccess()){
                System.out.println("connect success");
                // 关闭
                // channel.close();
            }else{
                System.out.println("connect failed");
            }
        });
    }
}
