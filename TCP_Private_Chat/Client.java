package TCP_Private_Chat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-02 10:46
 **/

public class Client {
    public static void main(String[] args) throws Exception {
        InetAddress inetAddress = InetAddress.getLocalHost();
        Socket socket = new Socket(inetAddress, 6666);
        new ReceiveMsg(socket).start();
        PrintStream ps = new PrintStream(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("请输入：");
            String line = sc.nextLine();
            ps.println(line);
            ps.flush();
        }
    }
}

class ReceiveMsg extends Thread {
    private Socket socket;

    public ReceiveMsg(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println("收到" + socket.getRemoteSocketAddress() + "的新会话：" + str);
            }
        } catch (Exception e) {
            System.out.println("对方已下线！！！");
        }
    }
}

