package dev.ric.entities;

public class Account {

	private int accountID;
	private String accountName;
	private double balance;
	private int id;

	
	
	public Account() {
		super();
	}
	public Account(String accountName, int id) {
		super();
		this.accountName = accountName;
		this.id = id;
		this.balance = 0;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", accountName=" + accountName + ", balance=" + balance + ", id="
				+ id + "]";
	}
	
	
	


}
