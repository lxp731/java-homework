package Dandi;

import java.net.*;

public class UDPSend {
	public static void main(String[] args) {
		System.out.println("UDPSend启动.......");
		DatagramSocket ds=null;
		try {
			ds=new DatagramSocket();//创建DatagramSocket用于发送数据
			String str="Hello Word";
			//创建DatagramPacket,将数据转换成字节数组
			//提供发送方的ip地址和端口号
			DatagramPacket dp=new DatagramPacket(str. getBytes(),
					str.getBytes().length,
					InetAddress.getByName("127.0.0.1"),8888);
					ds.send(dp);//数据发送
					System.out.println("UDPSend发送成功.........." );
		}catch(Exception e) {e.printStackTrace();}
		finally {ds.close();}
	}

}
