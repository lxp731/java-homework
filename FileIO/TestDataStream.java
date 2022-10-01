package FileIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestDataStream {

	public static void main(String[] args) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));
		for (int i = 0; i < 5; i++) 
			dos.writeDouble(Math.random());//随机生成五个double数
		dos.writeBoolean(true);
		dos.writeChar(65);
		dos.writeInt(1234);
		dos.close();
		DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));
		for (int i = 0; i < args.length; i++) 
			System.out.println(dis.readDouble());
		System.out.println(dis.readBoolean());
		System.out.println(dis.readChar());
		System.out.println(dis.readInt());
		dis.close();
		
	}

}
