package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
		//����һ����
		Socket socket = new Socket("127.0.0.1",8888);
		
		System.out.println("�ͻ��ˣ����ֹ��������ϲ���...");
		
		//������Ϣ
		PrintWriter clientsend = new PrintWriter(socket.getOutputStream());
		clientsend.println("�ͻ��ˣ����ǿͻ��ˡ�");
		clientsend.flush();
		
		//������Ϣ
		BufferedReader clientreceive = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		String s = clientreceive.readLine();
		System.out.println(s);
	}

}
