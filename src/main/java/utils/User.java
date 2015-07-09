package utils;

import java.util.function.Consumer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.DeletePage;
import pages.SignUpPage;

public class User {

	private boolean isExisting;
	private int addToLogin = (int) (Math.random()*10000);
	private String login;
	private String password;
	private String name;
	private String email;
	
	private User() {
		isExisting = false;
		this.login = "testuserlogin"+ addToLogin ;
		this.password = "password"+ addToLogin;
		this.name = "testusername" + addToLogin;
		this.email = login + "@testuser.com";
	}
	
	public static User generateMockUser(){
		return new User();
	}
	
	public static User generateEmptyFieldsUser(){
		 User user = new User();
		 user.login = "";
		 user.password = "";
		 user.name = "";
		 user.email = "";
		return user;
	}
	
	public static User generateSpaceFieldUser(){
		 User user = new User();
		 user.login = "    ";
		 user.password = "     ";
		 user.name = "     ";
		 user.email = "     ";
		return user;
	}
	
	public static User generateSearchUser(){
		User user = new User();
		user.login = "special_user_login";
		user.password = "password";
		user.name = "special_user_name";
		user.email = "special@user.com";
		return user;
	}
	
	public static User setLoginAndPassword(String login, String password){		
		User user = new User();
		user.login = login;
		user.password = password;
		return user;
	}
	
	public static User setLogin(String login){		
		User user = new User();
		user.login = login;
		return user;
	}
	
	public static User setPassword(String password){		
		User user = new User();
		user.password = password;
		return user;
	}
	
	public static User setName(String name){		
		User user = new User();
		user.name = name;
		return user;
	}
	
	public static User setEmail(String email){		
		User user = new User();
		user.email = email;
		return user;
	}

	public String getName() {
		return name;
	}	
	
	public String getEmail() {
		return email;
	}
				
	public String getLogin(){
		return login;		
	}
	
	public String getPassword(){
		return password;		
	}
	
	public boolean isExisting() {
		return isExisting;
	}
	
	private boolean executeInDriver(Consumer<WebDriver> consumer) {
		WebDriver wd = null;
		try {
			 wd = new FirefoxDriver();
			 consumer.accept(wd);
		}
		catch (Exception e) {
			return false;
		}
		finally {
			if (wd != null) {
				wd.quit();
			}
		}
		return true;
	}
	
	public User register() {
		if (!isExisting) {
			isExisting = executeInDriver(wd -> new SignUpPage(wd).get().signUpAs(User.this));
		}
		return this;
	}
	
	public User delete(boolean force) {
		if (isExisting || force) {
			isExisting = !executeInDriver(wd -> new DeletePage(wd, User.this.getName()).get().deleteUser());
		}
		return this;
	}
	
	public User delete() {
		return delete(false);
	}
	
	@Override
	public String toString() {
		return String.format("{User: {login: '%s', password: '%s', name: '%s', email: '%s', existing: '%s'}}", login, password, name, email, isExisting);
	}
}

