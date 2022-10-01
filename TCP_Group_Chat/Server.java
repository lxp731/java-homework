package TCP_Group_Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-01 17:02
 **/

public class Server {

    //定义一个静态的List集合来存储在线的Socket
    public static List<Socket> allOnlineSockets = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8082);
        while (true) {
            //等待客户端的连接
            Socket socket = serverSocket.accept();
            //获取远程主机的地址
            System.out.println(socket.getRemoteSocketAddress() + "已上线");
            allOnlineSockets.add(socket);
            new ServerForward(socket).start();
        }
    }
}

class ServerForward extends Thread {
    private Socket socket;

    public ServerForward(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(socket.getRemoteSocketAddress() + "说了：" + str);
                sendMsgToAll(str);
            }
        } catch (Exception e) {
            System.out.println(socket.getRemoteSocketAddress() + "下线了！！！");
            Server.allOnlineSockets.remove(socket);
        }
    }

    private void sendMsgToAll(String line) throws Exception {
        for (Socket socket : Server.allOnlineSockets) {
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println(line);
            ps.flush();
        }
    }
}