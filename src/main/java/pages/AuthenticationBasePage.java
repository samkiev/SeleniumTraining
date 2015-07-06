package pages;

import org.openqa.selenium.WebDriver;
import utils.User;


public abstract class AuthenticationBasePage<T extends AuthenticationBasePage<T>> extends BasePage<T> {

	//public static final UserOld ADMIN = new UserOld("admin","admin");
	
	
	public AuthenticationBasePage(WebDriver wd) {
		super(wd);
	}

	protected AuthenticationBasePage(WebDriver wd, boolean checkIfLoaded) {
		super(wd, checkIfLoaded);
	}

	@Override
	public void load() {
		if (!isLoggedIn()) {				
			new LoginPage(driver).get().loginAs(User.setLoginAndPassword("admin", "admin"));
		}
		driver.get(getPageUrl());
	}	

	public void logOut(){
		System.out.println("Log Out");
		logOutLink.click();
	}
}
