package com.tison.kong.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author tison
 * @date 2019/8/26
 * @description NIO客户端实现
 */
public class Client {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel;
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 1234));
        while (true) {
            LocalTime time = LocalTime.now();
            byte[] requst = time.toString().getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(requst.length);
            buffer.put(requst);
            //调用flip之后，读写指针指到缓存头部，并且设置了最多只能读出之前写入的数据长度(而不是整个缓存的容量大小)
            buffer.flip();
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            Thread.sleep(2000);
        }
    }
}
