package Dandi;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Note extends JFrame implements ActionListener {
	JMenuBar menubar;
	JMenu file,edit,format,watch,help;
	JMenuItem create,open,save,saveto,set,print,exit;
	JMenuItem cx,cut,copy,paste,delete,cz,th,zd,qx,rq;
	JMenuItem font,ztl,ckbz,gy;
	JCheckBoxMenuItem zdhh;
	JTextArea panel;
	JScrollPane jsp;
	private static String name;
	JFileChooser filechooser;
	public Note(){
		super("�½��ı��ĵ�.txt"+"- ���±�");
		menubar=new JMenuBar();
		filechooser=new JFileChooser();
		file=new JMenu("�ļ�(F)");
		edit=new JMenu("�༭(E)");
		format=new JMenu("��ʽ(O)");
		watch=new JMenu("�鿴(V)");
		help=new JMenu("����(H)");
		create=new JMenuItem("�½�");
		open=new JMenuItem("��");
		save=new JMenuItem("����");
		saveto=new JMenuItem("���Ϊ");
		set=new JMenuItem("ҳ������");
		print=new JMenuItem("��ӡ");
		exit=new JMenuItem("�˳�");
		cx=new JMenuItem("����");cut=new JMenuItem("����");copy=new JMenuItem("����");paste=new JMenuItem("ճ��");
		delete=new JMenuItem("ɾ��");cz=new JMenuItem("����");th=new JMenuItem("�滻");zd=new JMenuItem("ת��");
		qx=new JMenuItem("ȫѡ");rq=new JMenuItem("����/ʱ��");
		zdhh=new JCheckBoxMenuItem("�Զ�����");font=new JMenuItem("����");ztl=new JMenuItem("״̬��");ckbz=new JMenuItem("�鿴����");gy=new JMenuItem("���ڼ��±�");
		menubar.add(file);menubar.add(edit);menubar.add(format);menubar.add(watch);menubar.add(help);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		file.add(create);file.add(open);file.add(save);file.add(saveto);file.addSeparator();file.add(set);file.add(print);file.addSeparator();file.add(exit);
		edit.add(cx);edit.addSeparator();edit.add(cut);edit.add(copy);edit.add(paste);edit.add(delete);
		edit.addSeparator();edit.add(cz);edit.add(th);edit.add(zd);edit.addSeparator();edit.add(qx);edit.add(rq);
		cx.setEnabled(false);cut.setEnabled(false);copy.setEnabled(false);paste.setEnabled(false);delete.setEnabled(false);cz.setEnabled(false);th.setEnabled(false);
		zd.setEnabled(false);qx.setEnabled(false);
		format.add(zdhh);format.add(font);
		watch.add(ztl);help.add(ckbz);help.addSeparator();help.add(gy);
		create.addActionListener(this);open.addActionListener(this);save.addActionListener(this);
		saveto.addActionListener(this);set.addActionListener(this);exit.addActionListener(this);gy.addActionListener(this);help.addActionListener(this);
		zdhh.addActionListener(this);font.addActionListener(this);rq.addActionListener(this);
		print.setEnabled(false);
		this.setJMenuBar(menubar);
		panel=new JTextArea();
		//panel.setLayout(new FlowLayout());
		jsp=new JScrollPane(panel);
		jsp.setPreferredSize(new Dimension(780,550));
		this.add(jsp,BorderLayout.CENTER);
		//this.add(panel);
		this.setBounds(600, 300, 600, 400);
		this.setVisible(true);
		this.setIconImage(new ImageIcon("src\\1.ico").getImage());
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	public static void main(String[] args) {
		Note note=new Note();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==create){
			createFile();
		}
		if(e.getSource()==exit){
			System.exit(0);
		}
		if(e.getSource()==open){
			filechooser.showOpenDialog(this);
			panel.setText(openFile());
		}
		if(e.getSource()==save||e.getSource()==saveto){
			filechooser.showSaveDialog(this);
			File file=filechooser.getSelectedFile();
			saveFile(file);
		}
		if(e.getSource()==set){
			String s=JOptionPane.showInputDialog(this,"��������ߣ�","Ĭ�ϣ�600 400");
			String[] str=new String[2];
			str=s.split(" ");
			int wigth=Integer.parseInt(str[0]);
			int height=Integer.parseInt(str[1]);
			this.setSize(wigth, height);
		}
		if(e.getSource()==copy){
			panel.copy();
		}
		if(e.getSource()==cut){
			panel.cut();
		}
		if(e.getSource()==paste){
			panel.paste();
		}
		if(e.getSource()==rq){
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			JOptionPane.showMessageDialog(this, df.format(new Date()));
		}
		if(e.getSource()==font){
			String s=JOptionPane.showInputDialog(this,"�������������ƣ�","����");
			panel.setFont(new Font(s,Font.TYPE1_FONT,20));
		}
		if(e.getSource()==gy){
			JOptionPane.showMessageDialog(this, "�������˻���ʱ��ֱ���Ʒ��ò׺���");
		}
		if(e.getSource()==help){
			String web="www.baidu.com";
			Desktop desktop=Desktop.getDesktop();
			try {
				desktop.browse(new URI(web));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
		if(e.getSource()==zdhh){
			if(zdhh.isSelected()){
				panel.setLineWrap(true);
			}else{
			panel.setLineWrap(false );
			}
		}
	}
	@SuppressWarnings("finally")
	private String openFile() {
		FileInputStream is=null;
		StringBuilder sb=null;
		//FileOutputStream fo=null;
		try {
			is=new FileInputStream(filechooser.getSelectedFile());
			sb=new StringBuilder();
			byte[] data=new byte[1024];
			int len=-1;
			try {
				while((len=is.read(data))!=-1){
					String str=new String(data,0,len);
					sb.append(str);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			name = filechooser.getSelectedFile().getName();
			this.setTitle(name + " - ���±�");
			return sb.toString();
		}
		
	}
	private void createFile() {
		// String name = null;
		File file = null;
		// ѡ�񱣴��ȡ��
		if (filechooser.showSaveDialog(Note.this) == JFileChooser.APPROVE_OPTION) {
			file = filechooser.getSelectedFile();// ��ȡѡ�е��ļ�
		} else {
			return;
		}
		name = filechooser.getName(file);// ��ȡ������ļ���
		if (file.exists()) { // ��ѡ�������ļ�----ѯ���Ƿ�Ҫ����
			int i = JOptionPane.showConfirmDialog(null, "���ļ��Ѵ���,�Ƿ񸲸�ԭ�ļ�", "ȷ��", JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				saveFile(file);
			} else {
				filechooser.showSaveDialog(Note.this);// ����ѡ��
			}
		} else {//�ļ������ڣ���ֱ�ӱ���
			saveFile(file);
		}
	}
	private void saveFile(File file) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			bw.write(panel.getText());//д���ļ�
			bw.flush();
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(Note.this, "�ļ��������" + e1.getMessage());
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e1) {
			}
		}
	}


}