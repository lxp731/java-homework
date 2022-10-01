package MutiThread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFilesDemo implements Runnable{
	String filepath;
	String text;
	
	public WriteFilesDemo(String filepath, String text) {
//		super();
		this.filepath = filepath;
		this.text = text;
	}

	public void WriteFiles() throws IOException, InterruptedException {

        File file = new File(filepath);
        FileWriter fileWriter = new FileWriter(filepath);
        fileWriter.write(text);
        fileWriter.close();
        
        System.out.println(text+"��д����"+filepath);
        Thread.sleep(10000);
    }

	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		try {
			this.WriteFiles();
		} catch (IOException | InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
