package MutiThread;

import java.io.IOException;

public class WriteFileMain {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO �Զ����ɵķ������
		String filepath1 = "G:\\java/2.txt";
		String filepath2 = "G:\\java/3.txt";
		
		String text1 = "ǰ�����֣������ƻ�.";
		String text2 = "��ҹ�����Һ�ɫ�۾�����ȴ����Ѱ�ҹ����� .";
		
//		WriteFilesDemo writeFileMain1 = new WriteFilesDemo(filepath1,text1);
//		WriteFilesDemo writeFileMain2 = new WriteFilesDemo(filepath2,text2);
//		
//		writeFileMain1.WriteFiles();
//		writeFileMain2.WriteFiles();
		
		Thread writeFileMain1 = new Thread(new WriteFilesDemo(filepath1, text1));
		Thread writeFileMain2 = new Thread(new WriteFilesDemo(filepath2, text2));
		
		writeFileMain1.start();
		writeFileMain2.start();
	}

}
