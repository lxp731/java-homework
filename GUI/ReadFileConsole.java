package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileConsole {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		File file = new File("G:\\java/Move Your Body.txt");
		FileReader fileReader = new FileReader(file);
		int size = (int)file.length();
		char[] ch = new char[size];
		int length = 0;
		while (fileReader.ready()) {
			length = length + fileReader.read(ch,0,size-length);
		}
		System.out.println(ch);
	}

}
