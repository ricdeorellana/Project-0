package dev.ric.services;

import java.util.List;

import dev.ric.entities.Transaction;

public interface TransactionServices {
	//Create
	boolean withdrawTransaction(Transaction transaction);
	
	boolean depositTransaction(Transaction transaction);
	//Read
	List<Transaction> getUserTransaction(int id);


}
