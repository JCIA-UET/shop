package uet.jcia.shop.model;

import java.sql.*;

public class DBConnector {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/shop";
	private static final String USERNAME = "root"; 
	private static final String PASSWORD = "26031812";
	private static Connection con = null;
	private static DBConnector connector = new DBConnector();
	
	private DBConnector() {
		
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (InstantiationException e) {
			e.printStackTrace();
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static DBConnector getInstance() { return connector; }
	
	public Connection getConnection() { return con; }
	
	

}
