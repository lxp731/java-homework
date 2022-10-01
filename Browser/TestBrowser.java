package Browser;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class TestBrowser extends JFrame implements ActionListener, HyperlinkListener {

    //文件操作
    JFileChooser jfc = new JFileChooser();

    //初始化网页面板
    JEditorPane jep = new JEditorPane();

    //文件菜单下的选项
    JMenuItem newbuild = new JMenuItem("新建窗口");
    JMenuItem openfile = new JMenuItem("打开本机");
    JMenuItem closewin = new JMenuItem("关闭当前");
    JMenuItem quitprog = new JMenuItem("退出程序");

    //帮助菜单下的选项
    JMenuItem about = new JMenuItem("关于");

    //工具栏的按钮
    JButton back = new JButton("后退");
    JButton goahd = new JButton("前进");
    JButton refresh = new JButton("刷新");
    JButton home = new JButton("主页");

    //最底下的状态栏
    JLabel state = new JLabel("", JLabel.RIGHT);

    //搜索框
    JTextField search = new JTextField("www.bing.com");

    //初始化一个URL
    URL url = new URL("https://www.bing.com");                    //http://www.bing.com

    //搜索框后的搜索按钮
    JButton okey = new JButton("搜索");

    //初始化浏览器窗口数量为0
    int browinum = 0;

    //最后一个窗口为false
    boolean lastwin = false;

    //控制前进与后退,创建一个历史列表
    List<String> history = new ArrayList<String>();
    int maxHistory = 50;
    int currentPage = -1;

    public TestBrowser() throws IOException {

        //初始化一个容器
        Container cp = this.getContentPane();

        //初始化菜单栏并添加到容器
        JMenuBar jmb = new JMenuBar();
        jmb.setBackground(new Color(249, 205, 173));
        cp.add(jmb, BorderLayout.NORTH);

        //创建“文件”菜单
        JMenu file = new JMenu("文件");

        //把“菜”添加进“文件”菜单
        file.add(newbuild);
        file.add(openfile);
        file.add(closewin);
        file.add(quitprog);

        //创建“帮助”菜单并把他的“菜”加到帮助菜单
        JMenu help = new JMenu("帮助");
        help.add(about);

        //添加菜单栏，并添加菜单到菜单栏
        this.setJMenuBar(jmb);
        jmb.add(file);
        jmb.add(help);

        //初始化一个工具栏
        JToolBar jtb = new JToolBar();
        cp.add(jtb, BorderLayout.NORTH);

        //对工具栏按钮的美化
        back.setBackground(new Color(137, 190, 178));
        goahd.setBackground(new Color(137, 190, 178));
        refresh.setBackground(new Color(137, 190, 178));
        home.setBackground(new Color(137, 190, 178));

        //把工具添加到工具栏
        jtb.add(back);
        jtb.add(goahd);
        jtb.add(refresh);
        jtb.add(home);

        //初始状态“返回”和“前进”不可用
        back.setEnabled(false);
        goahd.setEnabled(false);

        //快捷键（ALT+）
        file.setMnemonic('F');
        newbuild.setMnemonic('N');
        openfile.setMnemonic('O');
        closewin.setMnemonic('W');
        quitprog.setMnemonic('Q');

        //搜索框的美化
        search.setBackground(new Color(200, 200, 169));
        JLabel addr = new JLabel("        导航  ");
        okey.setBackground(new Color(38, 157, 128));

        //把搜索相关的组件添加进工具栏
        jtb.add(addr);
        jtb.add(search);
        jtb.add(okey);

        //设置网页面板不可编辑，并美化
        jep.setEditable(false);
        jep.setBackground(new Color(245, 255, 250));

        //设置网页面板和状态栏的位置
        cp.add(jep, BorderLayout.CENTER);
        cp.add(state, BorderLayout.SOUTH);

        //添加滚轮
        JScrollPane jsp = new JScrollPane(jep);
        this.add(jsp);
        jsp.setBounds(5, 5, 700, 285);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //改变图标
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("H:\\ico/google.png");
        this.setIconImage(img);

        //将当前打开的窗口加1
        browinum++;

        //所有的监听器都放在这里
        this.openfile.addActionListener(this);
        this.closewin.addActionListener(this);
        this.quitprog.addActionListener(this);
        this.newbuild.addActionListener(this);
        this.about.addActionListener(this);
        this.jep.addHyperlinkListener(this);
        this.okey.addActionListener(this);
        this.home.addActionListener(this);
        this.refresh.addActionListener(this);
        this.back.addActionListener(this);
        this.goahd.addActionListener(this);
        this.back.addActionListener(this);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWin();
            }
        });

        //设置窗口属性
        this.setSize(900, 500);
        this.setTitle("Not A Browser V0.1.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }


    //TODO 实现对文件内容的返回
    public String ReadFromFile() throws IOException {
        String filename = jfc.getSelectedFile().getPath();    //得到路径和文件名
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);            //谁来读文件
        int size = (int) file.length();        //file.length是long型的,需要强制转换
        char[] ch = new char[size];            //定义一个char的数组，长度为文件的长度
        int length = 0;
        while (fileReader.ready()) {
            length = length + fileReader.read(ch, 0, size - length);
        }
        fileReader.close();
        return new String(ch);
    }


    //TODO 实现文件内容的显示（显示在当前面板区域内）
    public void ReadFile() throws IOException {

        jfc.showOpenDialog(this);
        String filename = "empty";
        filename = jfc.getSelectedFile().getPath();    //得到路径和文件名
        File file = new File(filename);
        if (!file.exists())
            file.createNewFile();
        String s = new String(ReadFromFile());
        this.jep.setText(s);
    }


    //新建一个浏览器页面
    public void NewBrowser() throws IOException {
        TestBrowser tb = new TestBrowser();
        tb.setSize(this.getWidth(), this.getHeight());        // 新窗口大小和当前窗口一样大
        tb.setVisible(true);
    }


    //刷新页面
    public void ReloadPage() throws IOException {
        jep.setDocument(new javax.swing.text.html.HTMLDocument());
        String s = "https://" + search.getText();
        url = new URL(s);
        jep.setPage(url);
    }


    //存储URL
    public void saveURL(ActionEvent e) throws IOException {
        if (e.getSource() == okey) {
            history.add("https://" + search.getText());
            int count = history.size();
            if (count > maxHistory + 10) {
                history = history.subList(count - maxHistory, count); //只保留一段
                count = maxHistory;
            }
            // 将当前页面下标置为count-1,count准备迎接下一个
            currentPage = count - 1;
            // 如果当前页面不是第一个页面，则可以后退，允许点击后退按钮。
            if (currentPage > 0) {
                back.setEnabled(true);
            }
        }
    }


    //回退
    public void backPage() throws IOException {
        if (currentPage > 0) {                    //当前页面不是第一页
            String s = history.get(--currentPage);
            url = new URL(s);
            jep.setPage(url);
            back.setEnabled(true);                // 如果当前页面下标大于0，允许后退
        } else if (currentPage < history.size() - 1) {
            goahd.setEnabled(true);                // 如果当前页面下标不是最后，允许前进
        }
    }


    //前进
    public void forwardPage() throws IOException {
        if (currentPage < history.size() - 1) {            //小于数组长度才能前进
            String s = history.get(++currentPage);
            url = new URL(s);
            jep.setPage(url);
            goahd.setEnabled(true);                        // 如果当前页面下标不是最后，允许前进
        } else if (currentPage > 0) {
            back.setEnabled(true);                        // 如果当前页面下标大于0，允许后退
        }
    }


    //回到主页
    public void BackHome() throws IOException {
        url = new URL("https://www.bing.com");
        jep.setPage(url);
        search.setText("www.bing.com");
    }


    //关闭页面
    public void closeWin() {
        this.setVisible(false);                //设置页面为不可见
        this.dispose();                        //对窗口组件进行销毁
        synchronized (TestBrowser.class) {
            this.browinum--;
            if ((browinum == 0) && lastwin) {
                System.exit(0);
            }
        }
    }


    //退出程序,弹出对话框,请求确认,如果确认退出,则退出应用程序
    public void exitProg() {
        if ((JOptionPane.showConfirmDialog(this, "你确定退出Web浏览器？", "退出",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {
            System.exit(0);
        }
    }


    //设置浏览器是否在所有窗口都关闭时退出
    public void setLastWin(boolean b) {
        lastwin = b;
    }


    //获取页面
    public void GetPage() throws IOException {
        String s = "https://" + search.getText();
        url = new URL(s);
        jep.setPage(url);
//		return true;
    }


    //获取地址
    public void getAddr() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(search.getText());
        String s = inetAddress.toString();
        state.setText("正在访问：" + s + "  " + getIP());
//		search.setText(s);							//有改动
    }


    //获取IP
    public String getIP() throws UnknownHostException {
        String ip = InetAddress.getByName(search.getText()).getHostAddress();
        return ip;
    }


    //所有监听动作都在这里
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO 自动生成的方法存根
        if (e.getSource() == openfile)                //打开本地
            try {
                this.ReadFile();
            } catch (IOException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }
        else if (e.getSource() == quitprog)            //退出程序
            this.exitProg();
        else if (e.getSource() == newbuild)            //新建窗口
            try {
                this.NewBrowser();
            } catch (IOException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }
        else if (e.getSource() == about)                //产看关于
            JOptionPane.showMessageDialog(this, "Design By:XiaoPeng Liu\n" +
                    "Power By:Knight\n" +
                    "版权乌有 侵权无妨");
        else if (e.getSource() == okey) {            //确认访问
            try {
                this.GetPage();                        //获取网页
            } catch (IOException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }
            try {
                this.getAddr();                        //得到地址
            } catch (UnknownHostException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }
//			back.setEnabled(true);					//设置为可点击
        } else if (e.getSource() == home) {            //回到主页
            try {
                this.BackHome();
            } catch (IOException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }
        } else if (e.getSource() == refresh) {        //刷新页面
            try {
                this.ReloadPage();
            } catch (IOException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }
        } else if (e.getSource() == closewin) {        //关闭当前
            this.closeWin();
        } else if (e.getSource() == back) {            //上一个页面
            try {
                this.backPage();
            } catch (IOException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }
        } else if (e.getSource() == goahd) {            //下一个页面
            try {
                this.forwardPage();
            } catch (IOException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }
        }

    }


    //处理超链接的接口实现方法
    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
        // TODO 自动生成的方法存根
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            try {
                jep.setPage(e.getURL());
                String s = e.getURL().toString();
                String str = s.substring(8);
                search.setText(str);
            } catch (IOException ioe) {
                ioe.printStackTrace();
                String s = e.getURL().toString();
                String str = s.substring(7);
                search.setText(str);
            }
        }
    }


    //程序入口
    public static void main(String[] args) throws IOException {
        TestBrowser tb = new TestBrowser();
        tb.setLastWin(true);
        tb.setVisible(true);
    }

}
