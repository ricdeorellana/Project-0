package dev.ric.daotest;

import org.junit.jupiter.api.Test;

import dev.ric.daos.AccountDAO;
import dev.ric.daos.AccountDAOimpl;
import dev.ric.entities.Account;
import dev.ric.entities.User;

class AccountDAOTest {
	private static AccountDAO adao = new AccountDAOimpl();
	@Test
	void test() {
		Account u = new Account("admin", 2);
		System.out.println(u);
		Account u2 = new Account("admin", 2);
		adao.createAccount(u);
		adao.createAccount(u2);
		System.out.println(u);
		System.out.println(u2);
		
//		adao.getAllAccountByID(u.getId());
	}
	
	@Test
	void updateTest(){
		Account a = adao.getAccountByAccountID(26);
		System.out.println(a);
		a.setBalance(500);
		System.out.println(adao.updateAccountBal(a));
		
		
		
		
	}

}
