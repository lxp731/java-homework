package TCPDemo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-01 17:03
 **/

public class Client {
    public static void main(String[] args) throws IOException {
        //为socket打造参数
        InetAddress inetAddress = InetAddress.getLocalHost();
        //设置socket的参数
        Socket socket = new Socket(inetAddress, 8080);
        //为客户端发送消息写输出流
        OutputStream os = socket.getOutputStream();
        //把低级的字节输出流包装成打印流,打印流擅长处理文字
        PrintStream ps = new PrintStream(os);
        //发送的消息
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("请输入：");
            String str = sc.nextLine();
            ps.println(str);
            //发送消息后必须刷新
            ps.flush();
        }
        //一般不要去关闭socket
//      socket.close();
    }
}
