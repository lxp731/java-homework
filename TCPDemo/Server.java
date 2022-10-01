package TCPDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-01 17:02
 **/

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        //用bufferedReader包装输入字节流，期间用转换流转换
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str;
        while ((str = br.readLine()) != null) {
            //获取远程主机的地址
            System.out.println(socket.getRemoteSocketAddress() + "说了：" + str);
        }
    }
}
