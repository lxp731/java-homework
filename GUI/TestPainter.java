package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;


public class TestPainter extends JFrame implements MouseMotionListener{

    int startX;
    int startY;

    public TestPainter (){
    	
    	this.addMouseMotionListener(this);
        this.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		// TODO 自动生成的方法存根
        		startX=e.getX();
        		startY=e.getY();
        	}
		});
        
        
//        this.addMouseMotionListener(new MouseMotionAdapter() {
//        	@Override
//        	public void mouseDragged(MouseEvent e) {
//        		// TODO 自动生成的方法存根
//        		Graphics g = this.getGraphics();
//        		int x = e.getX();
//        		int y = e.getY();
//        		if ((e.getModifiersEx() & InputEvent.BUTTON3_DOWN_MASK) != 0 ) {
//        			g.setColor(this.getBackground());
//        			g.fillOval(x-10, y-10, 20, 20);
//        		} else{
//        			g.drawLine(startX, startY, x, y);
//        			startX = x;
//        			startY = y;
//        		}
//        	}
//        	
//		});
        
        this.setSize(500,500);
    	this.setTitle("小画家");
    	this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //实现鼠标左键画，右键擦的功能
	@Override
    	public void mouseDragged(MouseEvent e) {
		// TODO 自动生成的方法存根
		Graphics g = this.getGraphics();
		int x = e.getX();
		int y = e.getY();
		if ((e.getModifiersEx() & InputEvent.BUTTON3_DOWN_MASK) != 0 ) {
			g.setColor(this.getBackground());
			g.fillOval(x-10, y-10, 20, 20);
		} else{
			g.drawLine(startX, startY, x, y);
			startX = x;
			startY = y;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	
	
	//程序入口
	public static void main(String[] args) {
		TestPainter test = new TestPainter();
		test.setVisible(true);
	}
}
