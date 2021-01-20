package dev.ric.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.ric.entities.Transaction;
import dev.ric.util.JDBCConnection;

public class TransactionDAOimpl implements TransactionDAO {
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public boolean withdrawTransaction(Transaction transaction) {
		try {
			// Java is unaware of our sequences, so
			// its better or easier if we just use our procedure
			// that abstracted our sequence
			String sql = "CALL withdraw(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql); // THIS IS USED TO CALL PROCEDURES
			cs.setString(1, Integer.toString(transaction.getAccountId()));
			cs.setString(2, Double.toString(transaction.getChangeBal()));
			cs.setString(3, Double.toString(transaction.getFinBal()));
			cs.execute();
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean depositTransaction(Transaction transaction) {
		try {
			
			String sql = "CALL deposit(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql); // THIS IS USED TO CALL PROCEDURES
			cs.setString(1, Integer.toString(transaction.getAccountId()));
			cs.setString(2, Double.toString(transaction.getChangeBal()));
			cs.setString(3, Double.toString(transaction.getFinBal()));
			cs.execute();
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	

	@Override
	public List<Transaction> getUserTransaction(int id) {
		List<Transaction> transactions = new ArrayList<Transaction>();

		try {
			String sql = "SELECT * FROM transactions WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Transaction a = new Transaction();
				a.setAccountId(rs.getInt("ACCOUNT_ID"));
				a.setTransNum(rs.getInt("TRANSACTION_NUMBER"));
				a.setWithdrawed(rs.getDouble("WITHDRAWN"));
				a.setDeposited(rs.getDouble("DEPOSITED"));
				a.setFinBal(rs.getDouble("ENDING_BALANCE"));
				transactions.add(a);
			}

			return transactions;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	

}
