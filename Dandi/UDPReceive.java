package Dandi;

import java.net.*;

public class UDPReceive {
	public static void main(String[] args) {
		System.out.println("UDPReceive启动,端口8888.......");
		DatagramSocket ds=null;
		try {
			ds=new DatagramSocket(8888);//创建DatagramSocket用于发送数据
			byte[] buf=new byte[124];//创建接受数据的字节数组
			DatagramPacket dp=
					new DatagramPacket(buf,buf.length);//创建DatagramPacket
			System.out.println("UDPReceive等待接收数据.......");
			ds.receive(dp);//接收数据报包
			//将接收的数据转换成字符串
			String msg=new String(buf,0,dp.getLength());
			String ip=dp.getAddress().getHostAddress();//获得传输方的ip地址
			System.out.println("UDPReceive接收来自"+ip+"\n\t"+InetAddress.getLocalHost().getHostName()+"-402   的信息："+msg);
		}catch(Exception e) {e.printStackTrace();}
		finally{ds.close();}
	}//main方法结束
}
