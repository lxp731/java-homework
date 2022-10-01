package GUI;

import java.io.*;

public class CSOReadFile {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO 自动生成的方法存根
		String file = "G:\\java/Move Your Body.txt";
		Reader reader = new FileReader(file);
		char[] c = new char[1];
		try {
			while(reader.read(c) > -1){
				String s = new String(c);
				System.out.print(s);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
