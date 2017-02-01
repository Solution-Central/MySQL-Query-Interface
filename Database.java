import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Database extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	static JTextArea query = new JTextArea();
	JLabel prompt = new JLabel("Enter Your Query Below...");
	JButton exec = new JButton("Execute");
	JButton reset = new JButton("Reset");
	static JTextArea queryStatus = null;
	static JTable table = new JTable();
	static DefaultTableModel model = (DefaultTableModel) table.getModel();
	static Connector dc = null;
	
	public Database(Connector conn){
		add(prompt);
		dc = conn;
		
		query.setFont(new Font("Verdana", Font.PLAIN, 12));
		JScrollPane queryArea = new JScrollPane(query);
		
		int x = (int) (Main.frame.getWidth()*.95);
		int y = (int) (Main.frame.getHeight()*.3);
		
		queryArea.setPreferredSize(new Dimension(x, y));
		add(queryArea);
		
		exec.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				execute();
			}
			
		});
		
		reset.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.setColumnCount(0);
				model.setRowCount(0);
			}
			
		});
		add(exec);
		add(reset);
		
		JScrollPane result = new JScrollPane(table);
		result.setPreferredSize(new Dimension(x, (int) (y*1.5)));
		add(result);
		
		queryStatus = new JTextArea("Click EXECUTE to run Query...");
		queryStatus.setEditable(false);
		queryStatus.setFont(new Font("Times New Roman", Font.BOLD, 12));
		add(queryStatus);

	}
	
	private static void execute(){
		model.setColumnCount(0);
		model.setRowCount(0);
		String q = query.getText();
		
		try{
			if(q.length() >= 4 && (q.substring(0, 6).equalsIgnoreCase("SELECT") || q.substring(0, 4).equalsIgnoreCase("SHOW"))){
				ResultSet rs = dc.executeQuery(q);
				ResultSetMetaData rsmd = rs.getMetaData();
				
				for(int i = 1; i <= rsmd.getColumnCount(); ++i){
					model.addColumn(rsmd.getColumnName(i));
				}
				
				while(rs.next()){
					String[] data = new String[rsmd.getColumnCount()];
					
					for(int i = 1; i <= rsmd.getColumnCount(); ++i){
						data[i-1] = rs.getString(i);
					}
					
					model.addRow(data);
				}
				
				queryStatus.setText("Query Execution Successful");
			}
			else{
				int rows = dc.execute(q);
				queryStatus.setText("Query Execution Successful! Number of Rows affected: " +rows);
			}
		} catch(SQLException e){
			queryStatus.setText(e.toString());
		}
	}
}
