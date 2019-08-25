package com.tison.kong.basic.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Tison
 * @Date: 2019-08-25
 * @Descritpion:  BIO客户端实现
 */
public class BIOClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//循环发送数据给服务端
		new Thread(() -> {
			try {
				Socket socket = new Socket("127.0.0.1", 8080);
				while (true) {
					try {
						socket.getOutputStream().write((LocalDateTime.now() + ": hello world").getBytes());
						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
