package dev.ric.services;

import java.util.List;

import dev.ric.daos.UserDAO;
import dev.ric.daos.UserDAOimpl;
import dev.ric.entities.User;

public class UserServicesImpl implements UserServices {
	UserDAO udao = new UserDAOimpl();
	
	
	public boolean createUser(String username, String password, boolean admin) {
		User user = new User(username, password, admin);
		return udao.createUser(user);
	}
	
	public User login(String username, String password) {
		for(User user : udao.getAllUsers()) {		
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}	
		return null;
	}

	@Override
	public boolean deleteUser(int id) {
		return udao.deleteUser(id);
	}


	@Override
	public User getUserById(int id) {
		return udao.getUserById(id);
	}

	@Override
	public boolean updateUser(User change) {
		return udao.updateUser(change);
		
	}

	@Override
	public List<User> getAllUsers() {
		return udao.getAllUsers();
		
	}
	
	

}
