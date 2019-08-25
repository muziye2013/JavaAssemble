package com.tison.kong.basic.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Tison
 * @Date: 2019-08-25
 * @Descritpion:  BIO同步阻塞型服务端实现
 */
public class BIOServer {

	/**
	 * 服务端网络IO模型的封装对象
	 */
	ServerSocket server;

	public BIOServer(int port){
		try {
			server = new ServerSocket(port);
			System.out.println("BIO服务已启动，监听端口是：" + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 开始监听，并处理逻辑
	 * @throws IOException 
	 */
	public void listen() throws IOException{
		// (1) 接收新连接线程
		new Thread(() -> {
			while (true) {
				try {
					// (1) 阻塞方法获取新的连接
					Socket socket = server.accept();
					// (2) 每一个新的连接都创建一个线程，负责读取数据
					new Thread(() -> {
						try {
							int len;
							byte[] data = new byte[1024];
							InputStream inputStream = socket.getInputStream();
							// (3) 按字节流方式读取数据
							while ((len = inputStream.read(data)) != -1) {
								System.out.println(new String(data, 0, len));
							}
						} catch (IOException e) {
						}
					}).start();

				} catch (IOException e) {
				}
			}
		}).start();
	}
	
	public static void main(String[] args) throws IOException {
		new BIOServer(8080).listen();
	}

}
