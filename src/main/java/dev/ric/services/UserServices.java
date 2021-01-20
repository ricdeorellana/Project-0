package dev.ric.services;

import java.util.List;

import dev.ric.entities.User;

public interface UserServices {
	
	boolean createUser(String username, String password, boolean admin);
	
	User login(String username, String password);
	
	boolean deleteUser(int id);
	
	boolean updateUser(User change);
	
	User getUserById(int id);
	
	List<User> getAllUsers();
	
}
