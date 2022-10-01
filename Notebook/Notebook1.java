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
	
	//����һ���˵���
	JMenuBar jMenuBar = new JMenuBar();
	
	JMenu file = new JMenu("�ļ�");
	JMenu edit = new JMenu("�༭");
	
	JMenuItem fileOpen = new JMenuItem("��");
	JMenuItem fileSave = new JMenuItem("����");
	JMenuItem exit = new JMenuItem("�˳�");
	JMenuItem fileCopy = new JMenuItem("����");
	JMenuItem filePaste = new JMenuItem("ճ��"); 
	JMenuItem fileAll = new JMenuItem("ȫѡ");
	JMenuItem fileCut = new JMenuItem("����");
	
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
				// TODO �Զ����ɵ� catch ��
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
	
	
	//���ļ�
	public void readFile() throws IOException {
		jfc.showOpenDialog(this);
		String filename = "";
		filename=jfc.getSelectedFile().getPath();	//�õ�·�����ļ���
		File file = new File(filename);
		if(!file.exists())
			file.createNewFile();
		
		String s = new String(readFromFile());
		this.jTextArea.setText(s);
	}
	
	
	//��ȡ�ļ�����
	public String readFromFile() throws IOException {
		String filename=jfc.getSelectedFile().getPath();	//�õ�·�����ļ���
		File file = new File(filename);
		FileReader fileReader = new FileReader(file);			//˭�����ļ�
		int size = (int)file.length();		//file.length��long�͵�,��Ҫǿ��ת��
		char[] ch = new char[size];			//����һ��char�����飬����Ϊ�ļ��ĳ���
		int length = 0;						//
		while (fileReader.ready()) {
			length = length + fileReader.read(ch,0,size-length);
		}
		fileReader.close();
		return new String(ch);
	}
	
	
	//�����ļ�
	public void writeFile() throws IOException {
		jfc.showSaveDialog(this);
		String filename = jfc.getSelectedFile().getPath();
		File file = new File(filename);
		FileWriter fw = new FileWriter(file);
		String s = jTextArea.getText();
		fw.write(s);
		fw.close();
	}
	
	
	//ȫѡ
    public void SelectAllString() {
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
    	Clipboard clipboard = toolkit.getSystemClipboard();
    	String s = jTextArea.getText();
		StringSelection stringSelection = new StringSelection(s);
		clipboard.setContents(stringSelection, null);
	}
    
    
    //ʵ�ָ��ƵĹ���
    public void CopyString() {
        // ��ȡϵͳ�ļ��а�
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // ��ȡѡ�е�����
        String copyText = jTextArea.getSelectedText();

        System.out.println("�㸴�Ƶ������ǣ�" + copyText);
        // ��װ�ı�����
        Transferable trans = new StringSelection(copyText);
        // ���ı��������õ�ϵͳ������
        clipboard.setContents(trans, null);
    }

    
    //ʵ��ճ���Ĺ���
    public void PasteString()  {
    	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = clipboard.getContents(this);
		String s = "";
		try {
			s = (String) transferable.getTransferData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		this.jTextArea.replaceRange(s, jTextArea.getSelectionStart(), jTextArea.getSelectionEnd());
	}
    
    //TODO ʵ�ּ��±��ļ��й���
    public void CutString() {
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
    	Clipboard clipboard = toolkit.getSystemClipboard();
    	String s = jTextArea.getSelectedText();
    	StringSelection stringSelection = new StringSelection(s);
    	clipboard.setContents(stringSelection, null);
    	jTextArea.replaceRange("", jTextArea.getSelectionStart(), jTextArea.getSelectionEnd());
	}
}