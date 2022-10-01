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
	
	JMenuItem fileNew,fileOpen,fileSave,fileExit;				//�ļ��Ӳ˵�
	JRadioButtonMenuItem optBlue,optYellow,optRed,optWhite,optdefault;		//ѡ���Ӳ˵�
	JMenuItem fileRevoke,fileCut,fileCopy,filePaste,fileAll;	//�༭�Ӳ˵�
	JMenuItem timestamp;										//�����Ӳ˵�
	JMenuItem about;											//�����Ӳ˵�
	
	JCheckBox cb = new JCheckBox("�Զ�����");
	
	JTextArea jta = new JTextArea();							//�ı���
	JScrollPane jsp = new JScrollPane(jta);						//���ı�������ӹ�����
	JFileChooser jfc = new JFileChooser();						//�ļ�
	
	public  TestNote() {
		
		this.setLayout(null);									//���ò���
		
		this.add(jsp);								//�ı�����ֱ��ӹ�����
		jsp.setBounds(5, 5, 700, 285);				//����������
		jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);			//������ʾ
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	//��Ҫʱ��ʾ
		
		JMenuBar jmb = new JMenuBar();				//����˵���
		this.setJMenuBar(jmb);						//�Ѳ˵����ӵ�����ϣ�һ�����ֻ����һ���˵���
		
		JMenu file = new JMenu("�ļ�");
		file.setFont(new Font("TimesRoman",Font.BOLD,15));
		
		JMenu format = new JMenu("��ʽ");
		format.setFont(new Font("TimesRoman",Font.BOLD,15));
		
		JMenu edie = new JMenu("�༭");
		edie.setFont(new Font("TimesRoman",Font.BOLD,15));
		
		JMenu aider = new JMenu("����");
		aider.setFont(new Font("TimesRoman",Font.BOLD,15));
		
		JMenu help = new JMenu("����");
		help.setFont(new Font("TimesRoman",Font.BOLD,15));
		
