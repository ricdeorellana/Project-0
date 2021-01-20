package dev.ric.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.ric.entities.User;
import dev.ric.util.JDBCConnection;



public class UserDAOimpl implements UserDAO {
public static Connection conn = JDBCConnection.getConnection();
	@Override
	public boolean createUser(User user) {
		// DML Statements Performed from within JDBC auto-commit.

				try {
					// Java is unaware of our sequences, so
					// its better or easier if we just use our procedure
					// that abstracted our sequence
					String admin;
					String sql = "CALL add_user(?,?, ?)";
					CallableStatement cs = conn.prepareCall(sql); // THIS IS USED TO CALL PROCEDURES
					cs.setString(1, user.getUsername());
					cs.setString(2, user.getPassword());
					
					if(user.isSuperUser()) admin = "Y";
					else admin = "N";
					
					cs.setString(3, admin);
					
					cs.execute();
					return true;

				} catch (SQLException e) {
					e.printStackTrace();
				}
				return false;
	}

	@Override
	public User getUserById(int id) {
		try {
			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(id));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User a = new User();
				a.setId(rs.getInt("ID"));
				a.setUsername(rs.getString("USERNAME"));
				a.setPassword(rs.getString("PASSWORD"));
				if(rs.getString("SYSADMIN").toUpperCase().equals("Y")) {
					a.setSuperUser(true);
				}
				else a.setSuperUser(false);
				
				return a;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();

		try {
			String sql = "SELECT * FROM users";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User a = new User();
				a.setId(rs.getInt("ID"));
				a.setUsername(rs.getString("USERNAME"));
				a.setPassword(rs.getString("PASSWORD"));
				if(rs.getString("SYSADMIN").toUpperCase().equals("Y")) {
					a.setSuperUser(true);
				}
				else a.setSuperUser(false);
				users.add(a);
			}

			return users;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


	@Override
	public boolean deleteUser(int id) {
		try {
			String sql = "DELETE users WHERE ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ps.executeQuery();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(User change) {

		try {
			String admin;
			String sql = "UPDATE users SET username = ?, password = ?, sysadmin= ? WHERE ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			if(change.isSuperUser()) admin = "Y";
			else admin = "N";
			ps.setString(1, change.getUsername());
			ps.setString(2, change.getPassword());
			ps.setString(3, admin);
			ps.setString(4, Integer.toString(change.getId()));
			ps.executeUpdate();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
