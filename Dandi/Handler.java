package Dandi;

//HTTP服务器中处理客户端请求的Handler类
import java.io.*;
import java.net.*;

public class Handler implements Runnable {
	private Socket sc;// 定义Socket类

	public Handler(Socket sc) {
		this.sc = sc;
	}

	public void run() {
		BufferedReader request = null;
		OutputStream out = null;
		try {
			// 获得客户端输入流（HTTP客户端请求的信息）
			request = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			out = sc.getOutputStream();
			String str = "";
//			 while((str=request.readLine())!=null) {
//			 //获得HTTP请求的所有信息
//			 System.out.println(str);
//			 }
			str = request.readLine();// 读取HTTP请求信息的第一行
			if (str.indexOf("GET") > -1) {// 如果是GET请求的文件名
				String fileName = str.replace("GET", "");// 获得请求的文件名
				fileName = fileName.replace("HTTP/1.1", "").trim();
				if (fileName.length() == 1) {// 如果没有文件名，默认是index.html
					fileName = "index.html";
				}
				response(out, fileName);// 响应客户端请求的文件
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
			// 服务器端存放html的路径是webapps，获得请求文件的输入流
			in = new FileInputStream("G:\\java" + fileName);
			byte[] buf = new byte[1024];
			int tmp = 0;
			// 向客户端发送HTTP响应状态行信息
			out.write("HTTP/1.1 200 OK\r\n".getBytes());
			out.write("Content-Type:text/html\r\n\r\n".getBytes());
			while ((tmp = in.read(buf)) != -1) {// 向客户端发送正文信息
				out.write(buf, 0, tmp);
				out.flush();
			}
		} // try结束
		catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				out.write("<Font color=red>请求的文件不存在</Font>".getBytes());
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
