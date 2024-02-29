package electricity.billing.system;

import java.awt.*;
import java.awt.Color;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class DepositeDetails extends JFrame implements ActionListener {

	JButton search, print;
	Choice meternumber, cmonth;
	JTable table;

	DepositeDetails() {
		super("Deposite Details"); // frame title
		setSize(700, 700);
		setLocation(400, 100);
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		JLabel lblmeternumber = new JLabel("Search By Meter Number");
		lblmeternumber.setBounds(20, 20, 150, 20);
		add(lblmeternumber);

		meternumber = new Choice();
		meternumber.setBounds(180, 20, 150, 20);
		add(meternumber);

		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			while (rs.next()) {
				meternumber.add(rs.getString("meter_no"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLabel lblmonth = new JLabel("Search By Month");
		lblmonth.setBounds(360, 20, 120, 20);
		add(lblmonth);

		cmonth = new Choice();
		cmonth.setBounds(480, 20, 150, 20);
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

		table = new JTable();
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from bill");
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			e.printStackTrace();
		}

		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0, 100, 700, 600);
		add(sp);

		search = new JButton("Search");
		search.setBounds(20, 70, 80, 20);
		search.addActionListener(this);
		add(search);

		print = new JButton("Print");
		print.setBounds(120, 70, 80, 20);
		print.addActionListener(this);
		add(print);

		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DepositeDetails();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == search) {
			String query = "(select * from bill WHERE meter_no ='"+meternumber.getSelectedItem()+"'and months = '"+cmonth.getSelectedItem()+"')";
			
			try {
				Conn c = new Conn();
				ResultSet rs = c.s.executeQuery(query);
				table.setModel(DbUtils.resultSetToTableModel(rs));
			}catch(Exception e) {
				//e.printStackTrace();
			}
			
			
			}else {
			try {
				table.print();     // for print the table
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
