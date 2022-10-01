package Dandi;

import java.io.*;
import java.net.*;

public class HttpServer {
	public HttpServer() throws IOException {
		ServerSocket server = null;
		server = new ServerSocket(8888); // 建立监听，端口是8888
		
		System.out.println(">>>>>服务器正在启动... ...");
		System.out.println(">>>>>启动端口8888... ...");
		System.out.println(">>>>>服务器启动成功... ...");
		
		while (true) { 								// 无限循环，接受客户请求
			Socket sc = server.accept(); 			// 获得客户请求
			System.out.println("接受来自：" + sc.getInetAddress().getHostName() + "的请求");
			Handler handler = new Handler(sc); 		// 处理每个客户短请求的
			new Thread(handler).start();			// 新创建一个线程处理客户的请求
		} // while结束

	}// 构造方法结束

	public static void main(String[] args) throws IOException {
		new HttpServer();
	}
}
