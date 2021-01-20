package dev.ric.services;

import java.util.List;

import dev.ric.entities.Account;

public interface AccountServices {

	boolean createAccount(String accountName, int id);

	boolean deleteAccount(int id);

	public Account getAccountByAccountID(int id);

	public List<Account> getAllAccountByID(int id);

	public boolean updateAccountBal(Account change);
}
