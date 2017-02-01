import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.*;

public class ConnectDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private String status = Main.stat;
		
	JLabel hostName = new JLabel("Host Name");
	JTextField host = new JTextField();
	JLabel portNo = new JLabel("Port Number");
	JTextField port = new JTextField();
	JLabel dbname = new JLabel("Database");
	JTextField database = new JTextField();
	JLabel username = new JLabel("Username");
	JTextField user = new JTextField();
	JLabel password = new JLabel("Password");
	JPasswordField pass = new JPasswordField();
	JLabel state = new JLabel(status);
	
	JButton ok = new JButton("Okay");
	JButton cancel = new JButton("Cancel");
	
	Properties props = null;
	
	public ConnectDialog(JFrame owner, String title, Properties p, Color col){
		super(owner, title, true);
		setSize(400, 250);
		setLocation(450, 150);
		
		state.setForeground(col);
		
		props = new Properties(p);
		ok.setPreferredSize(new Dimension(75,25));
		ok.addActionListener(this);
		
		cancel.setPreferredSize(new Dimension(75,25));
		cancel.addActionListener(this);
		
		JPanel cpanel1 = new JPanel();		// Login Details
		JPanel cpanel2 = new JPanel();		// Button
		JPanel cpanel3 = new JPanel();		// Login Label
		
		cpanel3.add(state);
		
		cpanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		cpanel1.setLayout(new GridLayout(5,2));
		cpanel1.add(hostName);
		cpanel1.add(host);
		cpanel1.add(portNo);
		cpanel1.add(port);
		cpanel1.add(dbname);
		cpanel1.add(database);
		cpanel1.add(username);
		cpanel1.add(user);
		cpanel1.add(password);
		cpanel1.add(pass);
		
		cpanel2.add(ok);
		cpanel2.add(cancel);
		
		add(cpanel1, BorderLayout.NORTH);
		add(cpanel2, BorderLayout.SOUTH);
		add(cpanel3, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == cancel)
			System.exit(0);
		this.dispose();
		
	}
	
	public Properties getProps(){
		props.setProperty("Database", database.getText());
		props.setProperty("Host name", host.getText());
		props.setProperty("Port", port.getText());
		props.setProperty("Username", user.getText());
		
		return props;
	}
	
}
