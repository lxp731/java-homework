package UDPDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSend {

    public static void main(String[] args) throws IOException {
        // TODO 自动生成的方法存根
        //创建一个发送数据的对象（人）不需要设置端口号，自带默认的端口号
        DatagramSocket dgs = new DatagramSocket();
        //实际的发送内容（韭菜）
        String str = "java just so so!";
        //字节数组来存储发送的数据
        byte[] conent = str.getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        //TODO 创建一个封装数据的对象（韭菜盘子）
        DatagramPacket dgp = new DatagramPacket(conent, conent.length, address, 8080);
//		DatagramPacket dgp = new DatagramPacket(conent,conent.length,InetAddress.getLocalHost(),8080);
        System.out.println("Sending......");
        //开始发送数据
        dgs.send(dgp);
        //发送完毕，关闭连接，释放资源
        dgs.close();
        System.out.println("Done!");
    }

}
