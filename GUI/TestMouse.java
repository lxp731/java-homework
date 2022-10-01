package GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TestMouse extends JFrame {

	JButton jb = new JButton("Press me");
	JTextField jtf = new JTextField(35);
	TestMouse(){
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(jtf);
		cp.add(jb);
		
		
		//添加鼠标监听器
		jb.addMouseListener(new MouseAdapter() {		//MouseAdapter 很重要

			int account= 1;
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				jtf.setText("mouse enter "+account);
				account++;
			}
			
		});
		
		
		//设置窗口属性
		this.setSize(500,500);
		this.setTitle("MouseEvent");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	
	//程序入口
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		TestMouse tm = new TestMouse();
		tm.setVisible(true);
	}
	
}
