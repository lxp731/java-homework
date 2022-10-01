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
        System.out.println(">>>>>>>>>>等待好友上线>>>>>>>>");
        Socket socket = serverSocket.accept();
        System.out.println(">>>>>>>>>>一名好友已上线>>>>>>>>");
        System.out.println("可以开始聊天了！");
        System.out.print("请输入：");
        Scanner sc = new Scanner(System.in);
        String content = sc.nextLine();
        Chat chat = new Chat(socket);
        chat.send(content);
        while (true) {
            if (content.equalsIgnoreCase("exit")) {
                break;
            }
            chat.receive();
            System.out.print("请输入:");
            content = sc.nextLine();
            chat.send(content);
        }
    }
}
