package UDPMulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPSend {

  public static void main(String[] args) throws IOException {

    // 创建一个发送数据的对象（人）不需要设置端口号，自带默认的端口号
    DatagramSocket dgs = new DatagramSocket();
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.print("Insert:");
      String str = sc.nextLine();

      if ("exit".equals(str)) {
        System.out.println("退出成功");
        dgs.close();
        break;
      }

      // 字节数组来存储发送的数据
      byte[] conent = str.getBytes();
      // 创建一个封装数据的对象（韭菜盘子）
      DatagramPacket dgp =
          new DatagramPacket(conent, conent.length, InetAddress.getByName("224.1.0.8"), 8080);

      // 开始发送数据
      dgs.send(dgp);
      // 发送完毕，关闭连接，释放资源
    }
  }
}
