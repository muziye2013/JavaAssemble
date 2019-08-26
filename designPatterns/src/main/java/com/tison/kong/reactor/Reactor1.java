package com.tison.kong.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

/**
 * @author tison
 * @date 2019/8/26
 * @description NIO实现的单线程Reactor模型
 */
public class Reactor1 {

    public static void main(String[] args) throws IOException {
        //使用Selector
        Selector selector = Selector.open();

        //建立Channel 并绑定到1234端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(1234));
        //使设定non-blocking的方式
        serverSocketChannel.configureBlocking(false);

        //向Selector注册Channel及我们有兴趣的事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            //Selector通过select方法通知我们我们感兴趣的事件发生了
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            //同步方式轮询
            while (iterator.hasNext()) {
                //一个key被处理完成后，就从就绪关键字（ready keys）列表中除去
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    //如果accept被激活，说明有客户端连接
                    ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = acceptServerSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("accept from "+socketChannel.socket().getInetAddress().toString());
                    //为客户端连接添注册读事件
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable() && key.isValid()) {
                    //如果读到客户端的数据
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int count = socketChannel.read(buffer);
                    if (count <= 0) {
                        socketChannel.close();
                        key.cancel();
                        System.out.println("Received invalide data, close the connection");
                        continue;
                    }
                    System.out.println("Received message"+new String(buffer.array()));
                }
                keys.remove(key);
            }
        }
    }
}
