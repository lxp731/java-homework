package Notebook;

import java.awt.*;              //这里主要是使用基础图形和 eventqueue
import java.awt.event.*;
import java.util.*;
import java.io.*;           //文件流。（这里使用字符流，以及异常）
import javax.swing.*;    //非基础类 绘图和交互等。 swing 比awt 强大很多倍。
import javax.swing.filechooser.*;             // 文件选择器
import javax.swing.filechooser.FileFilter;  //里面有一个可以判断是否应该接受一个文件
import java.text.SimpleDateFormat;           //加入 我们的 日期服务
 
public class openone {
    public static  void main(String[] args){
        EventQueue.invokeLater(new Runnable() { //与线程相关。事件列表
            @Override
            public void run() {
                Frame frame = new Frame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //基础的一些构造 frame
                frame.setVisible(false);
            }
        });
    }
}
// 可以有多个类。
class Frame extends JFrame {
    public Frame() {
        JMenuBar menus = new JMenuBar();                            //    关于菜单 ：jmenuitem 加到➡️ jmenu ➡️ jmenubar  一个比较大的可以放菜单的区域
        //也有例外
        JMenu Fileone = new JMenu("文件");                            // 放入菜单的选项
        Fileone.setFont(new Font("TimesRoman",Font.BOLD,15));
        JMenu Operation = new JMenu("文本");
        Operation.setFont(new Font("TimesRoman",Font.BOLD,15));         //font  设置字体。推荐一个好看的字体：timesroman。更好的视觉体验
        JMenu Windows = new JMenu("视窗");
        Windows.setFont(new Font("TimesRoman",Font.BOLD,15));
        JMenu Serve = new JMenu("辅助");
        Serve.setFont(new Font("TimesRoman",Font.BOLD,15));
        JMenu Help = new JMenu("帮助");
        Help.setFont(new Font("TimesRoman",Font.BOLD,15));
 
 
        menus.add(Fileone);                                 //大的菜单区域里加入
        menus.add(Operation);
        menus.add(Windows);
        menus.add(Serve);
        menus.add(Help);
 
 
 
        // ***************************************快捷键
        JMenuItem open = new JMenuItem("打开");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));   //  -- mac设置快捷键的方法   ：Toolkit （"工具包"） 超级强大～ 可以实现 ctrl 到command 的转换
        // 其实 在GUI中 可以用他来 获取屏幕的尺寸
        JMenuItem saveother = new JMenuItem("另存为");
        saveother.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,      //vk-n n就是快捷键
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        JMenuItem save = new JMenuItem("保存");
        save .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        JMenuItem Rename = new JMenuItem("重命名");
        Rename.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        JMenuItem more = new JMenuItem("更多"); //还想加入属性 比如字节数等等。
 
 
        JMenuItem Delete = new JMenuItem("清空当前");
        Delete .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        JMenu TEXT = new JMenu("文本权限");
        JMenu works = new JMenu("文本信息");
 
        ButtonGroup groupone = new ButtonGroup();
        JRadioButtonMenuItem readonly = new JRadioButtonMenuItem("只读操作");       //radiobutton    在这个组里只可以选一个
        JRadioButtonMenuItem operation = new JRadioButtonMenuItem("读写操作");          //记得放到组里 （注意只是逻辑上～ 作为一组）
        operation.setSelected(true);                                                //默认选择项
        groupone.add(operation);
        groupone.add(readonly);
        JMenuItem NUMBER = new JMenuItem("查看字数");
        JMenuItem LINES = new JMenuItem("查看行数");
 
 
 
 
        JMenuItem color = new JMenuItem("显示背景");                        // 主要是显示操作
        color .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
 
        JMenuItem leftone  = new JMenuItem("左半屏");
        leftone .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        JMenuItem  rightone  = new JMenuItem("右半屏");
        rightone .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        JMenuItem max  = new JMenuItem("最大化");
        max .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        JMenuItem min   = new JMenuItem("最小化");
        min .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        JMenuItem BACK = new JMenuItem("最初界面");
 
 
 
        JMenuItem dateadd = new JMenuItem("添加日期");                          //特殊服务
 
 
        JMenuItem More = new JMenuItem("关于我们");                         //   关于我们
 
 
        Fileone.add(open);
        Fileone.addSeparator();
        Fileone.add(saveother);
        Fileone.addSeparator();
        Fileone.add(save);
        Fileone.addSeparator();
        Fileone.add(Rename);
        Fileone.addSeparator();
        Fileone.add(more);
 
        Operation.add(Delete);
        Operation.add(TEXT);
        Operation.add(works );
        works.add(LINES );
        works.add(NUMBER);
        TEXT.add(readonly);                                 // 在面板的加入的时候是加入具体的 选项
        TEXT.add(operation);
 
 
 
        Windows.add(color);
        Windows.add(leftone);
        Windows.add(rightone);
        Windows.add(max);
        Windows.add(min);
        Windows.add(BACK);
 
        Serve.add(dateadd);
 
        Help.add(More);
 
 
        JPanel panel1 = new JPanel();                                       //  jpanel  ➡️ jframe
        textArea1 = new JTextArea(null, 8, 40);                             //textarea实现多行文本  textfield  单行
        textArea1.setLineWrap(true);                                        //准许换行
        textArea1.setSelectedTextColor(new Color(238,121,66));              //设置文本被选择后的颜色（ 可以多去api查方法 类     ）
        textArea1.setSelectionColor(new Color(238,238,209));                //设置选择色
        textArea1.setText("");												//设置初始文字
        textArea1.setBackground(new Color(245, 255, 250));					//设置背景色
        textArea1.setFont(new Font("TimesRoman",Font.BOLD,20));
 
