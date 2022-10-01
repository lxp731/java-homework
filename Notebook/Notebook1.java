package Notebook;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Notebook1 extends JFrame implements ActionListener{
	
	//创建一个菜单栏
	JMenuBar jMenuBar = new JMenuBar();
	
	JMenu file = new JMenu("文件");
	JMenu edit = new JMenu("编辑");
	
	JMenuItem fileOpen = new JMenuItem("打开");
	JMenuItem fileSave = new JMenuItem("保存");
	JMenuItem exit = new JMenuItem("退出");
	JMenuItem fileCopy = new JMenuItem("复制");
	JMenuItem filePaste = new JMenuItem("粘贴"); 
	JMenuItem fileAll = new JMenuItem("全选");
	JMenuItem fileCut = new JMenuItem("剪切");
	
	JTextArea jTextArea = new JTextArea();
	
	JFileChooser jfc = new JFileChooser();
	
	public Notebook1() {
		this.setJMenuBar(jMenuBar);
		this.add(jTextArea);
		this.jMenuBar.add(file);
		this.jMenuBar.add(edit);
		file.add(fileOpen);
		file.add(fileSave);
		file.add(exit);
		
		edit.add(fileCopy);
		edit.add(filePaste);
		edit.add(fileCut);
		edit.add(fileAll);
		
		fileOpen.addActionListener(this);
		fileSave.addActionListener(this);
		exit.addActionListener(this);
		fileCut.addActionListener(this);
		fileCopy.addActionListener(this);
		filePaste.addActionListener(this);
		fileAll.addActionListener(this);
		
		this.setSize(720,360);
		this.setTitle("MyNotebook");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		Notebook1 notebook1 = new Notebook1();
		notebook1.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==fileSave)
			try {
				this.writeFile();
			} catch (IOException e2) {
				// TODO 自动生成的 catch 块
				e2.printStackTrace();
			}
		else if (e.getSource()==fileOpen)
			try {
				this.readFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		else if (e.getSource()==exit) {
			System.exit(0);
		}
		else if (e.getSource()==fileCopy) {
			this.CopyString();
		}
		else if (e.getSource()==filePaste) {
			this.PasteString();
		}
		else if (e.getSource()==fileAll) {
			this.SelectAllString();
		}
		else if (e.getSource()==fileCut) {
			this.CutString();
		}
	}
	
	
	//打开文件
	public void readFile() throws IOException {
		jfc.showOpenDialog(this);
		String filename = "";
		filename=jfc.getSelectedFile().getPath();	//得到路径和文件名
		File file = new File(filename);
		if(!file.exists())
			file.createNewFile();
		
		String s = new String(readFromFile());
		this.jTextArea.setText(s);
	}
	
	
	//获取文件内容
	public String readFromFile() throws IOException {
		String filename=jfc.getSelectedFile().getPath();	//得到路径和文件名
		File file = new File(filename);
		FileReader fileReader = new FileReader(file);			//谁来读文件
		int size = (int)file.length();		//file.length是long型的,需要强制转换
		char[] ch = new char[size];			//定义一个char的数组，长度为文件的长度
		int length = 0;						//
		while (fileReader.ready()) {
			length = length + fileReader.read(ch,0,size-length);
		}
		fileReader.close();
		return new String(ch);
	}
	
	
	//保存文件
	public void writeFile() throws IOException {
		jfc.showSaveDialog(this);
		String filename = jfc.getSelectedFile().getPath();
		File file = new File(filename);
		FileWriter fw = new FileWriter(file);
		String s = jTextArea.getText();
		fw.write(s);
		fw.close();
	}
	
	
	//全选
    public void SelectAllString() {
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
    	Clipboard clipboard = toolkit.getSystemClipboard();
    	String s = jTextArea.getText();
		StringSelection stringSelection = new StringSelection(s);
		clipboard.setContents(stringSelection, null);
	}
    
    
    //实现复制的功能
    public void CopyString() {
        // 获取系统的剪切板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // 获取选中的内容
        String copyText = jTextArea.getSelectedText();

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
		this.jTextArea.replaceRange(s, jTextArea.getSelectionStart(), jTextArea.getSelectionEnd());
	}
    
    //TODO 实现记事本的剪切功能
    public void CutString() {
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
    	Clipboard clipboard = toolkit.getSystemClipboard();
    	String s = jTextArea.getSelectedText();
    	StringSelection stringSelection = new StringSelection(s);
    	clipboard.setContents(stringSelection, null);
    	jTextArea.replaceRange("", jTextArea.getSelectionStart(), jTextArea.getSelectionEnd());
	}
}