package Clock;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ETClock extends JFrame{
	public ETClock() throws InterruptedException {
		
		JLabel label1 = new JLabel();
		label1.setText((new Date()).toString());
		label1.setFont(new Font("????", Font.BOLD, 50));
		label1.setForeground(new Color(244,96,108));
		this.add(label1);
		this.pack();
		this.setVisible(true);
		
		while(true) {
//			label1.setText((new Date().toString()));
			Date now = new Date(); //?????????
			SimpleDateFormat timenow = new SimpleDateFormat("yyyy??MM??dd?? hh?mm??ss??");//??????????????? ??????а?
	        String time = timenow.format(now.getTime());
	        label1.setText(time);
	        Thread.sleep(1000);
		}

	}
	
	public static void main(String[] args) throws InterruptedException {
		ETClock clock = new ETClock();
		clock.setVisible(true);
	}

}
