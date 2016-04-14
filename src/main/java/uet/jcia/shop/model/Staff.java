package uet.jcia.shop.model;

public class Staff  extends Account{

	public Staff(String username, String password, int accountId, String email, String accoutType) {
		super(username, password, accountId, email, STAFF);
	}
}
