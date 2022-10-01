package TCP_Private_Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-02 10:47
 **/

public class Server {
    public static void main(String[] args) throws IOException {
        HashMap<String, Socket> Online = new HashMap<String, Socket>();
        String name = "";
        ServerSocket serverSocket = new ServerSocket(6666);
        while (true) {
            Socket socket = serverSocket.accept();
            Online.put(name, socket);
        }
    }
}