//      JScrollPane a = new JScrollPane(textArea1);                         //这个方法可以显示滚轮但是咱们先不用  也可以加上
        JFrame frame = new JFrame("MSCnotrpad 1.01");
        Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
        int WIDTH = (int) SCREEN.getWidth();
        int HEIGHT = (int) SCREEN.getHeight();
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(WIDTH / 4, HEIGHT / 4);
        frame.setSize(600, 400);
        frame.setJMenuBar(menus);                       //向frame加入菜单栏
        frame.getContentPane().add(panel1);
        frame.getContentPane().add(textArea1, BorderLayout.CENTER);
        frame.setResizable(true); //允许用户自己拖拉frame
        frame.setVisible(true);
 
 
 
        //****************************************右键监听  *****************************************
//        JPopupMenu popupone = new JPopupMenu();
//        JMenuItem pop1=  new JMenuItem("关闭窗口");
//        JMenuItem pop2 = new JMenuItem("最小化窗口");
//        JMenuItem pop3 = new JMenuItem("最大化窗口");
//        JMenuItem CLOSER = new JMenuItem("关闭菜单栏");
//        popupone.add(pop1);
//        popupone.add(pop2);
//        popupone.add(pop3);
//        popupone.add(CLOSER);
//        JPanel tanchu = new JPanel();
//        popupone.show(tanchu,WIDTH/4,HEIGHT/4);
//
//        textArea1.addMouseListener(new MouseAdapter() {
//
//        });
//        textArea1.setComponentPopupMenu(popupone);
//        menus.setComponentPopupMenu(popupone);
 
//**********************************下面给按钮安装监听器～*****************************************
// *********************************************************************************************
// *********************************************************************************************
//**********************************文件对话框 打开部分 设置它的文件对话框***************************
        open.addActionListener(new FileOpenListener());
        chooser = new JFileChooser();                                                   //创建一个事件监听器
        FileNameExtensionFilter filter = new FileNameExtensionFilter("文本文件", "txt");//设置filechooser。文件过滤器只选择txt类
        chooser.setFileFilter(filter);
        chooser.setFileView(new FileIconView(filter, null));//小图标使用默认图标
 
 
//*******************************       另存为 部分  设置他的文件对话框    **************************************************
        saveother.addActionListener(new SaveAnotherListener());
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter("文本文件", "txt");//设置filechooser。文件过滤器只选择txt类
        chooser.setFileFilter(filter2);
        chooser.setFileView(new FileIconView(filter2, null));
