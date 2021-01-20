package dev.ric.daos;

import java.util.List;

import dev.ric.entities.User;

/*
 * The User will be able to create a login, and will be able
 * to retrieve their data through the read statement. The admin
 * will be able to use the getallusers in order to be able to see
 * what users there are. Finally, Users will be able to delete their accounts. 
 * Admins can delete other users.
 */
public interface UserDAO {
	//CREATE
	boolean createUser(User user);
	
	//READ
	User getUserById(int id);
	
	
	List<User> getAllUsers();
	
	//UPDATE
	
	boolean updateUser(User change);
	
	//DELETE
	boolean deleteUser(int id);

}
