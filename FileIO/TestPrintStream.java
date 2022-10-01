package FileIO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class TestPrintStream {

	public static void main(String[] args) {
		try {
			PrintStream ps = new PrintStream(new FileOutputStream("ps.txt"));
			ps.println("2021年9月1日，是一个特殊的节日....");
			//连续输入5个随机数
			for (int i = 0; i < 5; i++)
				ps.print((int)(Math.random()*100)+" ");
			//输入一个换行符
			ps.println();	
			//输入一个Boolean
			ps.print(false);
			ps.close();
			
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
