package uet.jcia.shop.model;

import java.sql.*;

public class DBConnector {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	private static final String DB_URL = "jdbc:mysql://localhost:3306/shop?useSSL=false";
	private static final String USERNAME = "root"; 
	private static final String PASSWORD = "26031812";
	private static Connection con = null;
	private static DBConnector connector = new DBConnector();
	
	private DBConnector() {
				
	}
	
	public static DBConnector getInstance() { return connector; }
	
	public Connection createConnection() {
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			return con;
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Connection getConnection() { return con; }
	
	

}
