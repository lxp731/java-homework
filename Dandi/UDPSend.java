package Dandi;

import java.net.*;

public class UDPSend {
	public static void main(String[] args) {
		System.out.println("UDPSend����.......");
		DatagramSocket ds=null;
		try {
			ds=new DatagramSocket();//����DatagramSocket���ڷ�������
			String str="Hello Word";
			//����DatagramPacket,������ת�����ֽ�����
			//�ṩ���ͷ���ip��ַ�Ͷ˿ں�
			DatagramPacket dp=new DatagramPacket(str. getBytes(),
					str.getBytes().length,
					InetAddress.getByName("127.0.0.1"),8888);
					ds.send(dp);//���ݷ���
					System.out.println("UDPSend���ͳɹ�.........." );
		}catch(Exception e) {e.printStackTrace();}
		finally {ds.close();}
	}

}
