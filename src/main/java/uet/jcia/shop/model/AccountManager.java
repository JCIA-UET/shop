package uet.jcia.shop.model;

import java.sql.*;

import javax.naming.spi.DirStateFactory.Result;

public class AccountManager {
	
	DBConnector  dbconnector = DBConnector.getInstance();
	private String table = "account";
	
	private ResultSet getAccount(String username, String password){
		ResultSet rs = null ;
		String sqlcommand = "select account.accountType from "+table+" where account.username = ? AND account.password = ? ;";
		PreparedStatement pts = null;
		try {
			pts = dbconnector.getConnection().prepareStatement(sqlcommand);
			pts.setString(1, username);
			pts.setString(2, password);
			rs = pts.executeQuery();
			return rs;
		} catch (Exception e) {
			System.out.println("the Query ERRO!!   " + e.toString());
		}
		return null;
	}
	
	public String authenticate(String username , String password){
		ResultSet rs = this.getAccount(username, password);
		try {
			String typeOfAccount = null;
			while(rs.next()){
				typeOfAccount = rs.getString(1);
			}
			return typeOfAccount;
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
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
	
	public static void main(String[] args) {
		AccountManager test = new AccountManager();
		System.out.println(test.authenticate("hieu", "cuong"));
		Account a = new Customer("hieusonson","hieusonson",12,"hfeufhsf");
		test.insertAccount(a);
	}
	
}
