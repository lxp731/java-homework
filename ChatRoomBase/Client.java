package ChatRoomBase;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-01 15:45
 */
public class Client {
    public static void main(String[] args) throws IOException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        Socket socket = new Socket(inetAddress, 8080);
        Scanner sc = new Scanner(System.in);
        String content = "";
        Chat chat = new Chat(socket);
        chat.receive();
        while (true) {
            if (content.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.print("«Î ‰»Î:");
            content = sc.nextLine();
            chat.send(content);
            chat.receive();
        }
    }
}
