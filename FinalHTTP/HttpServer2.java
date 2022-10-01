package FinalHTTP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer2 {
    public static void main(String[] args) {
        startServer(8081);
    }

    private static void startServer(int port) {
        System.out.println("======>startServer");
        try {
            ServerSocket server = new ServerSocket(port);
//            server.setSoTimeout(0);
            Socket socket = null;
            while ((socket = server.accept()) != null) {
//                socket.setSoTimeout(2000);

                InputStream is = socket.getInputStream();

                byte[] buffer = new byte[1024];
                int len = is.read(buffer);
                getContent(buffer, len);
                responseText(socket);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=====>");
    }

    private static void responseText(Socket pSocket) {
        StringBuffer buffer = new StringBuffer();
         buffer.append("HTTP/1.1 200 OK\r\n");
         buffer.append("Date: Tue, 14 Sep 1999 02:19:57 GMT\r\n");
         buffer.append("Server: Apache/1.2.6\r\n");
         buffer.append("Connection: close\r\n");
         buffer.append("Content-Type: text/html\r\n");
         buffer.append("\r\n");
        try {
            OutputStream os = pSocket.getOutputStream();
            os.write(buffer.toString().getBytes());
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String getContent(byte[] data, int len) {
        String content = new String(data, 0, len);
        String[] lines = content.split("\r\n");
        String line = lines[lines.length - 1];
        int index = line.indexOf("=");
        String result = line.substring(index + 1);
        System.out.println("result: " + result);
        return "";
    }
}
