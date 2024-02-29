package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Splash extends JFrame implements Runnable { // (can't create abstract class object)

	Thread t;
	Splash() {
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/elect.jpg"));
		Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2); // i2 can't pass directly in to JLabel
		JLabel image = new JLabel(i3);
		add(image);

		setVisible(true);

		int x = 1;
		for (int i=2; i<600; i+=4, x+=1) {
			setSize(i+x, i);
			setLocation(700 -((i+x)/2),400-(i/2));
			try {
				Thread.sleep(5);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		t = new Thread(this); //reference of current class
		t.start();
		
		
		setSize(730, 550); // length , width
		setLocation(320, 120); // left to 320, top to 100
		setVisible(true);
	}

	public void run() { // method overriding
		try {
			Thread.sleep(7000);
			setVisible(false);
			
			//login form
			new Login();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Splash();

	}

}
