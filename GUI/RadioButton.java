package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioButton extends JFrame implements ItemListener{
	
	JRadioButton buttonL = new JRadioButton("�����");
	JRadioButton buttonC = new JRadioButton("�ж���");
	JRadioButton buttonR = new JRadioButton("�Ҷ���");
	JTextField jTextField = new JTextField("Hello");
	
	public RadioButton() {
		// TODO �Զ����ɵĹ��캯�����
		Container cp = this.getContentPane();
		JPanel p = new JPanel();
		p.add(buttonL);
		p.add(buttonC);
		p.add(buttonR);
		cp.add(jTextField,BorderLayout.NORTH);
		cp.add(p,BorderLayout.CENTER);
		
		this.setSize(300, 200);
		this.setTitle("��ѡ��ť");
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
		// TODO �Զ����ɵķ������
		if(buttonL.isSelected())
			jTextField.setHorizontalAlignment(JTextField.LEFT);
		if(buttonC.isSelected())
			jTextField.setHorizontalAlignment(JTextField.CENTER);
		if(buttonR.isSelected())
			jTextField.setHorizontalAlignment(JTextField.RIGHT);
	}

}
