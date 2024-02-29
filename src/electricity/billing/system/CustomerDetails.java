package electricity.billing.system;

import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class CustomerDetails extends JFrame implements ActionListener {

	JButton search,print;
	Choice meternumber, cmonth;
	JTable table;

	CustomerDetails() {
		super("Customer Details"); // frame title
		setSize(1000, 600);
		setLocation(170, 150);

		table = new JTable();
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			e.printStackTrace();
		}

		JScrollPane sp = new JScrollPane(table);
		add(sp);

		print = new JButton("Print");
		print.addActionListener(this);
		add(print,"South");

		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CustomerDetails();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
			try {
				table.print();     // for print the table
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	

}
