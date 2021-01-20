package dev.ric.daos;

import java.util.List;

import dev.ric.entities.Transaction;

/*
 * The following is the DAO for the transaction. There will be no need to update/delete 
 * transactions since they are tied to accounts which can be deleted. The getAllTransactions
 * option exists for admins only.
 */
public interface TransactionDAO {
	//Create
	boolean withdrawTransaction(Transaction transaction);
	
	boolean depositTransaction(Transaction transaction);
	//Read
	List<Transaction> getUserTransaction(int id);


	
}