//**********************************       单纯 保存部分              ***************************************
        save.addActionListener(new SaveListener());
        //**********************************       重命名              ***************************************
        Rename.addActionListener(new Renameone()); FileNameExtensionFilter filter3 = new FileNameExtensionFilter("文本文件", "txt");//设置filechooser。文件过滤器只选择txt类
        chooser.setFileFilter(filter3);
        chooser.setFileView(new FileIconView(filter3, null));
 
 
        //**********************************  文本   清空              ***************************************
        Delete.addActionListener(new Clearit());
//*********************************************  视窗  改变颜色 *********************************************************
        color.addActionListener(new ChangeColor());
 
 
//********************************************   视窗 改变窗口************************************************************
        min.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e){
                frame.setExtendedState(JFrame.ICONIFIED);
            }
        });
        max.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
        //不知道为什么实现不了？哎伤心 ，自己写一个事件监听来完成 实现另一个功能
 
//        widthone.addActionListener(new ActionListener(){
//            @Override public void actionPerformed(ActionEvent e){
//                frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
//            }
//        });
//        heightone.addActionListener(new ActionListener(){
//            @Override public void actionPerformed(ActionEvent e){
//                frame.setExtendedState(JFrame.MAXIMIZED_VERT);
//            }
//        });
 
        leftone.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setLocation( 0,0);
                frame.setSize(WIDTH/2,HEIGHT);
            }
        });
        rightone.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setLocation( WIDTH/2, 0);
                frame.setSize(WIDTH/2,HEIGHT);
            }
        });
        BACK.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                frame.setLocation(WIDTH / 4, HEIGHT / 4);
                frame.setSize(600, 400);
            }
        });
//****************************************    文本  只读 ******************************************************************
        readonly.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                saveother.setEnabled(false);
                save.setEnabled(false);
                Delete.setEnabled(false);
                textArea1.setLineWrap(true);
            }
        });
//*****************************************  文本  读写 *******************************************************************
 
        operation.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                saveother.setEnabled(true);
                save.setEnabled(true);
                Delete.setEnabled(true);
            }
        });
        //*****************************************  文本 行数*******************************************************************
        LINES.addActionListener(new showLine());
        //********************************************  辅助   ***************************************************************
        dateadd.addActionListener(new addtimeone());
//****************************************       单纯    帮助        ***************************************
        More.addActionListener(new HelpListener());
 
    }
 
    //*************************************     设置 单击时的响应操纵   *************************************
