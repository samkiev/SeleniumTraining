package utils;

import com.google.common.collect.ImmutableBiMap.Builder;

public class User {

	private int addToLogin = (int) (Math.random()*10000);
	private String login;
	private String password;
	private String name;
	private String email;
	
	private User(){		
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
}

