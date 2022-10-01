package TCP_SC_Thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-01 22:31
 **/

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {

            Socket socket = serverSocket.accept();
            System.out.println(socket.getRemoteSocketAddress() + "上线了。");
            new ServeThread(socket).start();
        }
    }

}
