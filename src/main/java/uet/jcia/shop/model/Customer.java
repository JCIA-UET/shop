package uet.jcia.shop.model;

public class Customer extends Account {
	
	public Customer(String username, String password, int accountId, String email, String accoutType) {
		super(username, password, accountId, email, CUSTOMER);
		
	}
	
}
