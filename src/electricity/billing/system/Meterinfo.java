package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Meterinfo extends JFrame implements ActionListener{

	JTextField tfname,tfaddress,tfstate,tfcity,tfemail,tfphone;
	JButton submit;
	JLabel lblmeter;
	Choice meterLocation,metertype,phasecode,billtype;
	String meternumber;
	
	
	 Meterinfo(String meternumber){
		 this.meternumber = meternumber;
		 
		setSize(700,500);
		setLocation(340,150);
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(173,216,230));
		add(p);
		
		JLabel heading = new JLabel("Meter Information");
		heading.setBounds(180,10,200,25);
		heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
		p.add(heading);
		
		JLabel lblname = new JLabel("Meter Number");
		lblname.setBounds(100,80,100,20);
		p.add(lblname);
		
		JLabel lblmeternumber = new JLabel(meternumber);
		lblmeternumber.setBounds(240,80,100,20);
		p.add(lblmeternumber);
		
		JLabel lblmeterno = new JLabel("Meter Location");
		lblmeterno.setBounds(100,120,100,20);
		p.add(lblmeterno);
		
		meterLocation = new Choice();
		meterLocation.add("Outside");
		meterLocation.add("Inside");
		meterLocation.setBounds(240,120,200,20);
		p.add(meterLocation);
		
		JLabel lbladdress = new JLabel("Meter Type");
		lbladdress.setBounds(100,160,100,20);
		p.add(lbladdress);
		
		metertype = new Choice();
		metertype.add("Electric Meter");
		metertype.add("Solar Meter");
		metertype.add("Smart Meter");
		metertype.setBounds(240,160,200,20);
		p.add(metertype);
		
		JLabel lblcity = new JLabel("PhaseCode");
		lblcity.setBounds(100,200,100,20);
		p.add(lblcity);
		
		phasecode = new Choice();
		phasecode.add("01");
		phasecode.add("02");
		phasecode.add("03");
		phasecode.add("04");
		phasecode.add("05");
		phasecode.add("06");
		phasecode.add("07");
		phasecode.add("08");
		phasecode.add("09");
		phasecode.setBounds(240,200,200,20);
		p.add(phasecode);
		
		
		JLabel lblstate = new JLabel("BillType");
		lblstate.setBounds(100,240,100,20);
		p.add(lblstate);
		
		billtype = new Choice();
		billtype.add("Normal");
		billtype.add("Commercial");
		billtype.setBounds(240,240,200,20);
		p.add(billtype);
		
		JLabel lblDays = new JLabel("Days");
		lblDays.setBounds(100,280,100,20);
		p.add(lblDays);
		
		JLabel lblemail = new JLabel("30 Days");
		lblemail.setBounds(240,280,100,20);
		p.add(lblemail);
		
		JLabel lblnote = new JLabel("Note");
		lblnote.setBounds(100,320,100,20);
		p.add(lblnote);
		
		JLabel lblnotes = new JLabel("By Default Bill is Generated for 30 Days Only");
		lblnotes.setBounds(240,320,500,20);
		p.add(lblnotes);
		
		submit = new JButton("Submit");
		submit.setBounds(220,390,100,25);	
		submit.setBackground(Color.black);
		submit.setForeground(Color.WHITE);
		submit.addActionListener(this);
		p.add(submit);
		
		setLayout(new BorderLayout());  // for center all details
		add(p, "Center");
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/hicon1.jpg"));
		Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		add(image, "West");   // border layout me aise dete h
		
		getContentPane().setBackground(Color.WHITE);
		
		
		
		
		
		
		
		
		
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Meterinfo("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==submit) {
			String meter = meternumber;   //for fetch user info
			String location = meterLocation.getSelectedItem();
			String type = metertype.getSelectedItem();
			String code = phasecode.getSelectedItem();
			String typebill = billtype.getSelectedItem();
			String days = "30";
			
			String query = "insert into meter_info values('"+meter+"','"+location+"','"+type+"','"+code+"','"+typebill+"' ,'"+days+"')";			
			try {
				Conn c = new Conn();
				c.s.executeUpdate(query);
				
				
				JOptionPane.showMessageDialog(null,"Meter Information Added Successfully");
				setVisible(false);
				
			}catch(Exception ae){
				ae.printStackTrace();
			}
		}else {
			setVisible(false);
		}
	}

}
