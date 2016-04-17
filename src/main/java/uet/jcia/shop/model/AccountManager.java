package uet.jcia.shop.model;

import java.sql.*;

import javax.naming.spi.DirStateFactory.Result;

public class AccountManager {
	
	DBConnector  dbconnector = DBConnector.getInstance();
	private String table = "account";
	
	public Account getAccountByUsername(String username){
		ResultSet rs = null ;
		String sqlcommand = "select * from "+table+" where account.username = ?;";
		PreparedStatement pts = null;
		Account account = null;
		try {
			Connection con = dbconnector.createConnection();
			pts = con.prepareStatement(sqlcommand);
			pts.setString(1, username);
			rs = pts.executeQuery();
			
			if (rs.next()) {
				int accountId = rs.getInt(1);
				String password = rs.getString(3);
				String email = rs.getString(4);
				String accountType = rs.getString(5);
				
				account = new Account(
						username, password, accountId,
						email, accountType);
			}
			
			con.close();
			return account;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean authenticate(String username , String password){
		Account account = getAccountByUsername(username);
		if (account == null ||
			!account.getPassword().equals(password)) {
			return false;
		}
		return true;
	}
	
	public void insertAccount(Account a){
		String sqlCommand = "insert into " + table + " value(?,?,?,?,?) ;";
		PreparedStatement pst = null;
		
		try {
			pst = dbconnector.getConnection().prepareStatement(sqlCommand);
			pst.setInt(1, a.getAccountId() );
			pst.setString(2,a.getUsername() );
			pst.setString(3,a.getPassword() );
			pst.setString(4, a.getEmail());
			pst.setString(5,a.getAccoutType());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insert fail!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}
		
	}
	
//	public static void main(String[] args) {
//		AccountManager test = new AccountManager();
//		System.out.println(test.authenticate("hieu", "cuong"));
//		Account a = new Customer("hieusonson","hieusonson",12,"hfeufhsf");
//		test.insertAccount(a);
//	}
	
}
