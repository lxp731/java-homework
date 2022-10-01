package MyChatRoom;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server extends JFrame {
    //    ServerPage sp;
    //创建G_Menu的对象
    Menu gm = new Menu();
    //服务器的ServerSocket
    private ServerSocket server;
    //保存要转发的消息
    public ArrayList<PrintWriter> list;
    public static String user;
    //定义用户集合
    public static ArrayList<User> list1 = new ArrayList<User>();
    //存放用户信息的变量
    public User uu;

    JTextArea jta = new JTextArea("欢迎来到疼逊扣扣\n");

    //    public Server() {
////        this.user = user;
//    }
    public void init() {
        Container cp = getContentPane();
        jta.setBackground(new Color(0xC8D6CA));
        JScrollPane jsp = new JScrollPane(jta);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);            //总是显示
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);    //需要时显示
        cp.add(jsp);
//        cp.add(jta);
        this.setTitle("服务器后台");
        this.setVisible(true);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
    }

    public void getServer(int port) {
//        p.jf.setVisible(false);
        init();
        //发送的消息先存起来
        list = new ArrayList<PrintWriter>();
        try {
            server = new ServerSocket(port);
            System.out.println("服务器启动，开始监听......");
            jta.append("服务器启动，开始监听......\n");
            while (true) {
                Socket client = server.accept();//接收客户端线程
                PrintWriter writer = new PrintWriter(client.getOutputStream());
                list.add(writer);
                Thread t = new Thread(new Chat(client));
                t.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class Chat implements Runnable {
        Socket socket;
        private BufferedReader br;
        private String msg;
        private String mssg = "";

        public Chat(Socket socket) {
            try {
                this.socket = socket;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public void run() {
            try {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while ((msg = br.readLine()) != null) {

                    if (msg.equals("1008611"))//匹配字符串 显示好友列表
                    {
                        //先把消息存起来
                        msg = br.readLine();
                        String[] st = msg.split(":");//将用户信息跟消息分隔开
                        uu = new User(st[0], st[1], socket);//将用户信息添加到User对象中
                        list1.add(uu);//将对象添加到用户集合
                        Iterator<User> it = Server.list1.iterator();//遍历用户集合
                        //如果迭代还有元素，返回为true，程序继续执行
                        while (it.hasNext()) {
                            User use = it.next();
                            msg = use.getName() + "(" + use.getSex() + "):";
                            mssg += msg;//将所有的用户信息连接成一个字符串
                        }
                        sendMessage("1008611");//显示好友列表匹配标识
                        sendMessage(mssg);//群发消息
                    } else if (msg.equals("10010"))//显示说话消息
                    {
                        msg = br.readLine();
                        System.out.println(msg + "\n");
                        jta.append(msg + "\n");
                        sendMessage("10010");//显示说话信息匹配标识
                        sendMessage(msg);
                    } else if (msg.equals("10086"))//显示进入聊天室
                    {
                        msg = br.readLine();
                        System.out.println(msg);
                        jta.append(msg + "\n");
                        sendMessage("10086");//进入聊天室匹配标识
                        sendMessage(msg);
                    } else if (msg.equals("841163574"))//私聊
                    {
                        msg = br.readLine();
                        String[] rt = msg.split("1072416535");//把传进来的用户信息跟说话内容分开
//                        System.out.println(rt[1]);//在服务器端显示说话内容
//                        System.out.println();
                        jta.append(rt[1] + "\n");
                        String[] tg = rt[0].split(":");//因为是私聊，传过来两个用户的用户信息，这句作用是再把两个用户信息分开
                        Iterator<User> iu = Server.list1.iterator();//遍历用户集合
                        while (iu.hasNext()) {
                            User se = iu.next();
                            if (tg[1].equals(se.getName() + "(" + se.getSex() + ")"))//如果传进来的用户信息跟集合中的用户信息吻合
                            {
                                try {
                                    PrintWriter pwriter = new PrintWriter(se.getSock().getOutputStream());//建立用户自己的流
                                    pwriter.println("841163574");//匹配标识
                                    pwriter.println(rt[1]);//向单独用户发送消息
                                    pwriter.flush();
                                    System.out.println(rt[1]);
//                                    jta.append(rt[1] + "\n");
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else if (tg[0].equals(se.getName()))//如果传进来的用户信息跟集合中的用户信息吻合
                            {
                                try {
                                    PrintWriter pwr = new PrintWriter(se.getSock().getOutputStream());//建立用户自己的流
                                    pwr.println("841163574");//匹配标识
                                    pwr.println(rt[1]);//向单独用户发送消息
                                    pwr.flush();
                                    System.out.println(rt[1]);
//                                    jta.append(rt[1] + "\n");
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    } else if (msg.equals("456987"))//下线
                    {
                        msg = br.readLine();
                        System.out.println(msg);//在服务端显示信息
                        jta.append(msg + "\n");
                        sendMessage("456987");//匹配字符串
                        sendMessage(msg);//匹配完毕后群发消息
                        String[] si = msg.split(":");//将传过来的用户名跟信息分隔开
                        Iterator<User> at = Server.list1.iterator();//遍历用户集合
                        while (at.hasNext()) {
                            User sr = at.next();
                            if (sr.getName().equals(si[0]))//如果传过来的用户名跟用户集合里的用户吻合
                            {
                                list1.remove(sr);//将吻合的用户移除
                                sr.getSock().close();//关闭此用户的socket
                            }
                        }
                        break;
                    } else if (msg.equals("123654"))//刷新
                    {

                        String mssge = "";
                        Iterator<User> iter = Server.list1.iterator();//遍历用户集合
                        while (iter.hasNext()) {
                            User uus = iter.next();
                            msg = uus.getName() + "(" + uus.getSex() + "):";
                            mssge += msg;//将所有的用户信息连接成一个字符串

                        }
                        sendMessage("123654");//发送刷新匹配标识
                        sendMessage(mssge);//群发消息
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendMessage(String message)//群发消息方法
    {
        try {
            for (PrintWriter pw : list)//输出流集合
            {
                pw.println(message);
                pw.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

