
package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		// 
		ServerSocket serverSocket = new ServerSocket(8888);
		
		System.out.println("�����������ڼ���......");
		
		//�������շ���Ϣ����Ҫ��һ�����ˣ�ֻ�Ǽ����Ļ�����Ҫ��
		Socket socket = serverSocket.accept();
		System.out.println("�����������ӳɹ�!");
		
		//������Ϣ
		BufferedReader serverreceive = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		String s = serverreceive.readLine();
		System.out.println(s);
		
		//������Ϣ
		PrintWriter serversend = new PrintWriter(socket.getOutputStream());
		serversend.println("�����������Ƿ�������");
		serversend.flush();
	}

}
