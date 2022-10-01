package MyChatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class Recove implements Runnable {
    public String user;
    private Socket socket;
    public BufferedReader br;
    //    private PrintWriter pw;
    private String msg;
    //创建G_Menu的对象 gm
    Menu gm = new Menu();


    public Recove(Socket socket, String user) throws Exception {
        this.socket = socket;
        this.user = user;
    }

    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while ((msg = br.readLine()) != null) {
                String message = msg;
                if (
                        message.equals("1008611"))//匹配字符串 显示好友列表
                {
                    gm.listModel1.clear();//接收前清空好友列表
                    gm.jcomb.removeAllItems();//清空JCombox
                    gm.jcomb.addItem("所有人");
                    message = br.readLine();
                    String[] str = message.split(":");//将接收到的所有用户信息分隔开
                    for (String ss : str) {
                        gm.listModel1.addElement(ss);//将所有用户信息添加到好友列表
                        gm.jcomb.addItem(ss);//将所有用户信息添加到JCombox
                    }
                } else if (message.equals("841163574"))//私聊
                {
                    message = br.readLine();
                    System.out.println("收到：" + message);//在服务器端显示私聊消息
                    gm.jta2.append(message + "\n");//在私聊频道显示私聊信息
                } else if (message.equals("10010"))//显示说话消息
                {
                    message = br.readLine();
                    System.out.println("收到：" + message);//在服务器端显示说话信息
                    gm.jta1.append(message + "\n");//在公共频道显示说话信息
                    gm.jta2.append(message + "\n");//在我的频道显示说话信息
                } else if (message.equals("10086"))//显示进入聊天室
                {
                    message = br.readLine();
                    gm.jta1.append(message + "\n");//在公共频道显示进入聊天室信息
                    gm.jta2.append(message + "\n");//在我的频道显示进入聊天室信息
                } else if (message.equals("123654"))//刷新
                {
                    gm.listModel1.clear();//将好友列表清空
                    gm.jcomb.removeAllItems();//将JCombox 清空
                    gm.jcomb.addItem("所有人");
                    message = br.readLine();
                    String[] sr = message.split(":");//将接收到的用户信息分隔开
                    for (String sst : sr) {
                        gm.listModel1.addElement(sst);//将刷新后所有用户信息添加到好友列表
                        gm.jcomb.addItem(sst);//将刷新后所有用户信息添加到JCombox
                    }
                } else if (message.equals("456987"))//下线
                {
                    message = br.readLine();
                    gm.jta1.append(message + "\n");//在公共频道显示用户下线信息
                    gm.jta2.append(message + "\n");//在我的频道显示用户下线信息
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
