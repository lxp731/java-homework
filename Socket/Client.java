package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
		//创建一根线
		Socket socket = new Socket("127.0.0.1",8888);
		
		System.out.println("客户端：（嘀咕）能连上不啊...");
		
		//发送消息
		PrintWriter clientsend = new PrintWriter(socket.getOutputStream());
		clientsend.println("客户端：我是客户端。");
		clientsend.flush();
		
		//接受消息
		BufferedReader clientreceive = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		String s = clientreceive.readLine();
		System.out.println(s);
	}

}
