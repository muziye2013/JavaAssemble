package com.tison.kong.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author tison
 * @date 2019/8/26
 * @description 主从Reactor模式中的主反应堆：主Reactor负责监控所有的连接请求
 */
public class MainReactor {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(1234));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        int coreNum = Runtime.getRuntime().availableProcessors();
        FollowerReactor[] followers = new FollowerReactor[coreNum];
        for(int i=0; i<coreNum; i++) {
            followers[i] = new FollowerReactor();
        }

        int index = 0;
        while(selector.select()>0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            for(SelectionKey key:keys) {
                keys.remove(key);
                if(key.isValid()&&key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel1.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("Accept request:" + socketChannel.socket().getInetAddress());
                    FollowerReactor follower = followers[++index%coreNum];
                    follower.register(socketChannel);
                }
            }
        }

    }
}
