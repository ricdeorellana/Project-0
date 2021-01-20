package dev.ric.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.ric.entities.Account;
import dev.ric.util.JDBCConnection;

public class AccountDAOimpl implements AccountDAO {
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public boolean createAccount(Account account) {

		try {
			// Java is unaware of our sequences, so
			// its better or easier if we just use our procedure
			// that abstracted our sequence
			String sql = "CALL add_account(?,?)";
			CallableStatement cs = conn.prepareCall(sql); // THIS IS USED TO CALL PROCEDURES
			cs.setString(1, account.getAccountName());
			cs.setString(2, Integer.toString(account.getId()));

			cs.execute();
			System.out.println("Congratulations on your new account " + account.getAccountName());
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Account> getAllAccountByID(int id) {

		List<Account> accounts = new ArrayList<Account>();

		try {
			String sql = "SELECT * FROM accounts WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Account a = new Account();
				a.setAccountID(rs.getInt("ACCOUNT_ID"));
				a.setAccountName(rs.getString("ACCOUNT_NAME"));
				a.setBalance(rs.getDouble("BALANCE"));
				a.setId(rs.getInt("ID"));
				accounts.add(a);
			}

			return accounts;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean updateAccountBal(Account change) {
		try {
			
			String sql = "UPDATE accounts SET BALANCE = ? WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Double.toString(change.getBalance()));
			ps.setString(2, Integer.toString(change.getAccountID()));
			ps.executeQuery();
			return true;

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteAccount(int id) {
		
		try {
			String sql = "DELETE accounts WHERE ACCOUNT_ID = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ps.executeQuery();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Account getAccountByAccountID(int id) {
		try {
			String sql = "SELECT * FROM accounts WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));

			ResultSet rs = ps.executeQuery();

			Account a = new Account();
			rs.next();
			a.setAccountID(rs.getInt("ACCOUNT_ID"));
			a.setAccountName(rs.getString("ACCOUNT_NAME"));
			a.setBalance(rs.getDouble("BALANCE"));
			a.setId(rs.getInt("ID"));

			return a;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
