package utils;

public class User {
	
	private String login;
	private String password;
	
	public User(){
		String login = System.getProperty("loigin");
		String password = System.getProperty("password");	
	}
	public String getLogin(String login){
			return System.getProperty("loigin");		
		}
	public String getPassword(String password){
		return System.getProperty("password");		
	}
	
}


