package UDPBroadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceive {

  public static void main(String[] args) throws IOException {

    // 接收端一定要有端口号，要不找不见家门
    DatagramSocket dgs = new DatagramSocket(8080);

    // 创建字节数组来存储接收到的消息 UDP协议一次接受最大数据为64K
    byte[] content = new byte[1024 * 64];

    // 创建接受的盘子 只需要知道内容的长度就OK
    DatagramPacket dgp = new DatagramPacket(content, content.length);
    System.out.println("Receving......");

    // 开始接受内容
    while (true) {
      dgs.receive(dgp);
      String str = new String(content, 0, dgp.getLength());

      // 可以拿到发送方的IP和PORT
      String ip = dgp.getAddress().toString();
      int port = dgp.getPort();
      System.out.println("收到地址为：" + ip + ",端口为" + port + ",消息为：" + str);
    }
  }
}
