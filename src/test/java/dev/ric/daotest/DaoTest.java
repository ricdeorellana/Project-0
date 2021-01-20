package dev.ric.daotest;

import org.junit.jupiter.api.Test;

import dev.ric.daos.UserDAO;
import dev.ric.daos.UserDAOimpl;
import dev.ric.entities.User;

class DaoTest {

	private static UserDAO udao = new UserDAOimpl();
	@Test
	public void addUser() {
		User u = new User("admin1", "password1", false );
		udao.createUser(u);
	}
	
	@Test
	public void updateUser() {
		User u = new User(22, "Lilbob", "Bob", false);
		udao.updateUser(u);
	}
	
	
	@Test
	public void getEmployee() {
		System.out.println(udao.getAllUsers());
		
		
	}
	
	@Test
	public void deleteUser() {
	udao.deleteUser(21);
	}

}
