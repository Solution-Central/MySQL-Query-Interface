import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import java.sql.Connection;
import java.sql.Statement;

public class Connector {
	Connection conn = null;
	Statement stat = null;
	static String url, database, username, password, hostname, port, driver;
	
	public Connector(Properties props, String pass){
		database = props.getProperty("Database");
		username = props.getProperty("Username");
		password = pass;
		hostname = props.getProperty("Host name");
		port = props.getProperty("Port");
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://"+ hostname +":"+ port +"/"+ database +"?useSSL=false";
	}
	
	public boolean open(){
		try {
			DriverManager.registerDriver((java.sql.Driver) Class.forName(driver).newInstance());
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
		} catch(CommunicationsException ce){
			Main.stat = "Server Communication Failed!";
			return false;
		} catch(Exception e){
			Main.stat = "Login Failed! Try again with correct Credentials";
			return false;
		}
		
		return true;
	}
	
	public ResultSet executeQuery(String s) throws SQLException{
		return stat.executeQuery(s);
	}
	
	public int execute(String s) throws SQLException{
		return stat.executeUpdate(s);
	}
	
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
