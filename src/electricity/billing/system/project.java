package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class project extends JFrame implements ActionListener {
	String atype, meter;

	project(String atype,String meter) {
		this.atype = atype;
		this.meter = meter;
		setExtendedState(JFrame.MAXIMIZED_BOTH); /* this shortcut can create a frame with full screen */

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/elect1.jpg"));
		Image i2 = i1.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		add(image);

		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);

		JMenu master = new JMenu("Master");
		master.setForeground(Color.BLUE);

		JMenuItem newcustomer = new JMenuItem("New Customer");
		newcustomer.setFont(new Font("monospaced", Font.PLAIN, 12));
		newcustomer.setBackground(Color.WHITE);
		ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icons/icon1.png"));
		Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		newcustomer.setIcon(new ImageIcon(image1));
		newcustomer.addActionListener(this);
		master.add(newcustomer);

		JMenuItem customerdetails = new JMenuItem("Customer Details");
		customerdetails.setFont(new Font("monospaced", Font.PLAIN, 12));
		customerdetails.setBackground(Color.WHITE);
		ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icons/icon2.png"));
		Image image2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		customerdetails.setIcon(new ImageIcon(image2));
		customerdetails.addActionListener(this);
		master.add(customerdetails);

		JMenuItem calculatebill = new JMenuItem("Calculate Bill");
		calculatebill.setFont(new Font("monospaced", Font.PLAIN, 12));
		calculatebill.setBackground(Color.WHITE);
		ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icons/icon5.png"));
		Image image4 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		calculatebill.setIcon(new ImageIcon(image4));
		calculatebill.addActionListener(this);
		master.add(calculatebill);
		
		JMenuItem depositedetails = new JMenuItem("Deposite Details");
		depositedetails.setFont(new Font("monospaced", Font.PLAIN, 12));
		depositedetails.setBackground(Color.WHITE);
		ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icons/icon3.png"));
		Image image3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		depositedetails.setIcon(new ImageIcon(image3));
		depositedetails.addActionListener(this);
		master.add(depositedetails);


		JMenu info = new JMenu("Information");
		info.setForeground(Color.RED);

		JMenuItem updateinformation = new JMenuItem("Update Information");
		updateinformation.setFont(new Font("monospaced", Font.PLAIN, 12));
		updateinformation.setBackground(Color.WHITE);
		ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icons/icon4.png"));
		Image image5 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		updateinformation.setIcon(new ImageIcon(image5));
		updateinformation.addActionListener(this);
		info.add(updateinformation);

		JMenuItem viewinformation = new JMenuItem("View Information");
		viewinformation.setFont(new Font("monospaced", Font.PLAIN, 12));
		viewinformation.setBackground(Color.WHITE);
		ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icons/icon6.png"));
		Image image6 = icon6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		viewinformation.setIcon(new ImageIcon(image6));
		viewinformation.addActionListener(this);
		info.add(viewinformation);

		JMenu user = new JMenu("User");
		user.setForeground(Color.BLUE);

		JMenuItem paybill = new JMenuItem("Pay Bill");
		paybill.setFont(new Font("monospaced", Font.PLAIN, 12));
		paybill.setBackground(Color.WHITE);
		ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icons/icon7.png"));
		Image image7 = icon7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		paybill.setIcon(new ImageIcon(image7));
		paybill.addActionListener(this);
		user.add(paybill);

		JMenuItem billdetails = new JMenuItem("Bill Details");
		billdetails.setFont(new Font("monospaced", Font.PLAIN, 12));
		billdetails.setBackground(Color.WHITE);
		ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icons/icon8.png"));
		Image image8 = icon8.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		billdetails.setIcon(new ImageIcon(image8));
		billdetails.addActionListener(this);
		user.add(billdetails);

		JMenu report = new JMenu("Report");
		report.setForeground(Color.RED);

		JMenuItem generatebill = new JMenuItem("Generate Bill");
		generatebill.setFont(new Font("monospaced", Font.PLAIN, 12));
		generatebill.setBackground(Color.WHITE);
		ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icons/icon9.png"));
		Image image9 = icon9.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		generatebill.setIcon(new ImageIcon(image9));
		generatebill.addActionListener(this);
		report.add(generatebill);

		JMenu mexit = new JMenu("Exit");
		mexit.setForeground(Color.BLUE);

		JMenuItem exit = new JMenuItem("Exit");
		exit.setFont(new Font("monospaced", Font.PLAIN, 12));
		exit.setBackground(Color.WHITE);
		ImageIcon icon12 = new ImageIcon(ClassLoader.getSystemResource("icons/icon11.png"));
		Image image12 = icon12.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		exit.setIcon(new ImageIcon(image12));
		exit.addActionListener(this);
		mexit.add(exit);

		if (atype.equals("Admin")) {
			mb.add(master);
		} else {
			mb.add(info);
			mb.add(user);
			mb.add(report);
		}
		mb.add(mexit);

		setLayout(new FlowLayout());
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		String msg = ae.getActionCommand();
		if (msg.equals("New Customer")) {
			new NewCustomer();
		} else if (msg.equals("Customer Details")) {
			new CustomerDetails();
		} else if (msg.equals("Calculate Bill")) {
			new CalculateBill();
		} else if (msg.equals("Deposite Details")) {
			new DepositeDetails();
		} else if (msg.equals("View Information")) {
			new ViewInformation(meter);
		} else if (msg.equals("Update Information")) {
			new UpdateInformation(meter);
		} else if(msg.equals("Bill Details")) {
			new BillDetails(meter);
		} else if(msg.equals("Exit")) {
			setVisible(false);
			new Login();
		} else if(msg.equals("Pay Bill")) {
			new PayBill(meter);
		} else if(msg.equals("Generate Bill")) {
			new GenerateBill(meter);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new project("","");
	}

}
