package dev.ric.app;

import java.util.Scanner;

import dev.ric.entities.Account;
import dev.ric.entities.Transaction;
import dev.ric.entities.User;
import dev.ric.services.AccountServices;
import dev.ric.services.AccountServicesimpl;
import dev.ric.services.TransactionServices;
import dev.ric.services.TransactionServicesimpl;
import dev.ric.services.UserServices;
import dev.ric.services.UserServicesImpl;

public class Main {
	private static Scanner scan = new Scanner(System.in);
	private static User loggedinUser = null;
	private static UserServices userv = new UserServicesImpl();
	private static AccountServices accs = new AccountServicesimpl();
	private static TransactionServices trans = new TransactionServicesimpl();

	public static void main(String[] args) {
		while (true) {
			while (true) {
				int a = firstPrompt();

				if (a == 1) { // This will log in the user
					loggedinUser = login();
					if(loggedinUser == null) {
						System.out.println("User does not exist. Create an account first to login.");
						continue;
					}
					break;
				}
				if (a == 2) {
					newUser();
				}
				if (a == 3) {
					exit();
				}
			}

			System.out.println("Welcome " + loggedinUser.getUsername());

			// This portion is for dealing with a user who has an account
			if (loggedinUser.isSuperUser()) {
				superMenu();
			} else {
				menu();
			}
		}
	}

	static int firstPrompt() {
		System.out.println("Welcome to FinBank. What would you like to do? 1. Login 2. Sign Up 3. Exit");
		int answer = scan.nextInt();
		return answer;
	}

	static void newUser() {
		boolean admin = false;
		try {
			System.out.println("Enter a new username"); // This is the username creation
			String name = scan.next();

			System.out.println("Enter a password"); // This is the password creation
			String password = scan.next();

			System.out.println("Are you an admin?\n1.Yes\n2. No");
			int answer = scan.nextInt();
			if (answer == 1) {
				admin = true;
				userv.createUser(name, password, admin);
			} else
				userv.createUser(name, password, admin);

		} catch (Exception e) {

		}
	}

	static void newUserByAdmin() {
		boolean admin = false;
		try {
			System.out.println("Enter a new username"); // This is the username creation
			String name = scan.next();

			System.out.println("Enter a password"); // This is the password creation
			String password = scan.next();

			System.out.println("Will they be an admin?\n1. Yes\n2. No");
			int answer = scan.nextInt();
			if (answer == 1) {
				admin = true;
				userv.createUser(name, password, admin);
			} else
				userv.createUser(name, password, admin);

			System.out.println("User Created Successfully");
		} catch (Exception e) {

		}
	}

	static User login() {
		System.out.println("Enter username.");
		String username = scan.next();

		System.out.println("Enter password.");
		String password = scan.next();

		User currentUser = userv.login(username, password);
		return currentUser;
	}

	static void accountManagement() {
		boolean check = true;
		int tChoice;
		int finalNum;
		double withdraw;
		double deposit;
		int accToTrans;
		
		System.out.println("Which account would you like to deposit/withdraw from? Enter Account Id");
		for(Account accounts : accs.getAllAccountByID(loggedinUser.getId()) ) {
			System.out.println(accounts);
		}
		accToTrans = scan.nextInt();
		Account a = accs.getAccountByAccountID(accToTrans);
		
		while (check) {
			System.out.println("What would you like to do with your " + a.getAccountName()
					+ " account?\n1. Withdraw\n2. Deposit\n3. Go Back to Accounts");
			tChoice = scan.nextInt();
			
			switch(tChoice) {
		
			case 1:
				System.out.println("How much would you like to withdraw? Your current balance is " + a.getBalance());
				System.out.println("Enter amount:");
				withdraw = scan.nextDouble();
				a.setBalance(a.getBalance() - withdraw);
				Transaction transaction = new Transaction(a.getAccountID(), withdraw, a.getBalance());
				trans.withdrawTransaction(transaction);
				accs.updateAccountBal(a);
				break;
				
			case 2:
				System.out.println("How much would you like to deposit? Your current balance is " + a.getBalance());
				System.out.println("Enter amount:");
				deposit = scan.nextDouble();
				a.setBalance(a.getBalance() + deposit);
				Transaction transactionD = new Transaction(a.getAccountID(), deposit, a.getBalance());
				trans.depositTransaction(transactionD);
				accs.updateAccountBal(a);
				
				break;
				
			case 3:
				System.out.println("Going back to your Accounts");
				check = false;
				break;
			}
			System.out.println("Would you like to make another withdrawal/deposit?\n1. Yes\n2. No");
			
			finalNum = scan.nextInt();
			if(finalNum == 2) {
				check = false;
			}
		}
	}

