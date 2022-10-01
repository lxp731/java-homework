package GUI;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class KeyBoardEvent extends JFrame {

	keyPanel kp = new keyPanel();
	KeyBoardEvent(){
		Container c = this.getContentPane();
		c.add(kp);
		kp.setFocusable(true);
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		kp.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// TODO 自动生成的方法存根
				switch (e.getKeyCode()) {
				case KeyEvent.VK_DOWN:
					kp.y+=10;
					break;
				case KeyEvent.VK_UP:
					kp.y-=10;
					break;
				case KeyEvent.VK_LEFT:
					kp.x-=10;
					break;
				case KeyEvent.VK_RIGHT:
					kp.x+=10;
					break;

				default:kp.c=e.getKeyChar();
					break;
				}
				kp.repaint();
			}
		});
	}
	
	
	public static void main(String[] args) {
		KeyBoardEvent kbe = new KeyBoardEvent();
		kbe.setVisible(true);
	}

class keyPanel extends JPanel{
		int x = 250;
		int y = 250;
		char c = 'a';
		@Override
		protected void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
		super.paintComponent(g);
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.drawString(String.valueOf(c), x, y);
		}
	}
}
