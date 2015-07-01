package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class User {
	
	private String login;
	private String password;
	private String name;
	private String email;
	
	
	public User(String login, String password, String name, String email) {
		
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}		
	public String getLogin(){
			return login;		
	}
	public String getPassword(){
		return password;		
	}	
}


