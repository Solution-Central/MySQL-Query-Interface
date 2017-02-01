import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main{
	
	static JFrame frame = null;
	static String stat = "Enter Login Credentials!!";

	public static void main(String[] args) {
		
		frame = new JFrame("Database Query Interface");
		
		boolean flag = true;
		Color col = Color.BLACK;
		
		Connector conn = null;
		
		do{
			Properties props = new Properties();
			ConnectDialog dialog = new ConnectDialog(frame, "Database Connection", props, col);
			dialog.setVisible(true);
			try{
				dialog.setUndecorated(flag);
			} catch(Exception e){
				System.exit(0);
			}
						
			conn = new Connector(dialog.getProps(), new String(dialog.pass.getPassword()));
			
			if(!conn.open()){
				col = Color.RED;
			}
			else	flag = false;
		}while(flag);
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
		
		Database panel = new Database(conn);
		frame.add(panel);

		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		menu.add(file);
		
		FileMenu fm = new FileMenu();
		JMenuItem load = new JMenuItem("Open");
		load.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				fm.openFile(frame);
			}
		});
		
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				fm.saveFile(frame);
			}
		});
		
		final Connector c = conn;
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				c.closeConnection();
				System.exit(0);
			}
		});
		
		file.add(load);
		file.addSeparator();
		file.add(save);
		file.addSeparator();
		file.add(exit);
		
		frame.setJMenuBar(menu);

		frame.setVisible(true);
		frame.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				c.closeConnection();
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
				
	}
}
