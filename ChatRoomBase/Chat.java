package ChatRoomBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-01 15:26
 */
public class Chat {
    Socket socket;

    public Chat(Socket socket) {
        this.socket = socket;
    }

    // TODO ������Ϣ�ķ���
    public void send(String content) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(content);
        printWriter.flush();
    }

    // TODO ������Ϣ�ķ���
    public void receive() throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("�յ��Է��Ự��" + bufferedReader.readLine());
    }
}
