package lianxi;

import javax.swing.*;
import java.awt.*;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-04 10:41
 **/

public class test extends JFrame {
    public test() {
        Container cp = getContentPane();
        JTextArea jta = new JTextArea("欢迎使用疼逊扣扣......");
        cp.add(jta);

        this.setSize(500, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        test t = new test();
        t.setVisible(true);
    }
}
