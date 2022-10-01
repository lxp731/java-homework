package MyChatRoom;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;

class Menu extends JFrame implements ActionListener {
    JFrame jf = new JFrame("聊天室");

    //创建Socket类的对象
    public Socket soc;
    public PrintWriter pw;

    public JPanel jp1 = new JPanel();
    public JPanel jp2 = new JPanel();
    public JPanel jp3 = new JPanel();
    public JPanel jp4 = new JPanel();
    public JPanel jp5 = new JPanel();
    public JPanel jp6 = new JPanel();
    public JPanel jp7 = new JPanel();

    public static JTextArea jta1 = new JTextArea(12, 42);
    public static JTextArea jta2 = new JTextArea(12, 42);

    public JLabel jl1 = new JLabel("对");

    public static JComboBox jcomb = new JComboBox();

    public JCheckBox jcb = new JCheckBox("私聊");

    public JTextField jtf = new JTextField(36);

    public JButton jb1 = new JButton("发送>>");
    public JButton jb2 = new JButton("刷新");

    public static DefaultListModel listModel1;
    public static JList lst1;

    //用户的姓名和性别
    public String na;
    public String se;
    //定义消息的内容
    public String message;

    Login l;

    public void getMenu(String name, String sex)// 显示聊天界面
    {

        jcomb.addItem("所有人");

        this.na = name;
        this.se = sex;

        //设置显示消息区域为不可编辑
        jta1.setEditable(false);
        jta1.setBackground(new Color(0xC8D6CA));
        jta2.setEditable(false);
        jta2.setBackground(new Color(0xC8D6CA));

        //新建好友列表
        listModel1 = new DefaultListModel();
        lst1 = new JList(listModel1);
        //设置列表的选择模式
        lst1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //显示首选行数
        lst1.setVisibleRowCount(18);
        //设置一个固定值，用于列表中每个单元格的高度
        lst1.setFixedCellHeight(28);
        //设置一个固定值，用于列表中每个单元格的宽度。
        lst1.setFixedCellWidth(100);
        lst1.setBackground(new Color(0xCAC7D7));

        //向三个大区域添加滚动条
        JScrollPane jsp1 = new JScrollPane(jta1);
        JScrollPane jsp2 = new JScrollPane(jta2);
        JScrollPane jsp3 = new JScrollPane(lst1);

        //在鼠标区域边界，并设置标题
        jsp3.setBorder(new TitledBorder("好友列表"));
        jsp1.setBorder(new TitledBorder("群聊频道"));
        jsp2.setBorder(new TitledBorder("私聊频道"));

        //面板1添加两个聊天频道，2行1列的布局模式
        jp1.setLayout(new GridLayout(2, 1));
        jp1.add(jsp1);
        jp1.add(jsp2);

        //面板2，普通布局，”对“”所有人“”私聊“
        jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp2.add(jl1);
        jp2.add(jcomb);
        jp2.add(jcb);
        jp2.setBackground(new Color(0xE7F5DE));

        //面板3，消息框和”发送“按钮
        jp3.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jp3.add(jtf);
        jp3.add(jb1);
        jp3.setBackground(new Color(0xD9DCD8));

        //面板4把面板2和3，调整为2行1列的布局
        jp4.setLayout(new GridLayout(2, 1));
        jp4.add(jp2);
        jp4.add(jp3);

        //面板5把面板1和面板4调整为方向布局，分别位于面板5的南北两侧
        jp5.setLayout(new BorderLayout());
        jp5.add(jp1, BorderLayout.NORTH);
        jp5.add(jp4, BorderLayout.SOUTH);
        jp5.setBackground(new Color(0xD9DCD8));

        //面板6把”好友列表“和”刷新按钮“添加进来，并设置布局为方向布局
        jp6.setLayout(new BorderLayout());
        jp6.add(jsp3, BorderLayout.NORTH);
        jp6.add(jb2, BorderLayout.SOUTH);

        //面板7把面板5和6设置为普通布局
        jp7.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jp7.add(jp6);
        jp7.add(jp5);

        //面板7添加进框架
        jf.add(jp7);

        jf.setLocationRelativeTo(null);
        jf.setSize(700, 650);

        //聊天室不可以调整大小
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jf.setVisible(true);

        //监听器都在这里
        jb1.addActionListener(this);
        jb2.addActionListener(this);

        //设置文本区域的换行策略
        jta1.setLineWrap(true);
        jta2.setLineWrap(true);

        //鼠标滚轮的显示策略
        jsp1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jf.pack();
    }

    //处理用户的信息
    public void sock(int port) throws Exception {
        String user = na + "(" + se + ")";// 将用户的姓名和性别保存成字符串形式

        soc = new Socket(user, port);// 创建客户端对象

        pw = new PrintWriter(soc.socket.getOutputStream());// 创建输出流
        pw.println("1008611");// 发送好友列表标识
        pw.println(na + ":" + se);// 发送用户信息
        pw.flush();
        pw.println("10086");// 发送进入聊天室标识
        pw.println("【" + na + "】" + "进入聊天室");// 发送进入聊天室信息
        pw.flush();

    }

    public Menu() {// 设置窗口关闭事件，如果点击窗口右上角叉号关闭，执行下边程序

        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    pw = new PrintWriter(soc.socket.getOutputStream());
                    pw.println("456987");// 发送下线标识
                    pw.println(na + ":离开聊天室");// 发送下线信息
                    pw.flush();
                    jf.dispose();// 关闭窗口
                } catch (Exception ex) {
                }
            }
        });
    }

    public void actionPerformed(ActionEvent event)// 事件触发
    {
        try {
            pw = new PrintWriter(soc.socket.getOutputStream());
            if (event.getSource() == jb1)// 点击发送触发
            {
                //如果会话框里面有消息
                if (jtf.getText() != "") {
                    //如果私聊按钮被选中
                    if (jcb.isSelected()) {
                        //把下拉框里的内容传给name1
                        String name1 = (String) jcomb.getSelectedItem();
                        //na、se是用户的姓名、性别
                        message = "私聊-----" + na + "(" + se + ")" + "对" + name1 + "说：" + jtf.getText();
                        pw.println("841163574");// 发送私聊标识
                        pw.println(na + ":" + name1 + "1072416535" + message);// 发送私聊信息
                        pw.flush();
                    } else {
                        pw.println("10010");// 发送群组聊天标识
                        pw.println(na + "说：" + jtf.getText());// 发送聊天信息
                        pw.flush();
                    }
                }
            } else if (event.getSource() == jb2)// 点击刷新触发
            {
                pw = new PrintWriter(soc.socket.getOutputStream());
                pw.println("123654");// 发送刷新标识
                pw.flush();
            }
            jtf.setText("");// 清空输入栏信息
            jtf.requestFocus();// 输入焦点
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
