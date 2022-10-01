package FinalHTTP;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class Hanlder implements Runnable {
    Socket socket;
    int contentLength = 0;
    String boundary = null;

    public Hanlder(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        OutputStream out = null;
        try {
            InputStream is = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            out = this.socket.getOutputStream();

            DataInputStream reader = new DataInputStream((socket.getInputStream()));

            String str = "";
            str = bufferedReader.readLine();

            Map<String, String> headers = new HashMap<>(10);
            String line = "";
            // readLine 读取的一行内容, 是会自动去掉换行符的. 对于空行来说, 去掉了换行符, 就变成空字符串
            while ((line = bufferedReader.readLine()) != null && line.length() != 0) {
                // 不能使用 : 来切分. 像 referer 字段, 里面的内容是可能包含 : .
                String[] headerTokens = line.split(": ");
                headers.put(headerTokens[0], headerTokens[1]);
                System.out.println(headerTokens[0]+" : "+headerTokens[1]);
            }

            if (str.contains("GET")) {
                String filename = str.replace("GET", "");
                filename = filename.replace("HTTP/1.1", "").trim();
                if (filename.trim().length() <= 0) {
                    filename = "index.html";
                }
                writer(out, filename);
                socket.close();
            }
            System.out.println("----------------------------------------");
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void writer(OutputStream out, String filename) throws IOException {
        FileInputStream fileInputStream = null;
        File file = new File("G:\\java" + filename);
        fileInputStream = new FileInputStream(file);
//        FileReader fileReader = new FileReader(file);
        byte[] buf = new byte[1024];
        int lenght = 0;

        out.write("HTTP/1.1 200 OK\r\n".getBytes());
        out.write("Content-Type:text/html\r\n\r\n".getBytes());
        while ((lenght = fileInputStream.read(buf)) != -1) {
            out.write(buf, 0, lenght);
            out.flush();
        }

        fileInputStream.close();
        out.close();
    }

}