	static void exit() {
		System.out.println("Are you sure you want to exit? 1. Yes 2. No");
		int input = scan.nextInt();
		if (input != 1) {
			System.out.println("Returning back to previous menu.");
			return;
		} else {
			System.out.println("Goodbye!");
			System.exit(input);
		}

	}

	static void menu() {
		boolean loop = true;

		while (loop) {
			String accName = null;
			int accToDel;
			int choice;
			int transCheck;
			
			System.out.println(
					"What would you like to do?\n1. Create Account\n2. Check Accounts\n3. Withdraw/Deposit\n4. Delete Accounts\n5. See Transaction History\n6. Logout");
			choice = scan.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter a name for your new account");
				accName = scan.next();
				accs.createAccount(accName, loggedinUser.getId());
				break;

			case 2:
				if (!accs.getAllAccountByID(loggedinUser.getId()).isEmpty()) {
					System.out.println("These are your accounts.");
					for(Account accounts : accs.getAllAccountByID(loggedinUser.getId()) ) {
						System.out.println(accounts);
					}
				
				} else
					System.out.println("You have no accounts. Create one first to be able to view them.");
				break;

			case 3:
				accountManagement();
				break;

			case 4:
				System.out.println("What account would you like to delete? Enter the Account Id");
				for(Account accounts : accs.getAllAccountByID(loggedinUser.getId()) ) {
					System.out.println(accounts);
				}
				accToDel = scan.nextInt();
				
				Account delTest = accs.getAccountByAccountID(accToDel);
				if(delTest.getBalance() ==0) {
				accs.deleteAccount(accToDel);
				}
				else System.out.println("You cannot delete an account with a balance.");
				break;
			case 5:
				System.out.println("Which account transaction history would you like to see?");
				
				for(Account accounts : accs.getAllAccountByID(loggedinUser.getId()) ) {
					System.out.println(accounts);
				}
				transCheck = scan.nextInt();
				System.out.println("Transactions:");
				if(!trans.getUserTransaction(transCheck).isEmpty()) {
				for(Transaction transactions : trans.getUserTransaction(transCheck)) {
					System.out.println(transactions);
				}}
				else System.out.println("Account Transaction History is Empty");
				break;
				
				
			case 6:
				loop = false;
				logout();
				break;
			}
		}
	}

	static void superMenu() {
		boolean loop = true;
		while (loop) {
			int userId = 0;
			int userIdDelete = 0;
			int choice;
			String userN = null;
			String passW = null;
			boolean admin = false;
			System.out.println(
					"What would you like to do?\n1. Create User\n2. See  Users\n3. Delete User\n4. Update User\n5. Logout");
			choice = scan.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Creating a new User");
				newUserByAdmin();
			case 2:
				System.out.println("Users:");
				for(int i = 1; i-1 < userv.getAllUsers().size(); i++)
				{
					System.out.println( i + ". " + userv.getAllUsers().get(i-1));
				}
				break;

			case 3:
				System.out.println("What User would you like to Delete? Enter their ID.");
				for(User users : userv.getAllUsers()) {
					System.out.println(users);
				}
				userId = scan.nextInt();
				userv.deleteUser(userId);
				break;
			case 4:

				System.out.println("What User would you like to update? Enter their ID");
				
				for(User users : userv.getAllUsers()) {
					System.out.println(users);
				}
				
				userIdDelete = scan.nextInt();
				System.out.println("Enter the new Username");
				userN = scan.next();
				System.out.println("Enter the new password");
				passW = scan.next();

				System.out.println("Will they be an admin? Yes or No");
				String answer = scan.next();
				if (answer.toUpperCase().equals("YES")) {
					admin = true;
				}
				User a = new User(userIdDelete, userN, passW, admin);

				userv.updateUser(a);

				System.out.println("The user " + userv.getUserById(userIdDelete).getUsername() + " has been updated");

				break;

			case 5:
				loop = false;
				logout();

				break;
			}
		}
	}

	static void logout() {
		loggedinUser = null;
		System.out.println("Logged Out Successfully");
		return;
	}

}
