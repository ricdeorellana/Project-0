package dev.ric.entities;
// This is the User entity, it contains our User bean

public class User {

	private int id; // Will distinguish user
	private String username; // Unique Username for login and interaction
	private String password; // Password to be able to login
	private boolean superUser = false; // Will give admin privileges
	
	public User(int id, String username, String password, boolean superUser) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.superUser = superUser;
	}

	
	
public User() {
		super();
	}



	// This User constructor will be used to create initial login
	public User(String username, String password, boolean superUser) {
		super();
		this.username = username;
		this.password = password;
		this.superUser = superUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSuperUser() {
		return superUser;
	}

	public void setSuperUser(boolean superUser) {
		this.superUser = superUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", superUser=" + superUser + "]";
	}
	
	




}
