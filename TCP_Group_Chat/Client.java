package TCP_Group_Chat;

import java.io.*;
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
        Socket socket = new Socket(inetAddress, 8082);

        //创建一个独立的线程去接受消息
        new ClientReceive(socket).start();

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
    }
}


class ClientReceive extends Thread {
    private Socket socket;

    public ClientReceive(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str;
            while ((str = br.readLine()) != null)
                System.out.println("收到新的会话消息：" + str);
        } catch (Exception e) {
            System.out.println("你已被管理员移除群聊！！！");
        }
    }
}
