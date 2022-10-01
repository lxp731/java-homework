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
		
		
		//�����������
		jb.addMouseListener(new MouseAdapter() {		//MouseAdapter ����Ҫ

			int account= 1;
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO �Զ����ɵķ������
				jtf.setText("mouse enter "+account);
				account++;
			}
			
		});
		
		
		//���ô�������
		this.setSize(500,500);
		this.setTitle("MouseEvent");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	
	//�������
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		TestMouse tm = new TestMouse();
		tm.setVisible(true);
	}
	
}
