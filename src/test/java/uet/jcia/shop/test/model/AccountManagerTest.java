package uet.jcia.shop.test.model;

import uet.jcia.shop.model.Account;
import uet.jcia.shop.model.AccountManager;
import uet.jcia.shop.model.Customer;

public class AccountManagerTest {
	public static void main(String[] args) {
		AccountManager test = new AccountManager();
		System.out.println(test.authenticate("bqc", "12345"));
//		Account a = new Customer("hieusonson","hieusonson",12,"hfeufhsf");
//		test.insertAccount(a);
	}
}
