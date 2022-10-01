package Notebook;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class TestNote extends JFrame implements ActionListener,ItemListener{
	
	JMenuItem fileNew,fileOpen,fileSave,fileExit;				//文件子菜单
	JRadioButtonMenuItem optBlue,optYellow,optRed,optWhite,optdefault;		//选项子菜单
	JMenuItem fileRevoke,fileCut,fileCopy,filePaste,fileAll;	//编辑子菜单
	JMenuItem timestamp;										//辅助子菜单
	JMenuItem about;											//帮助子菜单
	
	JCheckBox cb = new JCheckBox("自动换行");
	
	JTextArea jta = new JTextArea();							//文本块
	JScrollPane jsp = new JScrollPane(jta);						//在文本框上添加滚动条
	JFileChooser jfc = new JFileChooser();						//文件
	
	public  TestNote() {
		
		this.setLayout(null);									//设置布局
		
		this.add(jsp);								//文本块竖直添加滚动条
		jsp.setBounds(5, 5, 700, 285);				//滚动条属性
		jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);			//总是显示
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	//需要时显示
		
		JMenuBar jmb = new JMenuBar();				//构造菜单栏
		this.setJMenuBar(jmb);						//把菜单栏加到框架上，一个框架只能有一个菜单栏
		
		JMenu file = new JMenu("文件");
		file.setFont(new Font("TimesRoman",Font.BOLD,15));
		
		JMenu format = new JMenu("格式");
		format.setFont(new Font("TimesRoman",Font.BOLD,15));
		
		JMenu edie = new JMenu("编辑");
		edie.setFont(new Font("TimesRoman",Font.BOLD,15));
		
		JMenu aider = new JMenu("辅助");
		aider.setFont(new Font("TimesRoman",Font.BOLD,15));
		
		JMenu help = new JMenu("帮助");
		help.setFont(new Font("TimesRoman",Font.BOLD,15));
		
//		JCheckBox cb = new JCheckBox("自动换行");
		JMenuItem color = new JMenu("颜色(C)");
		

		
		//菜单栏里的菜单内容
		jmb.add(file);
		jmb.add(format);
		jmb.add(edie);
		jmb.add(aider);
		jmb.add(help);
		
		//格式菜单里的内容
		format.add(cb);
		format.addSeparator();
		format.add(color);
		
		//文件菜单里的内容
		file.add(fileNew = new JMenuItem("新建"));
		fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		file.addSeparator();
		file.add(fileOpen = new JMenuItem("打开"));
		fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		file.addSeparator();
		file.add(fileSave = new JMenuItem("保存"));
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		file.addSeparator();
		file.add(fileExit = new JMenuItem("退出"));
		fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		//颜色子菜单下的内容
		color.add(optBlue = new JRadioButtonMenuItem("渣渣灰(B)"));
		color.add(optYellow = new JRadioButtonMenuItem("玫瑰金(Y)"));
		color.add(optRed = new JRadioButtonMenuItem("螺狮粉(R)"));
		color.add(optWhite = new JRadioButtonMenuItem("默认白(W)"));
		color.add(optdefault = new JRadioButtonMenuItem("初心白(D)"));
		ButtonGroup btg = new ButtonGroup();
		btg.add(optBlue);
		btg.add(optYellow);
		btg.add(optRed);
		btg.add(optWhite);
		btg.add(optdefault);
		
		//编辑菜单下的内容
		edie.add(fileRevoke = new JMenuItem("撤销(Z)"));
		fileRevoke.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		edie.addSeparator();
		
		edie.add(fileCut = new JMenuItem("剪切(X)"));
		fileCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		edie.addSeparator();
		
		edie.add(fileCopy = new JMenuItem("复制(C)"));
		fileCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		edie.addSeparator();
		
		edie.add(filePaste = new JMenuItem("粘贴(V)"));
		filePaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		edie.addSeparator();
		
		edie.add(fileAll = new JMenuItem("全选(A)"));
		fileAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		//辅助菜单下的内容
		aider.add(timestamp = new JMenuItem("添加时间戳"));
		timestamp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		//帮助菜单下的内容
		help.add(about = new JMenuItem("关于"));
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		//"文件"窗口菜单的监听器
		fileNew.addActionListener(this);
		fileOpen.addActionListener(this);
		fileSave.addActionListener(this);
		fileExit.addActionListener(this);
		
		//"格式"窗口菜单的监听器
		cb.addActionListener(this);
		optBlue.addItemListener(this);
		optYellow.addItemListener(this);
		optRed.addItemListener(this);
		optWhite.addItemListener(this);
		optdefault.addItemListener(this);
		
		//"编辑"窗口菜单的监听器
		fileRevoke.addActionListener(this);
		fileCut.addActionListener(this);
		fileCopy.addActionListener(this);
		filePaste.addActionListener(this);
		fileAll.addActionListener(this);
		
		//"辅助"窗口菜单的监听器
		timestamp.addActionListener(this);
		
		//"关于"窗口菜单的监听器
		about.addActionListener(this);
		
		//初始窗口各种属性的设置
		this.jta.setBackground(new Color(245, 255, 250));                  //设置初始窗口颜色	
		this.jta.setSelectedTextColor(new Color(238,121,66));              //设置文本被选择后的颜色（ 可以多去api查方法 类     ）
		this.jta.setSelectionColor(new Color(238,238,209));                //设置选择色
        this.jta.setFont(new Font("宋体",Font.BOLD,20));
		this.setTitle("我不是记事本 V1.3.0");								   //设置窗口标题
		this.setSize(720,360);											   //设置初始窗口大小
		this.setLocationRelativeTo(null);								   //设置初始窗口居中显示
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			   //设置默认关闭方式
	}
	

	
	//TODO 实现改变记事本的背景颜色
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO 自动生成的方法存根
		if(optBlue.isSelected())
			jta.setBackground(new Color(151,173,172));
		if(optYellow.isSelected())
			jta.setBackground(new Color(214,213,183));
		if(optRed.isSelected())
			jta.setBackground(new Color(244,96,108));
		if(optWhite.isSelected())
			jta.setBackground(Color.WHITE);
		if(optdefault.isSelected())
			jta.setBackground(new Color(245, 255, 250));
	}
	
	
	
	//TODO 实现文件的保存
	public void writeFile() throws IOException {
		jfc.showSaveDialog(this);
		String filename = jfc.getSelectedFile().getPath();
		File file = new File(filename);
		FileWriter fw = new FileWriter(file);
		String s = jta.getText();
		fw.write(s);
		fw.close();
	}

	
	
	//TODO 实现对文件内容的返回
	public String readFromFile() throws IOException {
		String filename=jfc.getSelectedFile().getPath();	//得到路径和文件名
		File file = new File(filename);
		FileReader fileReader = new FileReader(file);			//谁来读文件
		int size = (int)file.length();		//file.length是long型的,需要强制转换
		char[] ch = new char[size];			//定义一个char的数组，长度为文件的长度
		int length = 0;						
		while (fileReader.ready()) {
			length = length + fileReader.read(ch,0,size-length);
		}
		fileReader.close();
		return new String(ch);
	}
	
	
	
	//TODO 实现文件内容的显示（显示在文本区域内）
	public void readFile() throws IOException {
		
		jfc.showOpenDialog(this);
		String filename = "empty";
		filename=jfc.getSelectedFile().getPath();	//得到路径和文件名
		File file = new File(filename);
		if(!file.exists())
			file.createNewFile();
		String s = new String(readFromFile());
		this.jta.setText(s);
	}
	
	
	
	//TODO 实现新建文件的功能
	public void newFile() {
		jta.setText("");
	}
	
	
	
	//TODO实现添加时间戳的功能
    public void addTime() {
        Date now = new Date(); //获取当前时间
        SimpleDateFormat timenow = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");//格式化当前日期时间 还要换行啊
        String time = timenow.format(now.getTime());
        String before=jta.getText();
        jta.setText(before+"\n\n\n"+"                                     "+time);
    }
    
    
        //TODO实现复制的功能
    public void CopyString() {
        // 获取系统的剪切板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // 获取选中的内容
        String copyText = jta.getSelectedText();

        System.out.println("你复制的内容是：" + copyText);
        // 封装文本内容
        Transferable trans = new StringSelection(copyText);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
    }

    
    
    //实现粘贴的功能
    public void PasteString()  {
    	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = clipboard.getContents(this);
		String s = "";
		try {
			s = (String) transferable.getTransferData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		this.jta.replaceRange(s, jta.getSelectionStart(), jta.getSelectionEnd());
	}

    
    
    //TODO 实现全选的功能
    public void SelectAllString() {
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
    	Clipboard clipboard = toolkit.getSystemClipboard();
    	String s = jta.getText();
		StringSelection stringSelection = new StringSelection(s);
		clipboard.setContents(stringSelection, null);
//    	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//    	String s = jta.getText();
//    	Transferable trans = clipboard.getContents(s);
//    	clipboard.setContents(trans, null);
	}
    
    
    
    //TODO 实现自动换行的功能
    public void WrapLine() {
		jta.setLineWrap(true);						//设置自动换行为真
	}
    
    
    
    //TODO 实现记事本的剪切功能
    public void CutString() {
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
    	Clipboard clipboard = toolkit.getSystemClipboard();
    	String s = jta.getSelectedText();
    	StringSelection stringSelection = new StringSelection(s);
    	clipboard.setContents(stringSelection, null);
    	jta.replaceRange("", jta.getSelectionStart(), jta.getSelectionEnd());
	}
    
    
    
	//TODO 监听动作都在这里 监听动作都在这里 监听动作都在这里
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==fileNew)			//"新建"动作的监听
			this.newFile();
		else if(e.getSource()==fileOpen)	//"打开"动作的监听
			try {
				this.readFile();
			} catch (IOException e2) {
				// TODO 自动生成的 catch 块
				e2.printStackTrace();
			}
		else if(e.getSource()==fileSave)	//"保存"动作的监听
			try {
				this.writeFile();
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		else if(e.getSource()==fileExit)	//"退出"动作的监听
			System.exit(0);
		else if(e.getSource()==fileCut)
//			JOptionPane.showMessageDialog(this, "剪切成功");
			this.CutString();
		else if(e.getSource()==fileCopy)
			this.CopyString();
//			JOptionPane.showMessageDialog(this, "复制成功");
		else if(e.getSource()==filePaste)
			this.PasteString();
//			JOptionPane.showMessageDialog(this, "粘贴成功");
		else if(e.getSource()==fileRevoke)
			JOptionPane.showMessageDialog(this, "功能持续完善中 敬请期待");
		else if (e.getSource()==fileAll) 
			this.SelectAllString();
		else if(e.getSource()==about)
			JOptionPane.showMessageDialog(this, "Design By:XiaoPeng Liu\n"+
												"Power By:Knight\n"+
												"版权乌有 侵权无妨");
		else if (e.getSource()==timestamp) 
			this.addTime();
		else if (cb.isSelected()) 
			jta.setLineWrap(true);
	}
	
	
	
	//TODO main函数位置，程序的入口
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		TestNote frame = new TestNote();
		frame.setVisible(true);
	}
}




