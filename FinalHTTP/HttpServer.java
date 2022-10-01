package FinalHTTP;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public HttpServer(int port) throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println(">>>>>>>>>>>>正在启动SERVER............");
        System.out.println(">>>>>>>>>>>>>端口号：" + port + ".............");
        System.out.println(">>>>>>>>>>>>SERVER启动成功............");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("接收到来自" + socket.getInetAddress().getHostAddress() + "的请求");
            Hanlder hanlder = new Hanlder(socket);
//            hanlder.run();
            Thread myHanlder = new Thread(hanlder);
            myHanlder.start();
        }
    }

    public static void main(String[] args) throws IOException {
        new HttpServer(8080);
    }
}
