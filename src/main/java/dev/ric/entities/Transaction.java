package dev.ric.entities;

public class Transaction {
private int accountId;
private int transNum;
private double changeBal;
private double finBal;
private double withdrawed = 0;
private double deposited = 0;

public Transaction() {
	super();
}
public Transaction(int accountId, int transNum, double changeBal, double finBal) {
	super();
	this.accountId = accountId;
	this.transNum = transNum;
	this.changeBal = changeBal;
	this.finBal = finBal;
}


public Transaction(int accountId, double changeBal, double finBal) {
	super();
	this.accountId = accountId;
	this.changeBal = changeBal;
	this.finBal = finBal;
}


public Transaction(int accountId, double changeBal) {
	super();
	this.accountId = accountId;
	this.changeBal = changeBal;
}
public int getAccountId() {
	return accountId;
}
public void setAccountId(int accountId) {
	this.accountId = accountId;
}
public int getTransNum() {
	return transNum;
}
public void setTransNum(int transNum) {
	this.transNum = transNum;
}
public double getChangeBal() {
	return changeBal;
}
public void setChangeBal(double changeBal) {
	this.changeBal = changeBal;
}
public double getFinBal() {
	return finBal;
}
public void setFinBal(double finBal) {
	this.finBal = finBal;
}



public double getWithdrawed() {
	return withdrawed;
}
public void setWithdrawed(double withdrawed) {
	this.withdrawed = withdrawed;
}
public double getDeposited() {
	return deposited;
}
public void setDeposited(double deposited) {
	this.deposited = deposited;
}
@Override
public String toString() {
	return "Transaction [accountId=" + accountId + ", transNum=" + transNum + ", changeBal=" + changeBal + ", finBal="
			+ finBal + ", withdrawed=" + withdrawed + ", deposited=" + deposited + "]";
}






}
