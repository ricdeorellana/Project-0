package dev.ric.daos;

import java.util.List;

import dev.ric.entities.Account;
/*
 * The user should be able to create new accounts, update things such as their 
 * account names, view their accounts, and delete their accounts if their balances
 * are at 0.
 */
public interface AccountDAO {
// Create
	public boolean createAccount(Account account);
// Read
	public Account getAccountByAccountID(int id);
	
	public List<Account> getAllAccountByID(int id);
// Update
	public boolean updateAccountBal(Account change);
//Delete
	public boolean deleteAccount(int id);
}
