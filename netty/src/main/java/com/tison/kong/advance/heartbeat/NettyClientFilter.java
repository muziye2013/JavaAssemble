package com.tison.kong.advance.heartbeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author tison
 * @date 2019/9/1
 * @description 优雅配置客户端责任链上的Handler
 */
public class NettyClientFilter extends ChannelInitializer<SocketChannel> {

    private ClientStarter clientStarter;

    public NettyClientFilter(ClientStarter starter){
        this.clientStarter = starter;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new IdleStateHandler(0, 0, 4));
        ch.pipeline().addLast(new HeartBeatClientHandler(clientStarter));
    }
}
