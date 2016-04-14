package uet.jcia.shop.model;

public class AccountManager {
	
	DBConnector  dbconnector = DBConnector.getInstance();
	
	public boolean authenticate(String username , String password){
		
		return true;
	}
	
	public String getAccountType(){
		return null;
	}
	
	
	
}
