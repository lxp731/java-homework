package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioButton extends JFrame implements ItemListener{
	
	JRadioButton buttonL = new JRadioButton("左对齐");
	JRadioButton buttonC = new JRadioButton("中对齐");
	JRadioButton buttonR = new JRadioButton("右对齐");
	JTextField jTextField = new JTextField("Hello");
	
	public RadioButton() {
		// TODO 自动生成的构造函数存根
		Container cp = this.getContentPane();
		JPanel p = new JPanel();
		p.add(buttonL);
		p.add(buttonC);
		p.add(buttonR);
		cp.add(jTextField,BorderLayout.NORTH);
		cp.add(p,BorderLayout.CENTER);
		
		this.setSize(300, 200);
		this.setTitle("单选按钮");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		ButtonGroup group = new ButtonGroup();
		group.add(buttonL);
		group.add(buttonC);
		group.add(buttonR);
		
		buttonL.addItemListener(this);
		buttonC.addItemListener(this);
		buttonR.addItemListener(this);
	}
	
	

	public static void main(String[] args) {
		RadioButton radioButton = new RadioButton();
		radioButton.setVisible(true);
	}



	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO 自动生成的方法存根
		if(buttonL.isSelected())
			jTextField.setHorizontalAlignment(JTextField.LEFT);
		if(buttonC.isSelected())
			jTextField.setHorizontalAlignment(JTextField.CENTER);
		if(buttonR.isSelected())
			jTextField.setHorizontalAlignment(JTextField.RIGHT);
	}

}
