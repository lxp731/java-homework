package MyChatRoom;

import java.net.InetAddress;

public class Socket//客户端
{
    //    private static final int PORT = 6666;//端口
    public static String user;
    public static java.net.Socket socket;

    //从G_menu传回来的
    public Socket(String user, int port) throws Exception {
        this.user = user;
        InetAddress inetAddress = InetAddress.getLocalHost();
        socket = new java.net.Socket(inetAddress, port);//建立socket连接
        System.out.println("【" + user + "】欢迎来到聊天室！");
        Thread tt = new Thread(new Recove(socket, user));//建立客户端线程
        tt.start();//启动线程
    }

//    public static void main(String[] args) throws Exception {
//        new Socket(user);
//    }
}

