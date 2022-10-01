package MyChatRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerPage extends JFrame implements ActionListener {

    JFrame jf;
    JTextField jtf2;

    JButton jb1;
    JButton jb2;

    public ServerPage() {
        jf = new JFrame("登录服务器");

        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        jp1.setBackground(new Color(0xC8D6CA));
        jp2.setBackground(new Color(0xC8D6CA));

        jb1 = new JButton("运行");
        jb2 = new JButton("关闭");

        JToolBar jtb = new JToolBar();
        jtb.setBackground(new Color(171, 166, 191));

        JLabel jl1 = new JLabel("地址");
        JTextField jtf1 = new JTextField("127.0.0.1", 23);
        jtf1.setBackground(new Color(171, 166, 191));
        JLabel jl2 = new JLabel("端口");
        jtf2 = new JTextField("6666", 23);
        jtf2.setBackground(new Color(171, 166, 191));

        ImageIcon icon = new ImageIcon("D:\\IDEA\\work\\TinyTerm\\src\\MyChatRoom\\back.png");
        JLabel jl3 = new JLabel(icon);

        jtb.add(jl1);
        jtb.add(jtf1);
        jtb.add(jl2);
        jtb.add(jtf2);

        jp1.add(jtb);

        jp2.add(jb1);
        jp2.add(jb2);

        jf.add(jp1, BorderLayout.NORTH);
        jf.add(jl3, BorderLayout.CENTER);
        jf.add(jp2, BorderLayout.SOUTH);
        jf.setVisible(true);
        jf.setResizable(false);

        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        jf.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.jb1.addActionListener(this);
        this.jb2.addActionListener(this);
    }

    //得到用户的端口号
    public int GetPort() {
        int port = Integer.parseInt(jtf2.getText());
        return port;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            jf.setVisible(false);
            jf.dispose();
            new Thread(() -> new Server().getServer(GetPort())).start();
//            new Server().getServer(GetPort());

        } else if (e.getSource() == jb2) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ServerPage();
    }
}
