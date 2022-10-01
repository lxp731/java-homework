package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dialog extends JFrame implements ActionListener {

    JButton jButton = new JButton("点我生成对话框");
    MyDialog dialog = new MyDialog(this, "我的对话框", true);

    public Dialog() {
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().add(jButton);

        this.setSize(300, 200);
        this.setTitle("对话框");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        jButton.addActionListener(this);
    }

    class MyDialog extends JDialog implements ActionListener {
        JButton jButton1 = new JButton("OK");
        JButton jButton2 = new JButton("Cancel");

        public MyDialog(Dialog dialog, String title, boolean modal) {
            super(dialog, title, modal);
            Container cp = this.getContentPane();
            cp.setLayout(new FlowLayout());
            cp.add(jButton1);
            cp.add(jButton2);
            jButton2.addActionListener(this);

            this.setSize(200, 120);
            this.setLocationRelativeTo(null);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO 自动生成的方法存根
            if (e.getSource() == jButton2)
                System.exit(0);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO 自动生成的方法存根
        if (e.getSource() == jButton)
            dialog.setVisible(true);
    }

    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        Dialog dialog = new Dialog();
        dialog.setVisible(true);
    }
}
