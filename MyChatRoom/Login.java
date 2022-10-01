package MyChatRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Login extends Frame implements ActionListener {
    JFrame jf = new JFrame("聊天登陆");

    JPanel jp1 = new JPanel();
    JPanel jp2 = new JPanel();
    JPanel jp3 = new JPanel();
    JPanel jp4 = new JPanel();
    JPanel jp5 = new JPanel();

    JLabel jl1 = new JLabel("昵称：");
    JLabel jl2 = new JLabel("地址：");
    JLabel jl3 = new JLabel("端口：");

    JRadioButton jrb1 = new JRadioButton("帅哥");
    JRadioButton jrb2 = new JRadioButton("美女");
    JRadioButton jrb3 = new JRadioButton("不告诉你");

    public JTextField jtf1 = new JTextField(15);
    public JTextField jtf2 = new JTextField(10);
    public JTextField jtf3 = new JTextField(10);

    JButton jb1 = new JButton("连接");
    JButton jb2 = new JButton("断开");

    ButtonGroup gb = new ButtonGroup();

    public void init()// 显示登录界面
    {
        //添加按钮的监听器
        jb1.addActionListener(this);
        jb2.addActionListener(this);

        //面板1添加姓名标签、姓名输入框
        jp1.add(jl1);
        jp1.add(jtf1);
        jp1.setBackground(new Color(0xC2D3DA));

        //面板2添加三个单选按钮
        jp2.add(jrb1);
        jp2.add(jrb2);
        jp2.add(jrb3);
        jp2.setBackground(new Color(0xC2D3DA));

        //面板3添加地址和地址输入框、端口和端口输入框
        jp3.add(jl2);
        jp3.add(jtf2);
        jp3.add(jl3);
        jp3.add(jtf3);
        jp3.setBackground(new Color(0xC2D3DA));

        //面板4添加”连接“”断开“两个按钮
        jp4.add(jb1);
        jp4.add(jb2);
        jp4.setBackground(new Color(0xC8D6CA));

        //面板5把面板1、2、3、4设置为4行1列
        jp5.setLayout(new GridLayout(4, 1));
        jp5.add(jp3);
        jp5.add(jp1);
        jp5.add(jp2);
        jp5.add(jp4);

        //面板5添加到框架
        jf.add(jp5);

        jtf2.setText("127.0.0.1");
        jtf2.setEnabled(false);
        jtf3.setText("6666");

        //单选按钮成组
        gb.add(jrb1);
        gb.add(jrb2);
        gb.add(jrb3);

        //设置初始属性
        jf.setLocation(200, 200);
        jf.setSize(350, 200);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true);
    }

    public int GetPort() {
        int port = Integer.parseInt(jtf3.getText());
        return port;
    }

    public void actionPerformed(ActionEvent event)// 事件触发
    {

        String s1 = null;
        if (event.getSource() == jb2) {
            System.exit(0);
        }
        if (event.getSource() == jb1) {
            if (jtf1.getText() == "") {
                JOptionPane.showMessageDialog(null, "请输入用户名！");
            } else if (!jrb1.isSelected() && !jrb2.isSelected() && !jrb3.isSelected()) {
                JOptionPane.showMessageDialog(null, "请选择性别！");
            } else {
                jf.setVisible(false);
                jf.dispose();
                if (jrb1.isSelected()) {
                    s1 = "boy";
                } else if (jrb2.isSelected()) {
                    s1 = "girl";
                } else if (jrb3.isSelected()) {
                    s1 = "secret";
                }

                //接着去执行了G_menu的两个方法
                Menu gmu = new Menu();
                gmu.getMenu(jtf1.getText(), s1);
                try {
                    //把端口号传给Menu的Sock.
                    gmu.sock(GetPort());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }

    }

    public static void main(String[] args) {
        Login ld = new Login();
        ld.init();
    }

}
