package Dandi;

import java.net.*;

public class UDPReceive {
	public static void main(String[] args) {
		System.out.println("UDPReceive����,�˿�8888.......");
		DatagramSocket ds=null;
		try {
			ds=new DatagramSocket(8888);//����DatagramSocket���ڷ�������
			byte[] buf=new byte[124];//�����������ݵ��ֽ�����
			DatagramPacket dp=
					new DatagramPacket(buf,buf.length);//����DatagramPacket
			System.out.println("UDPReceive�ȴ���������.......");
			ds.receive(dp);//�������ݱ���
			//�����յ�����ת�����ַ���
			String msg=new String(buf,0,dp.getLength());
			String ip=dp.getAddress().getHostAddress();//��ô��䷽��ip��ַ
			System.out.println("UDPReceive��������"+ip+"\n\t"+InetAddress.getLocalHost().getHostName()+"-402   ����Ϣ��"+msg);
		}catch(Exception e) {e.printStackTrace();}
		finally{ds.close();}
	}//main��������
}
