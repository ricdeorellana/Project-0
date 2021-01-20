package dev.ric.services;

import java.util.List;

import dev.ric.daos.AccountDAO;
import dev.ric.daos.AccountDAOimpl;
import dev.ric.entities.Account;

public class AccountServicesimpl implements AccountServices {
	private static AccountDAO accd = new AccountDAOimpl();

	@Override
	public boolean createAccount(String accountName, int id) {
		Account account = new Account(accountName, id);

		return accd.createAccount(account);
	}

	@Override
	public boolean deleteAccount(int id) {
		return accd.deleteAccount(id);
	}

	@Override
	public Account getAccountByAccountID(int id) {
		return accd.getAccountByAccountID(id);
	}

	@Override
	public List<Account> getAllAccountByID(int id) {
		return accd.getAllAccountByID(id);
	}

	@Override
	public boolean updateAccountBal(Account change) {
		
		return accd.updateAccountBal(change);
	}

}
