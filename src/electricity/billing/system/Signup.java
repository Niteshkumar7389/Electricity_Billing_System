package electricity.billing.system;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class Signup extends JFrame implements ActionListener{

	JButton create, back;
	Choice accountType;
	JTextField username, meter, name, password; 
	Signup(){
		setBounds(350,150, 700, 450);   //location 450 150 , size 700 400 of frame
		setVisible(true);
		getContentPane().setBackground(Color.white);
		setLayout(null);	
		
		
		JLabel heading = new JLabel("Create-Account-As");
		heading.setBounds(100,50,140,20);
		heading.setForeground(Color.GRAY);
		heading.setFont(new Font("tahoma", Font.BOLD, 14));
		add(heading);
		
		
		accountType = new Choice();
		accountType.add("Admin");
		accountType.add("Customer");
		accountType.setBounds(260, 50, 150, 20);
		add(accountType);
		
		
		JLabel lmeter = new JLabel("Meter Number");
		lmeter.setBounds(100,90,140,20);
		lmeter.setForeground(Color.GRAY);
		lmeter.setFont(new Font("tahoma", Font.BOLD, 14));
		add(lmeter);
		
		meter = new JTextField();
		meter.setBounds(260, 90, 150, 20);
		add(meter);
		
		JLabel lusername = new JLabel("Username");
		lusername.setBounds(100,130,140,20);
		lusername.setForeground(Color.GRAY);
		lusername.setFont(new Font("tahoma", Font.BOLD, 14));
		add(lusername);
		
		username = new JTextField();
		username.setBounds(260, 130, 150, 20);
		add(username);
		
		JLabel lname = new JLabel("Name");
		lname.setBounds(100,170,140,20);
		lname.setForeground(Color.GRAY);
		lname.setFont(new Font("tahoma", Font.BOLD, 14));
		add(lname);
		
		name = new JTextField();
		name.setBounds(260, 170, 150, 20);
		add(name);
		
		meter.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {}
			
			@Override
			public void focusLost(FocusEvent fe) {
				try {
					Conn c= new Conn();
					ResultSet rs = c.s.executeQuery("select * from login WHERE meter_no = '"+meter.getText()+"'");
					if(rs.next()) {
						name.setText(rs.getString("name"));
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});	
		
		
		JLabel lpassword = new JLabel("Password");
		lpassword.setBounds(100,210,140,20);
		lpassword.setForeground(Color.GRAY);
		lpassword.setFont(new Font("tahoma", Font.BOLD, 14));
		add(lpassword);
		
		password = new JTextField();
		password.setBounds(260, 210, 150, 20);
		add(password);
		
		create = new JButton("Create");
		create.setBounds(140, 260, 120, 30);
		create.addActionListener(this);
		add(create);
		
		back = new JButton("Back");
		back.setBounds(300, 260, 120, 30);
		back.addActionListener(this);
		add(back);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/signupimage.png"));
		Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image =new JLabel(i3);
		image.setBounds(420, 30, 250, 250);
		add(image);
		
	
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==create) {
			String atype = accountType.getSelectedItem(); // dropdown ki value nikalne ke liye getSelectedItem ka use
			String susername = username.getText();   // textfield se value nikalne ke liye getText ka use hota h
			String sname = name.getText();
			String spassword = password.getText();
			String smeter = meter.getText();
			
			try{
				Conn c = new Conn();
				String query = "insert into login values('"+smeter+"','"+susername+"', '"+sname+"', '"+spassword+"', '"+atype+"')";
				c.s.executeUpdate(query); //mysql me update ke liye c connection ke liye s statement ke liye
				//if query execute successfully then
				JOptionPane.showMessageDialog(null, "Account created successfully");
				
				setVisible(false);
				new Login();
			}catch (Exception ae) {
				ae.printStackTrace();
			}
			
			
			
			
			
		}else if(e.getSource()==back) {
			setVisible(false);
			new Login();
		}
	}
	
	
	
	
	public static void main(String[] args) {
		new Signup();

	}

}
