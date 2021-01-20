package dev.ric.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;



public class JDBCConnection {

	/*
	 * We are going to maintain and observe a single Connection Object within this class
	 * If no connection exists, we will create one and return it.
	 * If a connection does exists, we will return it.
	 */
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		try {
			if(conn == null) {
				//make a new connection
				
				/*
				 * Oracle added a hotfix to ensure that ojdbc drivers would
				 * correctly load at the beginning of your application starting.
				 * This line of code ensures that this driver is getting called. 
				 */
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//To establish a connection we need 3 credentials
				// URL (endpoint), username, password
				Properties props = new Properties();
				FileInputStream input = new FileInputStream(JDBCConnection.class.getClassLoader().getResource("connection.properties").getFile());
				
				props.load(input);
				
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				//Establish our connection
				conn = DriverManager.getConnection(url, username, password);
				return conn;
			}
			else {
				//return the connection that already exists
				return conn;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		
		Connection conn1 = getConnection();
		System.out.println(conn1);
	}
}
