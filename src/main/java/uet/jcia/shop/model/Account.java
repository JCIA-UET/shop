package uet.jcia.shop.model;

public class Account {
	public static final String CUSTOMER = "customer";
	public static final String STAFF = "staff";
	
	private String username;
	private String password;
	private int accountId;
	private String email;
	private String accoutType;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccoutType() {
		return accoutType;
	}
	public void setAccoutType(String accoutType) {
		this.accoutType = accoutType;
	}
	public Account(String username, String password, int accountId, String email, String accoutType) {
		super();
		this.username = username;
		this.password = password;
		this.accountId = accountId;
		this.email = email;
		this.accoutType = accoutType;
	}
	
}
