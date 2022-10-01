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
	    //实例化文本框
	    //在文本框上添加滚动条
	        //设置矩形大小.参数依次为(矩形左上角横坐标x,矩形左上角纵坐标y，矩形长度，矩形宽度)
	        //默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
	        //把滚动条添加到容器里面
	    }
	 
	}