//		JCheckBox cb = new JCheckBox("�Զ�����");
		JMenuItem color = new JMenu("��ɫ(C)");
		

		
		//�˵�����Ĳ˵�����
		jmb.add(file);
		jmb.add(format);
		jmb.add(edie);
		jmb.add(aider);
		jmb.add(help);
		
		//��ʽ�˵��������
		format.add(cb);
		format.addSeparator();
		format.add(color);
		
		//�ļ��˵��������
		file.add(fileNew = new JMenuItem("�½�"));
		fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		file.addSeparator();
		file.add(fileOpen = new JMenuItem("��"));
		fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		file.addSeparator();
		file.add(fileSave = new JMenuItem("����"));
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		file.addSeparator();
		file.add(fileExit = new JMenuItem("�˳�"));
		fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		//��ɫ�Ӳ˵��µ�����
		color.add(optBlue = new JRadioButtonMenuItem("������(B)"));
		color.add(optYellow = new JRadioButtonMenuItem("õ���(Y)"));
		color.add(optRed = new JRadioButtonMenuItem("��ʨ��(R)"));
		color.add(optWhite = new JRadioButtonMenuItem("Ĭ�ϰ�(W)"));
		color.add(optdefault = new JRadioButtonMenuItem("���İ�(D)"));
		ButtonGroup btg = new ButtonGroup();
		btg.add(optBlue);
		btg.add(optYellow);
		btg.add(optRed);
		btg.add(optWhite);
		btg.add(optdefault);
		
		//�༭�˵��µ�����
		edie.add(fileRevoke = new JMenuItem("����(Z)"));
		fileRevoke.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		edie.addSeparator();
		
		edie.add(fileCut = new JMenuItem("����(X)"));
		fileCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		edie.addSeparator();
		
		edie.add(fileCopy = new JMenuItem("����(C)"));
		fileCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		edie.addSeparator();
		
		edie.add(filePaste = new JMenuItem("ճ��(V)"));
		filePaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		edie.addSeparator();
		
		edie.add(fileAll = new JMenuItem("ȫѡ(A)"));
		fileAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		//�����˵��µ�����
		aider.add(timestamp = new JMenuItem("���ʱ���"));
		timestamp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		//�����˵��µ�����
		help.add(about = new JMenuItem("����"));
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		//"�ļ�"���ڲ˵��ļ�����
		fileNew.addActionListener(this);
		fileOpen.addActionListener(this);
		fileSave.addActionListener(this);
		fileExit.addActionListener(this);
		
		//"��ʽ"���ڲ˵��ļ�����
		cb.addActionListener(this);
		optBlue.addItemListener(this);
		optYellow.addItemListener(this);
		optRed.addItemListener(this);
		optWhite.addItemListener(this);
		optdefault.addItemListener(this);
		
		//"�༭"���ڲ˵��ļ�����
		fileRevoke.addActionListener(this);
		fileCut.addActionListener(this);
		fileCopy.addActionListener(this);
		filePaste.addActionListener(this);
		fileAll.addActionListener(this);
		
		//"����"���ڲ˵��ļ�����
		timestamp.addActionListener(this);
		
		//"����"���ڲ˵��ļ�����
		about.addActionListener(this);
		
		//��ʼ���ڸ������Ե�����
		this.jta.setBackground(new Color(245, 255, 250));                  //���ó�ʼ������ɫ	
		this.jta.setSelectedTextColor(new Color(238,121,66));              //�����ı���ѡ������ɫ�� ���Զ�ȥapi�鷽�� ��     ��
		this.jta.setSelectionColor(new Color(238,238,209));                //����ѡ��ɫ
        this.jta.setFont(new Font("����",Font.BOLD,20));
		this.setTitle("�Ҳ��Ǽ��±� V1.3.0");								   //���ô��ڱ���
		this.setSize(720,360);											   //���ó�ʼ���ڴ�С
		this.setLocationRelativeTo(null);								   //���ó�ʼ���ھ�����ʾ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			   //����Ĭ�Ϲرշ�ʽ
	}
	

	
	//TODO ʵ�ָı���±��ı�����ɫ
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO �Զ����ɵķ������
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
	
	
	
	//TODO ʵ���ļ��ı���
	public void writeFile() throws IOException {
		jfc.showSaveDialog(this);
		String filename = jfc.getSelectedFile().getPath();
		File file = new File(filename);
		FileWriter fw = new FileWriter(file);
		String s = jta.getText();
		fw.write(s);
		fw.close();
	}

	
	
	//TODO ʵ�ֶ��ļ����ݵķ���
	public String readFromFile() throws IOException {
		String filename=jfc.getSelectedFile().getPath();	//�õ�·�����ļ���
		File file = new File(filename);
		FileReader fileReader = new FileReader(file);			//˭�����ļ�
		int size = (int)file.length();		//file.length��long�͵�,��Ҫǿ��ת��
		char[] ch = new char[size];			//����һ��char�����飬����Ϊ�ļ��ĳ���
		int length = 0;						
		while (fileReader.ready()) {
			length = length + fileReader.read(ch,0,size-length);
		}
		fileReader.close();
		return new String(ch);
	}
	
	
	
	//TODO ʵ���ļ����ݵ���ʾ����ʾ���ı������ڣ�
	public void readFile() throws IOException {
		
		jfc.showOpenDialog(this);
		String filename = "empty";
		filename=jfc.getSelectedFile().getPath();	//�õ�·�����ļ���
		File file = new File(filename);
		if(!file.exists())
			file.createNewFile();
		String s = new String(readFromFile());
		this.jta.setText(s);
	}
	
	
	
	//TODO ʵ���½��ļ��Ĺ���
	public void newFile() {
		jta.setText("");
	}
	
	
	
	//TODOʵ�����ʱ����Ĺ���
    public void addTime() {
        Date now = new Date(); //��ȡ��ǰʱ��
        SimpleDateFormat timenow = new SimpleDateFormat("yyyy��MM��dd�� hhʱmm��ss��");//��ʽ����ǰ����ʱ�� ��Ҫ���а�
        String time = timenow.format(now.getTime());
        String before=jta.getText();
        jta.setText(before+"\n\n\n"+"                                     "+time);
    }
    
    
        //TODOʵ�ָ��ƵĹ���
    public void CopyString() {
        // ��ȡϵͳ�ļ��а�
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // ��ȡѡ�е�����
        String copyText = jta.getSelectedText();

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
		this.jta.replaceRange(s, jta.getSelectionStart(), jta.getSelectionEnd());
	}

    
    
    //TODO ʵ��ȫѡ�Ĺ���
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
    
    
    
    //TODO ʵ���Զ����еĹ���
    public void WrapLine() {
		jta.setLineWrap(true);						//�����Զ�����Ϊ��
	}
    
    
    
    //TODO ʵ�ּ��±��ļ��й���
    public void CutString() {
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
    	Clipboard clipboard = toolkit.getSystemClipboard();
    	String s = jta.getSelectedText();
    	StringSelection stringSelection = new StringSelection(s);
    	clipboard.setContents(stringSelection, null);
    	jta.replaceRange("", jta.getSelectionStart(), jta.getSelectionEnd());
	}
    
    
    
	//TODO ���������������� ���������������� ����������������
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==fileNew)			//"�½�"�����ļ���
			this.newFile();
		else if(e.getSource()==fileOpen)	//"��"�����ļ���
			try {
				this.readFile();
			} catch (IOException e2) {
				// TODO �Զ����ɵ� catch ��
				e2.printStackTrace();
			}
		else if(e.getSource()==fileSave)	//"����"�����ļ���
			try {
				this.writeFile();
			} catch (IOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		else if(e.getSource()==fileExit)	//"�˳�"�����ļ���
			System.exit(0);
		else if(e.getSource()==fileCut)
//			JOptionPane.showMessageDialog(this, "���гɹ�");
			this.CutString();
		else if(e.getSource()==fileCopy)
			this.CopyString();
//			JOptionPane.showMessageDialog(this, "���Ƴɹ�");
		else if(e.getSource()==filePaste)
			this.PasteString();
//			JOptionPane.showMessageDialog(this, "ճ���ɹ�");
		else if(e.getSource()==fileRevoke)
			JOptionPane.showMessageDialog(this, "���ܳ��������� �����ڴ�");
		else if (e.getSource()==fileAll) 
			this.SelectAllString();
		else if(e.getSource()==about)
			JOptionPane.showMessageDialog(this, "Design By:XiaoPeng Liu\n"+
												"Power By:Knight\n"+
												"��Ȩ���� ��Ȩ�޷�");
		else if (e.getSource()==timestamp) 
			this.addTime();
		else if (cb.isSelected()) 
			jta.setLineWrap(true);
	}
	
	
	
	//TODO main����λ�ã���������
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		TestNote frame = new TestNote();
		frame.setVisible(true);
	}
}




