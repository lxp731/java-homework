package TCP_SC_Thread;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-01 22:30
 **/

public class Client {
    public static void main(String[] args) throws Exception {

        InetAddress inetAddress = InetAddress.getLocalHost();

        Socket socket = new Socket(inetAddress, 8080);

        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os);

        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.print("请输入：");
            String str = sc.nextLine();
            ps.println(str);
            ps.flush();
        }
    }
}
