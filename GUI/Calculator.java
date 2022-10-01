package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Calculator extends JFrame implements ActionListener{
	JTextField f1 = new JTextField(5);
	JLabel jLabel = new JLabel("+");
	JTextField f2 = new JTextField(5);
	JButton jButton = new JButton("=");
	JTextField f3 = new JTextField(5);
	JButton clear = new JButton("clear");
	
	public Calculator() {
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(f1);
		cp.add(jLabel);
		cp.add(f2);
		cp.add(jButton);
		cp.add(f3);
		cp.add(clear);
		f3.setEditable(false);
		
		jButton.addActionListener(this);
		clear.addActionListener(this);
		
		this.setSize(300, 160);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Calculator 1.0");
	}
	
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		calculator.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource() == jButton) {
			String str1 = f1.getText();
			String str2 = f2.getText();
			double d1 = Double.valueOf(str1);
			double d2 = Double.valueOf(str2);
			String str3 =String.valueOf(d1+d2);
			f3.setText(str3);
		} else if(e.getSource() == clear){
			f1.setText("");
			f2.setText("");
			f3.setText("");
		}
	}

}
