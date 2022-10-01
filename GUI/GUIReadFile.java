package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GUIReadFile extends JFrame{
	public GUIReadFile() throws IOException {
		// TODO 自动生成的构造函数存根
		JTextArea jTextArea = new JTextArea();
		jTextArea.setText(readFile());
		jTextArea.setFont(new Font("微软雅黑",Font.BOLD,(int) 10));
		this.add(jTextArea);
		this.pack();
		this.setVisible(true);
	}
	
	public String readFile() throws IOException {
		File file = new File("G:\\java/Move Your Body.txt");
		FileReader fileReader = new FileReader(file);
		int size = (int)file.length();
		char[] ch = new char[size];
		int length = 0;
		while (fileReader.ready()) {
			length = length + fileReader.read(ch,0,size-length);
		}
		return new String(ch);
	}
	
	public static void main(String[] args) throws IOException {
		GUIReadFile gfile = new GUIReadFile();
		gfile.setVisible(true);
	}
}
