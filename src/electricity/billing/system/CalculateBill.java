package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class CalculateBill extends JFrame implements ActionListener {

	JTextField tfname, tfaddress, tfunits;
	JButton submit, cancel;
	JLabel lblnames, lbladdress;
	Choice meternumber, cmonth;

	CalculateBill() {
		setSize(700, 500);
		setLocation(340, 150);

		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(173, 216, 230));
		add(p);

		JLabel heading = new JLabel("Calculate Electricity Bill");
		heading.setBounds(100, 10, 400, 25);
		heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
		p.add(heading);

		JLabel lblmno = new JLabel("Meter Number");
		lblmno.setBounds(100, 80, 100, 20);
		p.add(lblmno);

		meternumber = new Choice();
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			while (rs.next()) {
				meternumber.add(rs.getString("meter_no")); // all row stored in rs so we need only meter no
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		meternumber.setBounds(240, 80, 200, 20);
		p.add(meternumber);
		;
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(100, 120, 100, 20);
		p.add(lblname);

		lblnames = new JLabel("");
		lblnames.setBounds(240, 120, 100, 20);
		p.add(lblnames);

		JLabel lbladdres = new JLabel("Address");
		lbladdres.setBounds(100, 160, 100, 20);
		p.add(lbladdres);

		lbladdress = new JLabel();
		lbladdress.setBounds(240, 160, 200, 20);
		p.add(lbladdress);

		try {
			Conn c = new Conn();
			ResultSet rs = c.s
					.executeQuery("select * from customer where meter_no='" + meternumber.getSelectedItem() + "'");
			while (rs.next()) {
				lblnames.setText(rs.getString("name"));
				lbladdress.setText(rs.getString("address"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		meternumber.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				try {
					Conn c = new Conn();
					ResultSet rs = c.s.executeQuery(
							"select * from customer where meter_no='" + meternumber.getSelectedItem() + "'");
					while (rs.next()) {
						lblnames.setText(rs.getString("name"));
						lbladdress.setText(rs.getString("address"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		JLabel lblunits = new JLabel("Units Consumed");
		lblunits.setBounds(100, 200, 100, 20);
		p.add(lblunits);

		tfunits = new JTextField();
		tfunits.setBounds(240, 200, 200, 20);
		p.add(tfunits);

		JLabel lblmonth = new JLabel("Month");
		lblmonth.setBounds(100, 240, 100, 20);
		p.add(lblmonth);

		cmonth = new Choice();
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
		cmonth.setBounds(240, 240, 200, 20);
		p.add(cmonth);

		submit = new JButton("Submit");
		submit.setBounds(120, 350, 100, 25);
		submit.setBackground(Color.black);
		submit.setForeground(Color.WHITE);
		submit.addActionListener(this);
		p.add(submit);

		cancel = new JButton("Cancel");
		cancel.setBounds(250, 350, 100, 25);
		cancel.setBackground(Color.black);
		cancel.setForeground(Color.WHITE);
		cancel.addActionListener(this);
		p.add(cancel);

		setLayout(new BorderLayout()); // for center all details
		add(p, "Center");

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/hicon2.jpg"));
		Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		add(image, "West"); // border layout me aise dete h

		getContentPane().setBackground(Color.WHITE);

		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CalculateBill();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == submit) {
			String meter = meternumber.getSelectedItem(); // for fetch meter info
			String units = tfunits.getText();
			String month = cmonth.getSelectedItem();

			int totalbill = 0;
			int unit_consumed = Integer.parseInt(units);
			String query = "select * from tax";

			try {
				Conn c = new Conn();
				ResultSet rs = c.s.executeQuery(query);
				while (rs.next()) {
					totalbill += unit_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
					totalbill += Integer.parseInt(rs.getString("meter_rent"));
					totalbill += Integer.parseInt(rs.getString("service_charge"));
					totalbill += Integer.parseInt(rs.getString("service_tax"));
					totalbill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
					totalbill += Integer.parseInt(rs.getString("fixed_tax"));
				}

			} catch (Exception ae) {
				ae.printStackTrace();
			}
			String query2 = "insert into bill values('"+meter+"','"+month+"','"+units+"','"+totalbill+"','Not paid')";
			
			try {
				Conn c = new Conn();
				c.s.executeUpdate(query2);
				
				JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
				setVisible(false);
			}catch(Exception E) {
				E.printStackTrace();
			}
			
		} else {
			setVisible(false);
		}
	}

}
