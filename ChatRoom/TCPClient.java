package ChatRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class TCPClient extends JFrame {

    private JTextField m_enter = new JTextField();
    private JTextArea m_display = new JTextArea();
    private int m_count = 0;
    private static Socket clientSocket;
    //private ExecutorService exec = Executors.newCachedThreadPool();
    private BufferedReader br;
    private PrintWriter pw;

    public TCPClient() {
        super("聊天程序客户端");


        Container c = getContentPane();
        //m_enter.setSize(100,99);
        //m_display.setSize(200,100);
        m_enter.setVisible(true);
        m_display.setVisible(true);
        m_enter.requestFocusInWindow();   //转移输入焦点到输入区域

        //将光标放置在文本区域的尾部
        m_display.setCaretPosition(m_display.getText().length());

        m_display.setEnabled(false);
        c.add(m_enter, BorderLayout.SOUTH);
        c.add(new JScrollPane(m_display), BorderLayout.CENTER);
        // this.add(panel);
        // this.setContentPane(jp);

    }


    public static void main(String[] args) throws Exception {
        TCPClient client = new TCPClient();
        client.setVisible(true);
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.setSize(470, 708);
        client.start();
    }


    public void start() {
        try {
            m_display.append("请创建用户名：");
            clientSocket = new Socket("localhost", 6666);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            PrintWriter pw = new PrintWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
            //ListenrServser l=new ListenrServser();
            m_enter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    try {
                        String s = event.getActionCommand();
                        m_enter.setText("");
                        if (m_count == 0) {
                            pw.println(s);
                            m_display.append("\n'" + s + "'" + "昵称设置成功。\n");

                        } else {

                            pw.println(s);

                        }
                        m_count++;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            String msgString;
            while ((msgString = br.readLine()) != null) {
                m_display.append(msgString + "\n");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}