// *********************************************************************************************
// ***********************************************打开文件**************************************
    String name;  //写在内部类外面 ，储存被选择文件的路径。
 
    private class FileOpenListener implements ActionListener {               //时间响应起器（实现接口）
        public void actionPerformed(ActionEvent event) {
            chooser.setCurrentDirectory(new File("/Users/frankdura/Desktop"));//默认的文件打开路径.否则为自己的文件夹
            int result = chooser.showOpenDialog(Frame.this); //显示文件选择对话框
            if (result == JFileChooser.APPROVE_OPTION) {
                name = chooser.getSelectedFile().getPath();
                File dataFile = new File(name);
                System.out.print(name);
                try {
                    FileReader fr = new FileReader(dataFile);               //字符流   fileinputstream  字节流 （黑人问号）
                    BufferedReader br = new BufferedReader(fr);             //处理流    （缓冲一下）
                    String content;
 
                    while ((content = br.readLine()) != null) {
                        textArea1.append(content + "\n");
 
                    }
                    br.close();
                    fr.close();
                } catch (FileNotFoundException e) {
                    System.out.println("找不到文件！原因如下：" + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
 
    // *********************************************************************************************
// ***********************************************保存********************************************
    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
 
            FileWriter fr;
            BufferedWriter br;
            try {
                if(name == null){
                    JFrame dialog2 = new JFrame();
                    cannotsave cannot = new cannotsave();
 
                }
                fr = new FileWriter(name);
                br = new BufferedWriter(fr);
                br.write(textArea1.getText());
                br.flush();
                br.close();
                fr.close();
            } catch (FileNotFoundException e) {
                System.out.println("找不到文件！原因如下：");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    // *********************************************************************************************
// ***********************************************另存为********************************************
    private class SaveAnotherListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            chooser.setCurrentDirectory(new File("/Users/frankdura/Desktop"));//默认的文件打开路径.否则为自己的文件夹
            int result = chooser.showSaveDialog(Frame.this); //显示文件选择对话框
            if (result == JFileChooser.APPROVE_OPTION) {
                String name = chooser.getSelectedFile().getPath();
                File dataFile = new File(name);
                FileWriter fr;
                BufferedWriter br;
                try {
                    fr = new FileWriter(name);
                    br = new BufferedWriter(fr);
                    br.write(textArea1.getText());
                    br.flush();
                    br.close();
                    fr.close();
                } catch (FileNotFoundException e) {
                    System.out.println("对不起，保存失败。原因如下：" + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //********************************************************重命名～*********************************
    private class Renameone implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            chooser.setCurrentDirectory(new File("/Users/frankdura/Desktop"));//默认的文件打开路径.否则为自己的文件夹
            int result = chooser.showOpenDialog(Frame.this); //显示文件选择对话框
            if (result == JFileChooser.APPROVE_OPTION) {
                String name = chooser.getSelectedFile().getPath();
                File dataFile = chooser.getSelectedFile();
                String nameone = "新的名字.txt";
                dataFile.renameTo(new File(nameone));
            }
        }
    }
    //*************************************  文本  清空 *************************************************
    private class Clearit implements  ActionListener{
        public void  actionPerformed(ActionEvent event){
 
            textArea1.setText(null);
        }
    }
    //*************************************  文本 行数 *************************************************
    private class showLine implements  ActionListener{
        public void actionPerformed(ActionEvent event){
            JFrame LINE = new JFrame("行数");
            line a = new line();
        }
    }
 
    //*************************************   视窗 色彩  **************************************************
    private  class ChangeColor implements  ActionListener {
        public void actionPerformed(ActionEvent event){
            Color defaultColor = getBackground();//获取背景色
            Color selected = JColorChooser.showDialog(Frame.this,"设置显示背景色",defaultColor);           //jcolorchooser 很好用 。
            if(selected != null)
            {
                textArea1.setBackground(selected);
            }
        }
    }
    //***********************************   辅助当前时间 ********************************************
    private  class addtimeone implements  ActionListener {
 
 
 
 
 
        public void actionPerformed(ActionEvent event) {
            Date now = new Date(); //获取当前时间
            SimpleDateFormat timenow = new SimpleDateFormat("yyyy年  MM月  dd日 \n");//格式化当前日期时间 还要换行啊
            String shijian = timenow.format(now.getTime());
            String before;
            before = textArea1.getText();
 
            textArea1.setText(shijian+"\n"+before);
 
        }
    }
    // **********************************   帮助栏关于我们 ********************************************
    private class HelpListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JFrame dialog1 = new JFrame();
            help help1 = new help();
        }
    }
 
    //*********************************************************************主要响应器end*****************************************************
    //*********************************************************************主要响应器end*****************************************************
    //*********************************************************************主要响应器end*****************************************************
 
 
 
 
    //*******************************************************************行高 数插件*****************************************************
    private class line extends JDialog {
        public line() {
            super();
            JPanel panel2 = new JPanel();
            int a = textArea1.getLineCount();
 
            String b = "当前行高数："+a;
            JLabel label2 = new JLabel(b, JLabel.CENTER);
            Font font = new Font(Font.DIALOG, Font.PLAIN, 20);                      //设置字体！
            add(label2,BorderLayout.NORTH);
            Frame.this.setVisible(true);
            JButton haode = new JButton("确定");
 
            panel2.add(haode);
            haode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });
            Dimension SCREEN2 = Toolkit.getDefaultToolkit().getScreenSize();
            int WIDTH2 = (int) SCREEN2.getWidth();
            int HEIGHT2 = (int) SCREEN2.getHeight();
            setLocation(WIDTH2 / 3, HEIGHT2 / 3);
            setTitle("关于我们");
            b = null;
 
            add(panel2,BorderLayout.SOUTH);
            setVisible(true);
            setSize(290,170);//这是对一个类进行操作
 
        }
    }
 
    //*************************************** 辅助帮助*************************************
 
    private class help extends JDialog{
        public help (){
            super();
 
            JPanel panel2 = new JPanel();
 
            JLabel label1 = new JLabel("version:1.01 MSCnotepad",JLabel.CENTER); //JLabel.CENTER可以实现将label文字放在居中位置
            JLabel label2 = new JLabel("如有任何问题请您咨询大豆豆",JLabel.CENTER);
            Font font = new Font(Font.DIALOG, Font.PLAIN, 20);                      //设置字体！
            label1.setFont(font);                                                   //设置字体！
 
 
            add(label1,BorderLayout.NORTH);
            add(label2);
 
            JButton haode = new JButton("确定");
            panel2.add(haode);
 
 
 
            haode.addActionListener(new  ActionListener(){
                public void actionPerformed(ActionEvent event) {
                    {
                        setVisible(false);
                    }
                }
            });
 
            Dimension SCREEN2 = Toolkit.getDefaultToolkit().getScreenSize();
            int WIDTH2 = (int) SCREEN2.getWidth();
            int HEIGHT2 = (int) SCREEN2.getHeight();
            setLocation(WIDTH2 / 3, HEIGHT2 / 3);
            setTitle("关于我们");
 
            add(panel2,BorderLayout.SOUTH);
            setSize(290,170);//这是对一个类进行操作
            setVisible(true);
 
 
 
        }
//**************************************   保存失败插件****************************************************
    }
    private class cannotsave extends JDialog{
        public cannotsave (){
            super();
 
            JPanel panel2 = new JPanel();
 
            JLabel label1 = new JLabel("您还未选择路径",JLabel.CENTER); //JLabel.CENTER可以实现将label文字放在居中位置
            JLabel label2 = new JLabel("请您设置另存为",JLabel.CENTER);
            Font font = new Font(Font.DIALOG, Font.PLAIN, 20);                      //设置字体！
            label1.setFont(font);                                                   //设置字体！
 
 
            add(label1,BorderLayout.NORTH);
            add(label2);
 
            JButton saveas = new JButton("另存为");
            JButton okokok = new JButton("取消");
            panel2.add(saveas);
            panel2.add(okokok);
 
            saveas.addActionListener(new  ActionListener(){
                public void actionPerformed(ActionEvent event) {
                    {
                        setVisible(false);    // 使用外部类
                        saveas.addActionListener(new SaveAnotherListener());
                        FileNameExtensionFilter filter2 = new FileNameExtensionFilter("文本文件", "txt");//设置filechooser。文件过滤器只选择txt类
                        chooser.setFileFilter(filter2);
                        chooser.setFileView(new FileIconView(filter2, null));
                        chooser.setCurrentDirectory(new File("/Users/frankdura/Desktop"));//默认的文件打开路径.否则为自己的文件夹
                        int result = chooser.showSaveDialog(Frame.this); //显示文件选择对话框
                        if (result == JFileChooser.APPROVE_OPTION) {
                            String name = chooser.getSelectedFile().getPath();
                            File dataFile = new File(name);
                            FileWriter fr;
                            BufferedWriter br;
                            try {
                                fr = new FileWriter(name);
                                br = new BufferedWriter(fr);
                                br.write(textArea1.getText());
                                br.flush();
                                br.close();
                                fr.close();
                            } catch (FileNotFoundException e) {
                                System.out.println("对不起，保存失败。原因如下：" + e.getMessage());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
 
 
                    }
                }
            });
            okokok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });
 
 
 
            Dimension SCREEN2 = Toolkit.getDefaultToolkit().getScreenSize();                       //toolkit
            int WIDTH2 = (int) SCREEN2.getWidth();
            int HEIGHT2 = (int) SCREEN2.getHeight();
            setLocation(WIDTH2 / 3, HEIGHT2 / 3);
            setTitle("关于我们");
 
            add(panel2,BorderLayout.SOUTH);
            setSize(290,170);//这是对一个类进行操作
            setVisible(true);
 
 
 
        }
 
    }
    //******************************
 
 
 
    //*************************************************************************************
    private class FileIconView extends FileView {
        public FileIconView(FileFilter aFilter, Icon anIcon) {
            filter = aFilter;
            icon = anIcon;
        }
 
        public Icon getIcon(File f) {
            if (!f.isDirectory() && filter.accept(f)) {
                return icon;
            } else {
                return null;
            }
        }
 
        private FileFilter filter,filer2,filter3;
        private Icon icon,icon2;
        //*************************************************************************************
 
    }
    JTextArea textArea1;
    private  JFileChooser chooser;
}
 
 
 