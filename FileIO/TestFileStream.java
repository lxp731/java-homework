package FileIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestFileStream {
	
	public static void main(String[] args) throws IOException {
		
//		FileInputStream fis = new FileInputStream("a.txt");
//		FileOutputStream fos = new FileOutputStream("b.txt");
		
		FileReader fis = new FileReader("a.txt");
		FileWriter fos = new FileWriter("b.txt");
		
		int r = 0;
		while((r=fis.read())!=-1) {
			fos.write(r);
		}
		fis.close();
		fos.close();
		System.out.println("copy success!");
	}

}
