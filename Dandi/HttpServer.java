package Dandi;

import java.io.*;
import java.net.*;

public class HttpServer {
	public HttpServer() throws IOException {
		ServerSocket server = null;
		server = new ServerSocket(8888); // �����������˿���8888
		
		System.out.println(">>>>>��������������... ...");
		System.out.println(">>>>>�����˿�8888... ...");
		System.out.println(">>>>>�����������ɹ�... ...");
		
		while (true) { 								// ����ѭ�������ܿͻ�����
			Socket sc = server.accept(); 			// ��ÿͻ�����
			System.out.println("�������ԣ�" + sc.getInetAddress().getHostName() + "������");
			Handler handler = new Handler(sc); 		// ����ÿ���ͻ��������
			new Thread(handler).start();			// �´���һ���̴߳���ͻ�������
		} // while����

	}// ���췽������

	public static void main(String[] args) throws IOException {
		new HttpServer();
	}
}
