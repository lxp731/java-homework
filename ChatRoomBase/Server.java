package ChatRoomBase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-01 14:49
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println(">>>>>>>>>>�ȴ���������>>>>>>>>");
        Socket socket = serverSocket.accept();
        System.out.println(">>>>>>>>>>һ������������>>>>>>>>");
        System.out.println("���Կ�ʼ�����ˣ�");
        System.out.print("�����룺");
        Scanner sc = new Scanner(System.in);
        String content = sc.nextLine();
        Chat chat = new Chat(socket);
        chat.send(content);
        while (true) {
            if (content.equalsIgnoreCase("exit")) {
                break;
            }
            chat.receive();
            System.out.print("������:");
            content = sc.nextLine();
            chat.send(content);
        }
    }
}
