package Dandi;

//HTTP�������д���ͻ��������Handler��
import java.io.*;
import java.net.*;

public class Handler implements Runnable {
	private Socket sc;// ����Socket��

	public Handler(Socket sc) {
		this.sc = sc;
	}

	public void run() {
		BufferedReader request = null;
		OutputStream out = null;
		try {
			// ��ÿͻ�����������HTTP�ͻ����������Ϣ��
			request = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			out = sc.getOutputStream();
			String str = "";
//			 while((str=request.readLine())!=null) {
//			 //���HTTP�����������Ϣ
//			 System.out.println(str);
//			 }
			str = request.readLine();// ��ȡHTTP������Ϣ�ĵ�һ��
			if (str.indexOf("GET") > -1) {// �����GET������ļ���
				String fileName = str.replace("GET", "");// ���������ļ���
				fileName = fileName.replace("HTTP/1.1", "").trim();
				if (fileName.length() == 1) {// ���û���ļ�����Ĭ����index.html
					fileName = "index.html";
				}
				response(out, fileName);// ��Ӧ�ͻ���������ļ�
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void response(OutputStream out, String fileName) {
		FileInputStream in = null;
		try {
			// �������˴��html��·����webapps����������ļ���������
			in = new FileInputStream("G:\\java" + fileName);
			byte[] buf = new byte[1024];
			int tmp = 0;
			// ��ͻ��˷���HTTP��Ӧ״̬����Ϣ
			out.write("HTTP/1.1 200 OK\r\n".getBytes());
			out.write("Content-Type:text/html\r\n\r\n".getBytes());
			while ((tmp = in.read(buf)) != -1) {// ��ͻ��˷���������Ϣ
				out.write(buf, 0, tmp);
				out.flush();
			}
		} // try����
		catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				out.write("<Font color=red>������ļ�������</Font>".getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
