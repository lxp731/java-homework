package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckBox extends JFrame implements ItemListener {

    JCheckBox bold = new JCheckBox("�Ӵ�");
    JCheckBox inclined = new JCheckBox("��б");
    JTextField jTextField = new JTextField("Hello");

    public CheckBox() {
        Container cp = this.getContentPane();
        JPanel p = new JPanel();
        p.add(bold);
        p.add(inclined);
        cp.add(p, BorderLayout.CENTER);
        cp.add(jTextField, BorderLayout.NORTH);

        this.setSize(300, 200);
        this.setTitle("��ѡ��");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        bold.addItemListener(this);
        inclined.addItemListener(this);
    }

    public static void main(String[] args) {
        // TODO �Զ����ɵķ������
        CheckBox checkBox = new CheckBox();
        checkBox.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO �Զ����ɵķ������
        int selectedStyle = 0;
        if (bold.isSelected())
            selectedStyle += Font.BOLD;
        if (inclined.isSelected())
            selectedStyle += Font.ITALIC;
        jTextField.setFont(new Font("����", selectedStyle, 20));
    }

}
