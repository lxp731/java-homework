package TCP_SC_Thread;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-02 10:02
 **/

public class ServeThread extends Thread {
    private Socket socket;

    public ServeThread(Socket socket) {
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
            }
        } catch (Exception e) {
            System.out.println(socket.getRemoteSocketAddress() + "已下线");
        }
    }
}
