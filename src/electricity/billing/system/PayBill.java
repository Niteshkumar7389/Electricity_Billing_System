package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class PayBill extends JFrame implements ActionListener {

	Choice cmonth;
	JButton pay, back;
	String meter;

	PayBill(String meter) {
		this.meter = meter;
		setLayout(null);
		setBounds(300, 150, 900, 600);
		getContentPane().setBackground(Color.WHITE);

		JLabel heading = new JLabel("PAY YOUR BILL");
		heading.setBounds(120, 5, 400, 30);
		heading.setFont(new Font("Tahoma", Font.BOLD, 24));
		add(heading);

		JLabel lblmeternumber = new JLabel("Meter Number");
		lblmeternumber.setBounds(35, 80, 200, 20);
		add(lblmeternumber);

		JLabel meternumber = new JLabel("");
		meternumber.setBounds(300, 80, 200, 20);
		add(meternumber);

		JLabel lblname = new JLabel("Name");
		lblname.setBounds(35, 140, 200, 20);
		add(lblname);

		JLabel name = new JLabel("");
		name.setBounds(300, 140, 200, 20);
		add(name);

		JLabel lblmonth = new JLabel("Month");
		lblmonth.setBounds(35, 200, 200, 20);
		add(lblmonth);

		cmonth = new Choice();
		cmonth.setBounds(300, 200, 200, 20);
		cmonth.add("January");
		cmonth.add("February");
		cmonth.add("March");
		cmonth.add("April");
		cmonth.add("May");
		cmonth.add("June");
		cmonth.add("July");
		cmonth.add("August");
		cmonth.add("September");
		cmonth.add("October");
		cmonth.add("November");
		cmonth.add("December");
		add(cmonth);

		JLabel lblunits = new JLabel("Units");
		lblunits.setBounds(35, 260, 200, 20);
		add(lblunits);

		JLabel labelunits = new JLabel("");
		labelunits.setBounds(300, 260, 200, 20);
		add(labelunits);

		JLabel lbltotalbill = new JLabel("Total Bill");
		lbltotalbill.setBounds(35, 320, 200, 20);
		add(lbltotalbill);

		JLabel labeltotalbill = new JLabel("");
		labeltotalbill.setBounds(300, 320, 200, 20);
		add(labeltotalbill);

		JLabel lblstatus = new JLabel("Status");
		lblstatus.setBounds(35, 380, 200, 20);
		add(lblstatus);

		JLabel labelstatus = new JLabel("");
		labelstatus.setBounds(300, 380, 200, 20);
		lblstatus.setForeground(Color.RED);
		add(labelstatus);

		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from customer WHERE meter_no = '" + meter + "'");
			while (rs.next()) {
				meternumber.setText(meter);
				name.setText(rs.getString("name"));
			}

			rs = c.s.executeQuery("select * from bill WHERE meter_no = '" + meter + "' and months = '"
					+ cmonth.getSelectedItem() + "'");
			while (rs.next()) {
				labelunits.setText(rs.getString("Units"));
				labeltotalbill.setText(rs.getString("totalbill"));
				labelstatus.setText(rs.getString("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		cmonth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				try {
					Conn c = new Conn();
					ResultSet rs = c.s.executeQuery("select * from bill WHERE meter_no = '" + meter + "' and months = '"
							+ cmonth.getSelectedItem() + "'");
					while (rs.next()) {
						labelunits.setText(rs.getString("Units"));
						labeltotalbill.setText(rs.getString("totalbill"));
						labelstatus.setText(rs.getString("status"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		pay = new JButton("Pay");
		pay.setBackground(Color.BLACK);
		pay.setForeground(Color.WHITE);
		pay.setBounds(100, 460, 100, 25);
		pay.addActionListener(this);
		add(pay);

		back = new JButton("Back");
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setBounds(230, 460, 100, 25);
		back.addActionListener(this);
		add(back);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bill.png"));
		Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(400, 120, 600, 300);
		add(image);

		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PayBill("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == pay) {
			try {
				Conn c = new Conn();
				c.s.executeUpdate("update bill set status ='Paid' WHERE meter_no = '" + meter + "' AND months = '"
						+ cmonth.getSelectedItem() + "' ");
			} catch (Exception ae) {
				ae.printStackTrace();
			}
			setVisible(false);
			new Paytm(meter);
		} else {
			setVisible(false);
		}

	}

}
