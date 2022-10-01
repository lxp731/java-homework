package Clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.time.LocalTime;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Myclock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ActionListener listener = new TimerClock();
		Timer t = new Timer(1000, listener);
		t.start();
		//System.exit(0);
	}

}

class ClockWindow extends JFrame {
	ClockWindow() {
		add(new ClockInfo());
		pack();
	}
}

class ClockInfo extends JComponent {
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 500;
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.draw(new Ellipse2D.Double(50.0, 50.0, 400.0, 400.0));
		//圆心为250, 250
		//绘制小时刻度
		g2.setPaint(Color.red);
		double r1 = 190, r2 = 200;
		double cx = 250.0, cy = 250.0;
		for(double i = 0; i < 2.0 * Math.PI; i += (Math.PI / 6.0)) {
			double lx, ly, rx, ry;
			lx = r1 * Math.sin(i);
			ly = r1 * Math.cos(i);
			rx = r2 * Math.sin(i);
			ry = r2 * Math.cos(i);
			g2.draw(new Line2D.Double(cx + lx, cy - ly, cx + rx, cy - ry));
		}
		//绘制分钟刻度
		r1 = 195.0;
		for(double i = 0; i < 2.0 * Math.PI; i += (Math.PI / 30.0)) {
			double lx, ly, rx, ry;
			lx = r1 * Math.sin(i);
			ly = r1 * Math.cos(i);
			rx = r2 * Math.sin(i);
			ry = r2 * Math.cos(i);
			g2.draw(new Line2D.Double(cx + lx, cy - ly, cx + rx, cy - ry));
		}
		//绘制指针
		double hour = LocalTime.now().getHour() * Math.PI / 6.0;
		double minute = LocalTime.now().getMinute() * Math.PI / 30.0;
		double second = LocalTime.now().getSecond() * Math.PI / 30.0;
		//时针
		g2.setPaint(Color.black);
		g2.setStroke(new BasicStroke(4.0f));
		g2.draw(new Line2D.Double(cx, cy, cx + 120.0 * Math.sin(hour), cy - 120.0 * Math.cos(hour)));
		//分针
		g2.setPaint(Color.green);
		g2.setStroke(new BasicStroke(2.0f));
		g2.draw(new Line2D.Double(cx, cy, cx + 140.0 * Math.sin(minute), cy - 140.0 * Math.cos(minute)));
		//秒针
		g2.setPaint(Color.red);
		g2.setStroke(new BasicStroke(1.0f));
		g2.draw(new Line2D.Double(cx, cy, cx + 160.0 * Math.sin(second), cy - 160.0 * Math.cos(second)));
		//文字
		Font f = new Font("Serif", Font.PLAIN, 30);//逻辑字体
		g2.setFont(f);
		g2.setColor(Color.orange);
		int h = LocalTime.now().getHour();
		int m = LocalTime.now().getMinute();
		int s = LocalTime.now().getSecond();
		g2.drawString("" + h + ":" + m + ":" + s,10,50);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}

class TimerClock implements ActionListener {
	ClockWindow frame;
	
	TimerClock() {
		frame = new ClockWindow();
		frame.setTitle("MyClock");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		frame.repaint();
	}
	
}
