package GUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JScroll extends JFrame{
	JScroll(){
		JTextArea jta= new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);
		
		this.setLayout(null);
		jsp.setBounds(13, 10, 350, 340);
		jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(jsp);
		
		this.setLocationRelativeTo(null);
		this.setSize(400, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	  public static void main(String[] args) {
	    JScroll mv=new JScroll();
	    mv.setVisible(true);
	    //ʵ�����ı���
	    //���ı�������ӹ�����
	        //���þ��δ�С.��������Ϊ(�������ϽǺ�����x,�������Ͻ�������y�����γ��ȣ����ο��)
	        //Ĭ�ϵ������ǳ����ı���Ż���ʾ�����������������ù�����һֱ��ʾ
	        //�ѹ�������ӵ���������
	    }
	 
	}