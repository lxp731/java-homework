package FinalHttpPlus;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest1 {
  public static void main(String[] args) throws IOException {

    ServerSocket serverSocket = new ServerSocket(8080);
    System.out.println("本地服务已启动，端口号为：" + serverSocket.getLocalPort());

    while (true) {
      // 循环监听 接受
      Socket socket = serverSocket.accept();
      System.out.println("远程主机地址:" + socket.getRemoteSocketAddress());
      // 创建对象
      Hanlder2 hanlder = new Hanlder2(socket);
      // 开启线程
      Thread myHanlder = new Thread(hanlder);
      myHanlder.start();
    }
    //        server.close();

  }
}

class Hanlder2 implements Runnable {
  Socket socket = null;

  public Hanlder2(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      InputStream is = this.socket.getInputStream();
      byte[] buffer = new byte[2048];
      int len = is.read(buffer);
      //            bufferedReader.read()
      String content = new String(buffer, 0, len);
      String[] lines = content.split("\r\n");
      //      for (int i = 0; i < lines.length; i++) {
      //        System.out.println(lines[i]);
      //      }
      if (lines[0].startsWith("GET")) {
        OutputStream out = socket.getOutputStream();
        FileInputStream fileInputStream = null;
        File file = new File("D:\\IDEA\\work\\TinyTerm\\src\\FinalHttpPlus\\post.html");
        fileInputStream = new FileInputStream(file);
        byte[] buf = new byte[2048];
        int lenght = 0;
        out.write("HTTP/1.1 200 OK\r\n".getBytes());
        out.write("Content-Type:text/html\r\n\r\n".getBytes());
        while ((lenght = fileInputStream.read(buf)) != -1) {
          out.write(buf, 0, lenght);
          out.flush();
        }
        fileInputStream.close();
        out.close();
      } else if (lines[0].startsWith("POST")) {
        System.out.println("获取到的参数：" + lines[lines.length - 1]);
      }
      System.out.println("--------------------------end");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
