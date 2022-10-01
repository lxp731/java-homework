package MutiThread;

import java.io.IOException;

public class WriteFileMain {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO 自动生成的方法存根
		String filepath1 = "G:\\java/2.txt";
		String filepath2 = "G:\\java/3.txt";
		
		String text1 = "前尘种种，似烟似幻.";
		String text2 = "黑夜给了我黑色眼睛，我却用它寻找光明。 .";
		
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
