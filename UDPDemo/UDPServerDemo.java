package UDPDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerDemo {

    public static void main(String[] args) throws IOException {
        // TODO 自动生成的方法存根
        String info = "haohao 学习";
        byte[] bytes = info.getBytes();
        DatagramPacket dp = new DatagramPacket(bytes,
                0, bytes.length, InetAddress.getByName("127.0.0.7"), 8080);
        //本程序的端口号
        DatagramSocket socket = new DatagramSocket(8888);
        socket.send(dp);
        socket.close();
    }

}
