package UDPDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceive {

    public static void main(String[] args) throws IOException {
        // TODO 自动生成的方法存根
        // 接收端一定要有端口号，要不找不见家门
        DatagramSocket dgs = new DatagramSocket(8080);
        // TODO 创建字节数组来存储接收到的消息 UDP协议一次接受最大数据为64K
        byte[] content = new byte[1024 * 64];
        // 创建接受的盘子 只需要知道内容的长度就OK
        DatagramPacket dgp = new DatagramPacket(content, content.length);
        System.out.println("Receving......");
        // 开始接受内容
        dgs.receive(dgp);
        String str = new String(content, 0, dgp.getLength());
        System.out.println("Done!" + str);

        // 可以拿到发送方的IP和PORT
        String ip = dgp.getSocketAddress().toString();
        System.out.println("对方的地址：" + ip);
        int port = dgp.getPort();
        System.out.println("对方的地址：" + port);
        System.out.println();
    }
}
