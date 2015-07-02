package utils;

public class User {
	
	private int addToLogin = (int) (Math.random()*1000);
	private String login = "testuserlogin"+ addToLogin ;
	private String password = "password"+ addToLogin;
	private String name = "testusername" + addToLogin;
	private String email = login + "@testuser.com";
	
	public User(){
		
	}
	
	public User(String login){
		this.login = login;
	}
	
	public User(String login, String password){
		this.login = login;
		this.password = password;
	}
	
	public User(String login, String password, String name, String email) {
		
		this.login = login;
		this.password = password;
		this.name = name;
		this.email = email;
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
}


