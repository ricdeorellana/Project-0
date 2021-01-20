package dev.ric.services;

import java.util.List;

import dev.ric.daos.TransactionDAO;
import dev.ric.daos.TransactionDAOimpl;
import dev.ric.entities.Transaction;

public class TransactionServicesimpl implements TransactionServices {
	private static TransactionDAO transd = new TransactionDAOimpl();

	@Override
	public boolean withdrawTransaction(Transaction transaction) {
		System.out.println("You have withdrawn " + transaction.getChangeBal());
		System.out.println("Your ending balance is " + transaction.getFinBal());
		return transd.withdrawTransaction(transaction);
	}

	@Override
	public boolean depositTransaction(Transaction transaction) {
		System.out.println("You have deposited " + transaction.getChangeBal());
		System.out.println("Your ending balance is " + transaction.getFinBal());
		return transd.depositTransaction(transaction);
	}

	@Override
	public List<Transaction> getUserTransaction(int id) {
		return transd.getUserTransaction(id);
	}


}
